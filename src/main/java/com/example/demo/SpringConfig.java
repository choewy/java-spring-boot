package com.example.demo;

import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  MemberService memberService() {
    return new MemberService(memberRepository);
  }
}
