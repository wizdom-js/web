package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

// 주문 결과 반환해주는거
public class OrderServiceImpl implements OrderService{

    // final은 기본으로 할당하든 또는 생성자로 할당 해야함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 단일 체계 원칙 잘 지킨거. 설계 잘된거
        // orderservice 입장에서는 할인 모르겠고 니가 할인해서 결과만 나한테 줘
        // 할인 변경 필요하면 할인 그쪽만 고치면 되니까 orderservice 안고쳐도 된다 ! !
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
