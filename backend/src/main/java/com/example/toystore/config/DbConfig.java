package com.example.toystore.config;

import com.example.toystore.common.LocalDateTimeConverter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;
import org.sql2o.quirks.NoQuirks;
import org.sql2o.converters.Converter;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DbConfig {

    private static final long DATA_SOURCE_LEAK_DETECTION = 30000;
    private static final long DATA_SOURCE_FAIL_TIMEOUT = 15000;
    public static final String POOL_NAME = "GolfTEC-Pool";

    @Bean
    public Sql2o sql2oWritingDataSource(@Autowired DataSource dataSource) {
        Map<Class, Converter> converters = new HashMap<>();
        converters.put(LocalDateTime.class, new LocalDateTimeConverter());
        return new Sql2o(dataSource, new NoQuirks(converters));
    }

    @Bean
    public DataSource goaDataSource() {
        HikariConfig hikariConfig = getHikariConfig();
        return new HikariDataSource(hikariConfig);
    }

    private HikariConfig getHikariConfig() {
        HikariConfig hikariConfig = new HikariDataSource();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/toy_store");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("MyNewPass");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setPoolName(POOL_NAME);
        hikariConfig.setLeakDetectionThreshold(DATA_SOURCE_LEAK_DETECTION);
        hikariConfig.setInitializationFailTimeout(DATA_SOURCE_FAIL_TIMEOUT);
        hikariConfig.addHealthCheckProperty("connectivityCheckTimeoutMs", "10000");
        hikariConfig.addHealthCheckProperty("expected99thPercentileMs", "10000");
        return hikariConfig;
    }
}
