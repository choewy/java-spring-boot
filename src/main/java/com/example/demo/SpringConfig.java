package com.example.demo;

import com.example.demo.repository.MemberJPARepository;
import com.example.demo.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private final EntityManager em;

  public SpringConfig(EntityManager em) {
    this.em = em;
  }

  @Bean
  MemberRepository memberRepository() {
    return new MemberJPARepository(em);
  }
}
