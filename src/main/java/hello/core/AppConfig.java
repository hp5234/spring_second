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

public class AppConfig {

    // 구현객체를 생성하고 연결하는 책임을 가지는 별도의 클래스

    // 맴버서비스 생성자를 통해
    // MemberRepository 를 주입
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 리펙터링으로 인해
    // memberRepository 를 어떤것을 사용하는지 보기 쉽게
    // 따로 빼준다 (리펙터링)
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 오더서비스 생성자를 통해
    // MemberRepository 및 DiscountPolicy 를 주입
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 리펙터링으로
    // discountPolicy 를 어떤것을 사용하는지 보기 쉽게
    // 따로 빼준다 (리펙터링)
    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
