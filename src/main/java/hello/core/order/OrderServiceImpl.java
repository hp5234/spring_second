package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // 생성자 자동 생성
// @Qualifier("mainDiscountPolicy") // 빈 추가 구분자 등록
public class OrderServiceImpl implements OrderService{

    // 저장소와 할인정책
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 기존에 있던 구현체 부분을 지워주고 생성자를 통해 주입받는다.(생성자 주입 방식)
    // 이로인해 OrderServiceImpl 는 추상화에만 의존하고 구현체는 전혀 알지 못한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    // 생성자
    // 롬복 적용에 의한 생략
    // 롬복의 @RequiredArgsConstructor 에 의해
    // final 이 붙은 필수 필드를 바탕으로
    // 생성자를 자동으로 생성해준다.
    @Autowired
    public OrderServiceImpl( MemberRepository memberRepository, DiscountPolicy discountPolicy ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    // @Qualifier 적용 생성자
    // 애노테이션 생성을 통해 생성한 애노테이션을 적용
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, /* @Qualifier("mainDiscountPolicy") */ @MainDiscountPolicy DiscountPolicy discountPolicy ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 스프링의 싱글톤보장을 테스트하기 위한 테스트용도
    // ConfigurationSingletonTest 에서 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
