package com.springboot3.db.config.naming;

import io.micrometer.common.util.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CamelCaseToSnakeNamingStrategy implements PhysicalNamingStrategy {
  @Override
  public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
    return convert(name);
  }

  @Override
  public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
    return convert(name);
  }

  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
    return convert(name);
  }

  @Override
  public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
    return convert(name);
  }

  @Override
  public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
    return convert(name);
  }

  private Identifier convert(Identifier identifier) {
    if (identifier == null || StringUtils.isBlank(identifier.getText())) {
      return identifier;
    }

    String regex = "([a-z])([A-Z])";
    String replacement = "$1_$2";
    String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();

    return Identifier.toIdentifier(newName);
  }
}
