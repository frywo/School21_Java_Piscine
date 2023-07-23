package edu.school21.repositories;

import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

public class EmbeddedDataSourceTest extends Assert {
    EmbeddedDatabase database;

    @BeforeEach
    @Test
    public void init() throws SQLException{
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        assertNotNull(database.getConnection());
    }

    @AfterEach
    public void shutdown(){
        database.shutdown();
    }

}
