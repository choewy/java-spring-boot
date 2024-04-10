package com.example.demo;

import com.example.demo.repository.MemberJDBCRepository;
import com.example.demo.repository.MemberRepository;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private final DataSource dataSource;

  public SpringConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  MemberRepository memberRepository() {
    return new MemberJDBCRepository(dataSource);
  }
}
