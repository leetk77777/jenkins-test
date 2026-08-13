package com.example.demo.batch.processor;

import com.example.demo.batch.domain.Member;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MemberItemProcessor implements ItemProcessor<Member, Member> {

	@Override
    public Member process(Member member) {

        member.setName(member.getName().toUpperCase());
        member.setAmount(member.getAmount() + 100);

        return member;
    }
}
