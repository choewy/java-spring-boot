package com.example.demo.repository;

import com.example.demo.domain.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MemberJDBCTemplateRepository implements MemberRepository {

  private final JdbcTemplate jdbcTemplate;

  public MemberJDBCTemplateRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private RowMapper<Member> memberRowMapper() {
    return (rs, rowNum) -> {
      Member member = new Member();
      member.setId(rs.getLong("id"));
      member.setName(rs.getString("name"));

      return member;
    };
  }

  @Override
  public Member save(Member member) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", member.getName());
    Number memberId = jdbcInsert.executeAndReturnKey(
      new MapSqlParameterSource(parameters)
    );

    member.setId(memberId.longValue());
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    List<Member> members = jdbcTemplate.query(
      "SELECT * FROM member WHERE id = ?",
      memberRowMapper(),
      id
    );

    return members.stream().findAny();
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> members = jdbcTemplate.query(
      "SELECT * FROM member WHERE name = ?",
      memberRowMapper(),
      name
    );

    return members.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    return jdbcTemplate.query("SELECT * FROM member", memberRowMapper());
  }
}
