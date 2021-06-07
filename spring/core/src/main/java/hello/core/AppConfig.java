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

    @Bean // 입력하면 귤색들이 스프링 컨테이너에 등록된다.
    // 생성자 주입 : 생성자를 통해서 객체가 new 인스턴스 생성된게 들어간다
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
   public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
