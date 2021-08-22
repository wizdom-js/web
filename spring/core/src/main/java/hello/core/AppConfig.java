package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration // 설정 정보. 구성 정보를 담당
// 애플리케이션 전체를 설정하고 구성한다는 뜻
public class AppConfig {

    // configurationTest 를 실행시키면 아래처럼 memberRepository가 3번 실행된다고 생각한다.
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 근데 막상 실행시켜보면
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // 이렇게 나온다. memberRepository는 한번 호출되는 것이다.
    // --> 스프링은 싱글톤을 보장해주는구나 ~~~!

    @Bean // 입력하면 귤색들이 스프링 컨테이너에 등록된다.
    // 생성자 주입 : 생성자를 통해서 객체가 new 인스턴스 생성된게 들어간다
    public MemberService memberService() {
        // 출력해보기
        System.out.println("call AppConfig.memberService");
        // 1번
        return new MemberServiceImpl(memberRepository());
    }

    @Bean // method extract 이렇게 하면 메소드명을 보는 순간 역할이 다 드러난다. (장점)
   public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // 필드 주입 할 때 (돌려볼 때) 위의 return 주석 처리하고 밑의 return 풀어서 돌린다.
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
