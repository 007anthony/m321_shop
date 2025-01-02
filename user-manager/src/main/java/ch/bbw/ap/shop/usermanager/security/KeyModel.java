package ch.bbw.ap.shop.usermanager.security;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class KeyModel {

    private String algorithm;
    private Path location;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Path getLocation() {
        return location;
    }

    public void setLocation(Path location) throws URISyntaxException {
        this.location = Paths.get(Objects.requireNonNull(KeyModel.class.getClassLoader().getResource(location.toString())).toURI());
    }
}
