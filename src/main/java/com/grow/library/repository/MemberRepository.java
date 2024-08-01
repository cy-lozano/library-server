package com.grow.library.repository;

import com.grow.library.model.Loan;
import com.grow.library.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
    List<Member> getMembers();
    Member getMember(Long id);
}
