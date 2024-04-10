package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  void join() {
    // given
    Member member = new Member();
    member.setName("member");

    // when
    Long memberId = memberService.join(member);

    // then
    assertThat(member.getId()).isEqualTo(memberId);
  }
}
