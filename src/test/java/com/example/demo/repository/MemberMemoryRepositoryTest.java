package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberMemoryRepositoryTest {

  MemberMemoryRepository memberRepository;

  @BeforeEach
  void beforeEach() {
    memberRepository = new MemberMemoryRepository();
  }

  @AfterEach
  void afterEach() {
    memberRepository.flush();
  }

  @Test
  void save() {
    // given
    Member member = new Member();
    member.setName("member");

    // when
    Member savedMember = memberRepository.save(member);

    // then
    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findById() {
    // given
    Member member = new Member();
    member.setName("member");
    memberRepository.save(member);

    // when
    Member savedMember = memberRepository.findById(member.getId()).get();

    // then
    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findByName() {
    // given
    Member member = new Member();
    member.setName("member");
    memberRepository.save(member);

    // when
    Member savedMember = memberRepository.findByName("member").get();

    // then
    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findAll() {
    // given
    Member memberA = new Member();
    memberA.setName("memberA");
    memberRepository.save(memberA);

    Member memberB = new Member();
    memberB.setName("memberB");
    memberRepository.save(memberB);

    // when
    List<Member> members = memberRepository.findAll();

    // then
    assertThat(members).hasSize(2);
  }
}
