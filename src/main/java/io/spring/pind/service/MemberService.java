package io.spring.pind.service;

import io.spring.pind.dto.MemberDTO;
import io.spring.pind.entity.Member;

import java.util.Optional;

public interface MemberService {
    Long register(MemberDTO memberDTO);

    MemberDTO getMember(Long mno);

    void modify(MemberDTO memberDTO);

    void remove(Long mno);

    Optional<Member> findByCertifiedKey(Member member);

    void modifyCertifiedKey(Member member);

    default MemberDTO entitiesToDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .regDate(member.getRegDate())
                .modDate(member.getModDate())
                .build();

        return memberDTO;
    }

    default Member dtoToEntity(MemberDTO memberDTO){
        Member member = Member.builder()
                .id(memberDTO.getId())
                .email(memberDTO.getEmail())
                .name(memberDTO.getName())
                .password(memberDTO.getPassword())
                .build();

        return member;
    }
}
