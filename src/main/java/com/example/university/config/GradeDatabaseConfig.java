package com.example.university.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.university.grade.repository",
        entityManagerFactoryRef = "gradeEntityManagerFactory",
        transactionManagerRef = "gradeTransactionManager"
)
public class GradeDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.grade")
    public DataSourceProperties gradeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource gradeDataSource() {
        return gradeDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean gradeEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("gradeDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.university.grade.entity")
                .persistenceUnit("grade")
                .properties(Map.of("hibernate.hbm2ddl.auto", "update"))
                .build();
    }

    @Bean
    public PlatformTransactionManager gradeTransactionManager(
            @Qualifier("gradeEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}