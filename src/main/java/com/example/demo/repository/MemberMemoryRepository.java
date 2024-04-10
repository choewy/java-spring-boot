package com.example.demo.repository;

import com.example.demo.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberMemoryRepository implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  public void flush() {
    store = new HashMap<>();
    sequence = 0L;
  }

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByname(String name) {
    return store
      .values()
      .stream()
      .filter(member -> member.getName().equals(name))
      .findFirst();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }
}