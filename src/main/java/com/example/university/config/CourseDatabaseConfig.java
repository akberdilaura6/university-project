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
        basePackages = "com.example.university.course.repository",
        entityManagerFactoryRef = "courseEntityManagerFactory",
        transactionManagerRef = "courseTransactionManager"
)
public class CourseDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.course")
    public DataSourceProperties courseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource courseDataSource() {
        return courseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean courseEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("courseDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.university.course.entity")
                .persistenceUnit("course")
                .properties(Map.of("hibernate.hbm2ddl.auto", "update"))
                .build();
    }

    @Bean
    public PlatformTransactionManager courseTransactionManager(
            @Qualifier("courseEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}