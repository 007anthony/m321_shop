package ch.bbw.ap.shop.m321shopcore.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


public class KeyModel {

    private String algorithm;
    private String location;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Path getLocation() {
        return Paths.get(location);
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
