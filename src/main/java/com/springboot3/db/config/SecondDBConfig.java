package com.springboot3.db.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.springboot3.db.repository.second",
  entityManagerFactoryRef = "secondEntityManagerFactory",
  transactionManagerRef = "secondTransactionManager"
)
@MapperScan(value = "com.springboot3.db.mapper.second", sqlSessionFactoryRef = "secondSessionFactory")
public class SecondDBConfig extends DBConfig {

  private final String SECOND_DATA_SOURCE = "secondDataSource";

  private final String SECOND_MANAGER_FACTORY = "secondEntityManagerFactory";

  private final String SECOND_TRANSACTION_MANAGER = "secondTransactionManager";

  private final String SECOND_SESSION_FACTORY = "secondSessionFactory";

  private final String SECOND_SESSION_TEMPLATE = "secondSessionTemplate";

  private final String SECOND_ENTITY_PACKAGE = "com.springboot3.db.entity.second";

  private final String SECOND_PERSIST_UNIT_NAME = "secondEntityManager";

  @ConfigurationProperties(prefix = "spring.second-db.datasource")
  @Bean(name = SECOND_DATA_SOURCE)
  public DataSource secondDataSource() {

    return super.setDataSource();
  }

  @Bean(name = SECOND_MANAGER_FACTORY)
  public EntityManagerFactory entityManagerFactory(@Qualifier(SECOND_DATA_SOURCE) DataSource dataSource) {

    return super.setEntityManagerFactory(dataSource, SECOND_ENTITY_PACKAGE, SECOND_PERSIST_UNIT_NAME);
  }

  @Bean(name = SECOND_TRANSACTION_MANAGER)
  public PlatformTransactionManager transactionManager(@Qualifier(SECOND_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {

    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean(name = SECOND_SESSION_FACTORY)
  public SqlSessionFactory sqlSessionFactory(@Qualifier(SECOND_DATA_SOURCE) DataSource dataSource) throws Exception {

    return super.makeSqlSessionFactory(dataSource);
  }

  @Bean(name = SECOND_SESSION_TEMPLATE)
  public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier(SECOND_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
    setMybatisProperties(sqlSessionFactory);

    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
