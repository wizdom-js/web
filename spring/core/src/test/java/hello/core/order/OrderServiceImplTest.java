package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 회원가입
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        // 수정자 메서드 사용하게 되면 createOrder만 테스트하고 싶어도 가짜 memorymemberrepository라도 넣어줘야한다.(임의의 dummy)
        // 수정자 메서들르 두면 의존 관계가 안보인다.
        // 하지만 생성자 주입으로 주면 new OrderServiceImpl()에 컴파일 오류가 발생한다.
        // 그럼 바로 어떤 값이 들어가야 하는지 바로 인지할 수 있음 (임의의 값 또는 목 넣어줄 수 있다)
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        // 검증
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
