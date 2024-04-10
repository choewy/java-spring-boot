package com.example.demo;

import com.example.demo.repository.MemberMemoryRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  MemberRepository memberRepository() {
    return new MemberMemoryRepository();
  }
}
