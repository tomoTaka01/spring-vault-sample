package com.example.springvaultsample;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;

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
            logger.info("vault json response is [{}]", response.getData());
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

}
