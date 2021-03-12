package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired //컨테이너 생성시 MemberController 생성자를 호출한다. Autowired가 있으면 스프링 컨테이너가 자동으로 컨테이너에 있는 memberService를 넣어준다. DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
