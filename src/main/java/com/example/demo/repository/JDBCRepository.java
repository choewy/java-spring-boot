package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

public abstract class JDBCRepository {

  protected DataSource dataSource;

  protected JDBCRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  protected Connection getConnection() {
    return DataSourceUtils.getConnection(dataSource);
  }

  protected void close(
    Connection connection,
    PreparedStatement preparedStatement,
    ResultSet resultSet
  ) {
    try {
      if (resultSet != null) {
        resultSet.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      if (connection != null) {
        releaseConnection(connection);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void releaseConnection(Connection connection) {
    DataSourceUtils.releaseConnection(connection, dataSource);
  }
}
