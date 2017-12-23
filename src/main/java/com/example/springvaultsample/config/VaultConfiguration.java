package com.example.springvaultsample.config;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.vault.config.EnvironmentVaultConfiguration;

@PropertySource("vault.yml")
@Import(EnvironmentVaultConfiguration.class)
public class VaultConfiguration {
}
