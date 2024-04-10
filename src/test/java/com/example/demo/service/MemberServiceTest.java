package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemberMemoryRepository memberRepository;
  MemberService memberService;

  @BeforeEach
  void beforeEach() {
    memberRepository = new MemberMemoryRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void afterEach() {
    memberRepository.flush();
  }

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

  @Test
  void joinFailed() {
    // given
    Member memberA = new Member();
    memberA.setName("member");

    Member memberB = new Member();
    memberB.setName("member");

    // when
    memberService.join(memberA);
    IllegalStateException exception = assertThrows(
      IllegalStateException.class,
      () -> memberService.join(memberB)
    );

    // then
    assertThat(exception.getMessage()).isEqualTo("Already Exists Member");
  }
}
