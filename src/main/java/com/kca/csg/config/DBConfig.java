package com.kca.csg.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//    basePackages = "com.kca.csg",
//    transactionManagerRef = "mariaDB_transactionManager",
//    entityManagerFactoryRef = "mariaDB_entity"
//)
public class DBConfig {
//     @Primary
//     @Bean(name = "maria_dataSource")
//     @ConfigurationProperties("spring.data.maria")
//     public DataSource mariaDataSource(){
//         return DataSourceBuilder.create().type(HikariDataSource.class).build();
//     }
//
//     @Primary
//     @Bean(name = "mariaDB_entityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//         EntityManagerFactoryBuilder builder, @Qualifier("maria_dataSource") DataSource dataSource){
//             Map<String, String> map = new HashMap<>();
//             map.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
//             map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//
//             return builder.dataSource(dataSource).packages("com.kca.csg.model").properties(map).build();
//     }
}
