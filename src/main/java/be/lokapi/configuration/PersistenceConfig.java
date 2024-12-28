package be.lokapi.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:8889/sch_lokapi_d");
        dataSource.setUsername("userTawfik");
        dataSource.setPassword("Made4Tyo..");
        return dataSource;
    }


    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em= new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("be.lokapi.entity");

        HibernateJpaVendorAdapter adapter =  new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");

        em.setJpaVendorAdapter(adapter);

        Properties properties = new Properties();
        // Définit la stratégie de nommage physique pour Hibernate
        properties.setProperty("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.setProperty("spring.jpa.hibernate.ddl-auto","update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");

        em.setJpaProperties(properties);

        return em;
    }

    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
