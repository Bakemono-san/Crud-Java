package org.detteapp.odc.config;

import org.detteapp.odc.collections.ArticleRepositoryCollection;
import org.detteapp.odc.collections.ClientRepositoryCollection;
import org.detteapp.odc.collections.DetteRepositoryCollection;
import org.detteapp.odc.database.DatabaseInterface;
import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.entities.ClientEntity;
import org.detteapp.odc.entities.DetteEntity;
import org.detteapp.odc.repositories.ArticleRepositoryJdbc;
import org.detteapp.odc.repositories.ClientRepositoryJdbc;
import org.detteapp.odc.repositories.DetteRepositoryJdbc;
import org.detteapp.odc.repositories.interfaces.ArticleRepositoryInterface;
import org.detteapp.odc.repositories.interfaces.ClientRepositoryInterface;
import org.detteapp.odc.repositories.interfaces.DetteRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "org.detteapp.odc")
@PropertySource("classpath:application.properties")
public class ProjectConifg {


    @Value("${service}")
    private String service;

    @Autowired
    private DatabaseConfig database;

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "collection")
    public ArticleRepositoryInterface articleRepository() {
         Set<ArticleEntity> articles = new HashSet<>();
         return new ArticleRepositoryCollection(articles);
    }

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "jdbc")
    public ArticleRepositoryInterface articleRepositoryJdbc(){
        return  new ArticleRepositoryJdbc(database);
    }

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "jdbc")
    public ClientRepositoryInterface clientRepositoryJdbc() {
        return new ClientRepositoryJdbc(database);
    }

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "jdbc")
    public DetteRepositoryInterface detteRepositoryJdbc() {
        return new DetteRepositoryJdbc(database);
    }

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "collection")
    public DetteRepositoryInterface detteRepository() {
        Set<DetteEntity> dettes = new HashSet<>();
        return new DetteRepositoryCollection(dettes);
    }

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "collection")
    public ClientRepositoryInterface clientRepository() {
        Set<ClientEntity> clients = new HashSet<>();
        return new ClientRepositoryCollection(clients);
    }
}


