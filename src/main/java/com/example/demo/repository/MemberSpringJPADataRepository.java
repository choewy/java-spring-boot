package com.example.demo.repository;

import com.example.demo.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSpringJPADataRepository
  extends JpaRepository<Member, Long>, MemberRepository {
  @Override
  Optional<Member> findByName(String name);
}
