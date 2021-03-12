package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired //컨테이너 생성시 MemberController 생성자를 호출한다. Autowired가 있으면 스프링 컨테이너가 자동으로 컨테이너에 있는 memberService를 넣어준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
/*
DI 세 가지 방법
1. 생성자 주입
2. 필드 주입(생성자 없이 필드(인스턴스 변수)에 @Autowired 쓰기) -> 좋은 방법은 아님
3. Setter 주입 -> 비권장(public으로 열어나야 함)

실무에서는 컴포넌트 스캔 주로 사용
but 정형화 되어 있지 않거나 바뀔 가능성이 있으면 Bean으로 등록함
 */