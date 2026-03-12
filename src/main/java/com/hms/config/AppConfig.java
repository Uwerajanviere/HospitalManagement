package com.hms.config;

import com.hms.config.DatabaseConnection;
import com.hms.service.AppointmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@ComponentScan("com.hms.config")
public class AppConfig {

    @Bean
    public Connection getConnection() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        return connection.getConnection();
    }


}
