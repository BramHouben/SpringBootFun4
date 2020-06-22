package com.Fontys.s44.BramHouben.Fun4Backend.dbSource;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MysqlDataSource {



    @Bean()
    //   @ConfigurationProperties("app.datasource")
    public HikariDataSource hikariDataSource() throws UnknownHostException {
        if (getIp()) {
            HikariConfig hikariConfig = new HikariConfig();
//            hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/bal2");
//            hikariConfig.setUsername("postgres");
//            hikariConfig.setPassword("password");
            hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/bal?createDatabaseIfNotExist=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            hikariConfig.setUsername("root");
            hikariConfig.setPassword("T5^Z+fASP<Wv;ty5");
            return new HikariDataSource(hikariConfig);
        } else {
            HikariConfig hikariConfig = new HikariConfig();
//            hikariConfig.setJdbcUrl("jdbc:postgresql://172.17.0.2:5432/bal2");
//            hikariConfig.setUsername("postgres");
//            hikariConfig.setPassword("password");
            hikariConfig.setJdbcUrl("jdbc:mysql://172.17.0.2:3306/bal?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            hikariConfig.setUsername("root");
            hikariConfig.setPassword("password");
            hikariConfig.setMaximumPoolSize(30);
            return new HikariDataSource(hikariConfig);
        }

    }


    private boolean getIp() throws UnknownHostException {
        InetAddress ip;
        String hostname;
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        System.out.println("Your current IP address : " + ip);
        System.out.println("Your current Hostname : " + hostname);
        return ip.toString().contains("192");

    }


}
