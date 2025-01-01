package ch.bbw.ap.shop.usermanager.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.crypto.SecretKey;
import java.security.cert.Certificate;

@Configuration
@ConfigurationProperties(prefix = "user-manager.auth.jwt")
public class JwtProperties {

    private KeyModel privateKey;
    private long expirationDate = 3_600_000;


    public KeyModel getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(KeyModel privateKey) {
        this.privateKey = privateKey;
    }

    public long getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }


}
