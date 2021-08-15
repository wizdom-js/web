package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 주문 결과 반환해주는거
@Component
public class OrderServiceImpl implements OrderService{

    // final은 기본으로 할당하든 또는 생성자로 할당 해야함
    private final MemberRepository memberRepository; // 회원찾아야하니까
    private final DiscountPolicy discountPolicy; // 할인 해줘야하니까 필요

    @Autowired
    // 생성자
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 주문생성 요청이오면 1. 회원 조회 2. 할인 정책에 회원 넘겨서 할인금액 받고
    // 3. 최종 생성된 주문을 반환한다.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 단일 체계 원칙 잘 지킨거. 설계 잘된거
        // orderservice 입장에서는 할인 모르겠고 니가 할인해서 결과만 나한테 줘
        // 할인 변경 필요하면 할인 그쪽만 고치면 되니까 orderservice 안고쳐도 된다 ! !
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 깨지는지 안깨지는지 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
