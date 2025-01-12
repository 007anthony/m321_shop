package ch.bbw.ap.shop.m321shopcore.security.jwt;

import ch.bbw.ap.shop.m321shopcore.security.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    @Autowired
    private JwtProperties jwtProperties;


    public String generateToken(Long id, String username, Role role) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {


        return Jwts.builder()
                .issuer("UserManager")
                .id(id.toString())
                .subject(username)
                .claim("role", role.toString())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationDate()))
                .signWith(getPrivateKey())
                .compact();
    }

    public boolean validateToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, MalformedJwtException {
        return Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .isSigned(token);
    }

    public Claims getClaims(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Claims claims = Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims;
    }

    private PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String key = Files.readString(jwtProperties.getPrivateKey().getLocation())
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\n", "");


        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(jwtProperties.getPrivateKey().getAlgorithm()).generatePrivate(spec);
    }

    private PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String key = Files.readString(jwtProperties.getPublicKey().getLocation())
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\n", "");


        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(jwtProperties.getPublicKey().getAlgorithm()).generatePublic(spec);
    }
}
