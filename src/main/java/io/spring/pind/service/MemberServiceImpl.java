package io.spring.pind.service;

import io.spring.pind.dto.MemberDTO;
import io.spring.pind.entity.Member;
import io.spring.pind.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final EmailService emailService;

    @Override
    public Long register(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);
        member.changeCertifiedKey(makeCertifiedKey());
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        if (result.isPresent()) {
            return null;
        }
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public MemberDTO getMember(Long mno) {
        Member member = memberRepository.getOne(mno);
        MemberDTO memberDTO = entitiesToDTO(member);
        return memberDTO;
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        Optional<Member> result = memberRepository.findById(memberDTO.getId());

        if(result.isPresent()){
            Member member = result.get();
            member.changeEmail(memberDTO.getEmail());
            member.changeName(memberDTO.getName());
            memberRepository.save(member);
        }
    }

    @Override
    public void remove(Long mno) {
        memberRepository.deleteById(mno);
    }

    @Override
    public Optional<Member> findByCertifiedKey(Member member) {
        return memberRepository.findByCertifiedKey(member.getEmail(), member.getCertifiedKey());
    }

    @Override
    public void modifyCertifiedKey(Member member) {
        memberRepository.updateCertifiedKey(member.getEmail());
    }

    private String makeCertifiedKey() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;

        do {
            num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }
        } while (sb.length() < 10);
        return sb.toString();
    }
}
