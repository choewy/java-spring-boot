package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberMemoryRepositoryTest {

  MemberMemoryRepository repository = new MemberMemoryRepository();

  @AfterEach
  void afterEach() {
    repository.flush();
  }

  @Test
  void save() {
    Member member = new Member();
    member.setName("member");
    Member savedMember = repository.save(member);

    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findById() {
    Member member = new Member();
    member.setName("member");
    repository.save(member);

    Member savedMember = repository.findById(member.getId()).get();
    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findByName() {
    Member member = new Member();
    member.setName("member");
    repository.save(member);

    Member savedMember = repository.findByname("member").get();
    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findAll() {
    Member memberA = new Member();
    memberA.setName("memberA");
    repository.save(memberA);

    Member memberB = new Member();
    memberB.setName("memberB");
    repository.save(memberB);

    List<Member> members = repository.findAll();

    assertThat(members).hasSize(2);
  }
}
