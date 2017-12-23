package com.example.springvaultsample.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
@ConfigurationProperties(prefix = "app.vault")
public class VaultProp {
    private URI uri;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
