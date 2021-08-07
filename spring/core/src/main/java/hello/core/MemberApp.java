package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        // 멤버 서비스 만들기
//        MemberService memberService = new MemberServiceImpl();

//         appconfig 버전
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 스프링 버전
        // 스프링은 모든게 applicationcontext 부터 시작한다. 스프링 컨테이너임
        // 모든걸 관리해줌 @Bean 같은거
        // annotationconfig~ appconfig의 configuration을 관리한다.
        // 이렇게하면 appconfig의 환경설정 정보를 가지고 안에 있는 아이들을(@Bean들) 스프링 빈에다 스프링 컨테이너 객체 생성한거에다가 다 넣어주고 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 꺼낼거 이름(메소드 이름 / 나는 이 객체를 찾을거야) , 반환타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 회원 가입
        // Long 타입이기 떄문에 1L로 한다. 안붙이면 컴파일 오류남
        Member member = new Member(1L, "memberA", Grade.VIP); // 이사람 가입해본다.
        memberService.join(member);

        // 멤버 찾기
        Member findMember = memberService.findMember(1L);
        // 가입한 멤버와,  멤버 찾아서 비교해보기
        System.out.println("new member = " + member.getName() );
        System.out.println("find Member = " + findMember.getName());

    }
}
