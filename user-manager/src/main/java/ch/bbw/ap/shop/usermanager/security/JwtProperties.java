package ch.bbw.ap.shop.usermanager.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "user-manager.auth.jwt")
public class JwtProperties {

    private KeyModel privateKey;
    private KeyModel publicKey;
    private long expirationDate = 3_600_000;


    public KeyModel getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(KeyModel privateKey) {
        this.privateKey = privateKey;
    }

    public KeyModel getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(KeyModel publicKey) {
        this.publicKey = publicKey;
    }

    public long getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }


}
