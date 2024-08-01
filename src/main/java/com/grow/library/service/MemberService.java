package com.grow.library.service;

import com.grow.library.model.Member;
import com.grow.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    public Member getMember(Long id) {
        return memberRepository.getMember(id);
    }
}
