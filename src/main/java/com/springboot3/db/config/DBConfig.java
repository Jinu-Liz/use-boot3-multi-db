package com.springboot3.db.config;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DBConfig extends HikariConfig {

  @Autowired
  private HibernateProperties hibernateProperties;

  @Autowired
  private JpaProperties jpaProperties;

  @Autowired
  private MybatisProperties mybatisProperties;

  protected void setConfigEntityManagerFactory(LocalContainerEntityManagerFactoryBean factory) {
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
      jpaProperties.getProperties(), new HibernateSettings()
    );

    factory.setJpaPropertyMap(properties);
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.afterPropertiesSet();
  }

  protected SqlSessionFactory makeSqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setVfs(SpringBootVFS.class);
    sessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
    sessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
//    sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisProperties.getConfigLocation()));

    return sessionFactoryBean.getObject();
  }

  protected void setMybatisProperties(SqlSessionFactory sqlSessionFactory) {
    mybatisProperties.getConfiguration().applyTo(sqlSessionFactory.getConfiguration());
  }
}