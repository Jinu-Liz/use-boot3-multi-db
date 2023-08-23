package com.springboot3.db.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JpaConfig {

  @PersistenceContext(unitName = "firstEntityManager")
  private EntityManager firstEntityManager;

  @PersistenceContext(unitName = "secondEntityManager")
  private EntityManager secondEntityManager;

  @Bean
  @Primary
  @Qualifier("FirstQF")
  JPAQueryFactory firstQueryFactory() {
    return new JPAQueryFactory(firstEntityManager);
  }

  @Bean
  @Qualifier("SecondQF")
  JPAQueryFactory secondQueryFactory() {
    return new JPAQueryFactory(secondEntityManager);
  }

}
