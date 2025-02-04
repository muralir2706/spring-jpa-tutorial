package com.murali.au.tutorial.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresDbContainer extends PostgreSQLContainer<PostgresDbContainer> {
    private static final String IMAGE_VERSION = "postgres:10.18";
    private static final String DATABASE_NAME = "testdb";
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "testpass";

    public static PostgresDbContainer container = new PostgresDbContainer()
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withInitScript("sql/init_script.sql");

    public PostgresDbContainer() {
        super(IMAGE_VERSION);
    }
}
