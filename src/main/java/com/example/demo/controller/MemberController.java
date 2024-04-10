package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("members")
  public String members(Model model) {
    List<Member> members = memberService.getMembers();
    model.addAttribute("members", members);

    return "pages/members";
  }

  @GetMapping("members/new")
  public String membersNew() {
    return "pages/member_new";
  }

  @PostMapping("members/new")
  public String createMember(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }
}
