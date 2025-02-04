package com.murali.au.tutorial.config;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DbSetupExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        PostgresDbContainer.container.start();
        updateDataSourceProps(PostgresDbContainer.container);
    }

    private void updateDataSourceProps(PostgresDbContainer container) {
        System.setProperty("db.host", container.getHost());
        System.setProperty("db.port", container.getFirstMappedPort().toString());
        System.setProperty("db.username", container.getUsername());
        System.setProperty("db.password", container.getPassword());
        System.setProperty("db.database", container.getDatabaseName());
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }
}
