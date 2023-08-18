package com.springboot3.db.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.springboot3.db.repository.first",
  entityManagerFactoryRef = "firstEntityManagerFactory",
  transactionManagerRef = "firstTransactionManager"
)
@MapperScan(value = "com.springboot3.db.mapper.first", sqlSessionFactoryRef = "firstSessionFactory")
public class FirstDBConfig extends DBConfig {

  private final String FIRST_DATA_SOURCE = "firstDataSource";

  private final String FIRST_MANAGER_FACTORY = "firstEntityManagerFactory";

  private final String FIRST_TRANSACTION_MANAGER = "firstTransactionManager";

  private final String FIRST_SESSION_FACTORY = "firstSessionFactory";

  private final String FIRST_SESSION_TEMPLATE = "firstSessionTemplate";

  @ConfigurationProperties(prefix = "spring.first-db.datasource")
  @Bean(name = FIRST_DATA_SOURCE)
  @Primary
  public DataSource firstDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean(name = FIRST_MANAGER_FACTORY)
  @Primary
  public EntityManagerFactory entityManagerFactory(@Qualifier(FIRST_DATA_SOURCE) DataSource dataSource) {

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setPackagesToScan("com.springboot3.db.entity.first");
    factory.setPersistenceUnitName("firstEntityManager");
    setConfigEntityManagerFactory(factory);

    return factory.getObject();
  }

  @Bean(name = FIRST_TRANSACTION_MANAGER)
  @Primary
  public PlatformTransactionManager transactionManager(@Qualifier(FIRST_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {

    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean(name = FIRST_SESSION_FACTORY)
  @Primary
  public SqlSessionFactory sqlSessionFactory(@Qualifier(FIRST_DATA_SOURCE) DataSource dataSource) throws Exception {

    return makeSqlSessionFactory(dataSource);
  }

  @Bean(name = FIRST_SESSION_TEMPLATE)
  @Primary
  public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier(FIRST_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
    setMybatisProperties(sqlSessionFactory);

    return new SqlSessionTemplate(sqlSessionFactory);
  }
}