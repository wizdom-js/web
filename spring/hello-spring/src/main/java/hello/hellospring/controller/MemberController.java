package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 어노테이션 해 놓으면 스프링 컨테이너가 스프링 뜰때 멤버 컨트롤러 객체를 생성해서 스프링에 넣어놓고 관리한다.
public class MemberController {

    // 멤버 서비스 가져다 쓰기
    // 근데 new로 가져다 쓸 수 있지만, 스프링이 관리를 하게 되면 스프링 컨테이너에 등록하고 스프링 컨테이너에서 받아쓰도록 해야한다.
    // 그런데 new로 하게 되면 문제 : 멤버 컨트롤러 말고 다른 컨트롤러에서 멤버 서비스를 가져다 쓸 수 있다.
    // Memberservice는 별 기능 없다. 여러 인스턴스 생성할 필요 없음. 하나만 생성해놓고 공용으로 쓰면 됨
    // 그래서 아래에 autowired 쓴다.
    private final MemberService memberService;

    // 스프링 컨테이너에 연결시켜줄때
    // 멈버 컨트롤러가 뜰때 생성자로 호출하는데, aurowired하면 스프링이 memberService를
    // 스프링 컨테이너에 있는 MemberService와 연결시켜준다.
    // 근데 memberservice는 순수한 자바 클래스라서 스프링이 알 수 있는 방법이 없다.
    // 그래서 Memberservice에 @Service 넣어준다.
    // repository는 @Repository 넣어준다.
    // 정형화 되어있는 패턴임.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // getmapping : url에 직접 치는거. 조회할때 주로 쓴다.
    @GetMapping("/members/new")
    public String createForm() {
        // 암것도 안하고 여기로 이동
       return "members/createMemberForm";
    }

    // createMemberForm.html에서 입력한 정보가 post방식으로 넘어온다.
    // postmapping : 데이터를 form같은 곳에 넣어서 전달할때 사용 (데이터 등록할때 주로 사용)
    // url같아도 postㅇ값기 때문에 creat 메소드가 실행된다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        // 멤버 만들기
        Member member = new Member();
        member.setName(form.getName());

        // 멤버 넘기기
        memberService.join(member);

        return "redirect:/"; //home화면으로 보내
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // 멤버 다 가져오기
        model.addAttribute("members", members); // 멤버 리스트 자체를 모델에 담아서 뷰 템플릿에 넘긴다.
        return "members/memberList";
    }
}
