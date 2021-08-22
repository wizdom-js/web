package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // long memberId = 1L; 이렇게 primitive 타입을 써도 상관없다.
        // 근데 null을 넣을 수 없다. long memberId = null; 이게 안됨
        // 나중에 db에 넣을때 처음 객체 생성 단계에서 Null을 넣을 수도 있기 때문에 Wrapper 타입을 쓴다.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // 프린트로 검증 아니라 이걸로 ~ 그래야 편하게 테스트 만들 수 있다.
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // 필드 주입 테스트
//    @Test
//    void fieldInjectiontTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//
//        // 각 setter 열어야 사용할 수 있음
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
//
//        orderService.createOrder(1L, "itemA", 10000);
//    }

}
