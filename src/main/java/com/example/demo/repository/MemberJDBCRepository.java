package com.example.demo.repository;

import com.example.demo.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class MemberJDBCRepository
  extends JDBCRepository
  implements MemberRepository {

  public MemberJDBCRepository(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  public Member save(Member member) {
    String sql = "INSERT INTO member(name) VALUES(?)";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = getConnection();
      preparedStatement =
        connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, member.getName());
      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        member.setId(resultSet.getLong(1));
        return member;
      }

      throw new SQLException("Failed Get Member with ID");
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
  }

  @Override
  public Optional<Member> findById(Long id) {
    String sql = "SELECT * FROM member WHERE id = ?";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Member member = new Member();
        member.setId(resultSet.getLong("id"));
        member.setName(resultSet.getString("name"));
        return Optional.of(member);
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
  }

  @Override
  public Optional<Member> findByName(String name) {
    String sql = "SELECT * FROM member WHERE name = ?";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, name);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Member member = new Member();
        member.setId(resultSet.getLong("id"));
        member.setName(resultSet.getString("name"));
        return Optional.of(member);
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
  }

  @Override
  public List<Member> findAll() {
    String sql = "SELECT * FROM member";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = getConnection();
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      List<Member> members = new ArrayList<>();

      while (resultSet.next()) {
        Member member = new Member();
        member.setId(resultSet.getLong("id"));
        member.setName(resultSet.getString("name"));
        members.add(member);
      }

      return members;
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
  }
}
