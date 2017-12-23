package com.example.springvaultsample.config;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.vault.config.EnvironmentVaultConfiguration;

@PropertySource("vault.yml")
@Import(EnvironmentVaultConfiguration.class)
public class VaultConfiguration {
}
