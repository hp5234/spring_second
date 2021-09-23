package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 저장소와 할인정책
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 기존에 있던 구현체 부분을 지워주고 생성자를 통해 주입받는다.(생성자 주입 방식)
    // 이로인해 OrderServiceImpl 는 추상화에만 의존하고 구현체는 전혀 알지 못한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자
    public OrderServiceImpl( MemberRepository memberRepository, DiscountPolicy discountPolicy ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
