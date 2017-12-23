package com.example.springvaultsample.config;

import com.example.springvaultsample.prop.SpringCloudVaultProp;
import com.example.springvaultsample.prop.VaultProp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(VaultConfig.class);
    private final VaultProp vaultProp;
    private final SpringCloudVaultProp springCloudVaultProp;

    public VaultConfig(VaultProp vaultProp, SpringCloudVaultProp springCloudVaultProp) {
        this.vaultProp = vaultProp;
        this.springCloudVaultProp = springCloudVaultProp;
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        logger.info("uri=[{}]", this.vaultProp.getUri().toString());
        return VaultEndpoint.from(this.vaultProp.getUri());
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        logger.info("Spring cloud valut token=[{}]", this.springCloudVaultProp.getToken());
        return new TokenAuthentication(this.springCloudVaultProp.getToken());
    }
}
