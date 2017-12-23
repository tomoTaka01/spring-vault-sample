package com.example.springvaultsample;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

import java.net.URI;

@SpringBootApplication
public class SpringVaultSampleApplication {
    private static Logger logger = LoggerFactory.getLogger(SpringVaultSampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringVaultSampleApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(VaultTemplate vaultTemplate) {
        return x -> {
            vaultTemplate.write("secret/hello", new Hello("world"));
            VaultResponseSupport<Hello> hello = vaultTemplate.read("secret/hello", Hello.class);
            logger.info("vault value is [{}]", hello.getData().getVault());
            VaultResponse response = vaultTemplate.read("secret/hello");
            logger.info("vault json response is [{}]",response.getData());
        };
    }

    public static class Hello {
        String vault;

        public Hello(@JsonProperty("value") String value) {
            this.vault = value;
        }

        public String getVault() {
            return vault;
        }
    }

    @Configuration
    public static class VaultConfig extends AbstractVaultConfiguration {
        @Override
        public VaultEndpoint vaultEndpoint() {
//			return VaultEndpoint.create("localhost", 8200);
            return VaultEndpoint.from(URI.create("http://localhost:8200"));
        }

        @Override
        public ClientAuthentication clientAuthentication() {
            return new TokenAuthentication("907b6f15-ac68-f3f5-2ed6-c3fca36b96a8");
        }
    }
}