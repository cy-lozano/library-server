package com.grow.library.repository.impl;

import com.grow.library.model.Member;
import com.grow.library.repository.MemberRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DummyMemberRepository implements MemberRepository {
    private Map<Long, Member> members = new HashMap<>();

    public DummyMemberRepository() {
        initMembers();
    }

    private void initMembers() {
        Faker faker = new Faker();

        for (int i = 1; i <= 50; i++) {
            Member member = new Member();
            member.setId((long) i);
            member.setFirstName(faker.name().firstName());
            member.setLastName(faker.name().lastName());
            member.setMembershipNumber(faker.idNumber().toString());
            member.setEmail(faker.internet().emailAddress());
            member.setMobileNumber(faker.phoneNumber().cellPhone());
            members.put(member.getId(), member);
        }
    }

    @Override
    public List<Member> getMembers() {
        return members.values().stream().toList();
    }

    @Override
    public Member getMember(Long id) {
        return members.get(id);
    }


}
