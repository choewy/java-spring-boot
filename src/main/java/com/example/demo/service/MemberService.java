package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  private void checkDuplicateMemberByName(Member member) {
    memberRepository
      .findByName(member.getName())
      .ifPresent(m -> {
        throw new IllegalStateException("Already Exists Member");
      });
  }

  public Long join(Member member) {
    checkDuplicateMemberByName(member);

    memberRepository.save(member);

    return member.getId();
  }

  public List<Member> getMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> getMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
