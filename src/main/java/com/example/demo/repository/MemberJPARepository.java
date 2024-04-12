package com.example.demo.repository;

import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MemberJPARepository implements MemberRepository {

  private final EntityManager em;

  public MemberJPARepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Member save(Member member) {
    em.persist(member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    Member member = em.find(Member.class, id);
    return Optional.of(member);
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> members = em
      .createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
      .setParameter("name", name)
      .getResultList();

    return members.stream().findFirst();
  }

  @Override
  public List<Member> findAll() {
    return em
      .createQuery("SELECT m FROM Member m", Member.class)
      .getResultList();
  }
}
