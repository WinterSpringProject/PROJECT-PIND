package io.spring.pind.controller;

import io.spring.pind.service.EmailService;
import io.spring.pind.entity.Member;
import io.spring.pind.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/member/email/certified")
    @Transactional
    public ModelAndView checkmail(HttpServletRequest request, Member member) throws MessagingException {
        Optional<Member> m = memberService.findByCertifiedKey(member);

        if(m != null) {
            memberService.modifyCertifiedKey(member);
        }
        return new ModelAndView("email_success");
    }
}
