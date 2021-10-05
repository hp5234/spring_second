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

@Configuration
public class AppConfig {

    // 호출 결과 추측 (순서는 보장하지 않는다.)
    // # 1
    // call AppConfig.memberService
    // call AppConfig.memberRepository

    // # 2
    // call AppConfig.memberRepository

    // # 3
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // >>> 결과적으로 스프링 컨테이너가 등록될 때
    // AppConfig.memberRepository 가 3번이 호출된다고 생각할 수 있다.
    // 테스트에서 생성을 통해 확인
    // >>> AppConfig.memberRepository 는 1번만 호출되는것을 확인할 수 있다 >>> 스프링이 싱글톤을 보장

    // ______________________________________________________________
    // 구현객체를 생성하고 연결하는 책임을 가지는 별도의 클래스

    // 맴버서비스 생성자를 통해
    // MemberRepository 를 주입
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService !!!");
        return new MemberServiceImpl(memberRepository());
    }

    // 리펙터링으로 인해
    // memberRepository 를 어떤것을 사용하는지 보기 쉽게
    // 따로 빼준다 (리펙터링)
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository !!!");
        return new MemoryMemberRepository();
    }

    // 오더서비스 생성자를 통해
    // MemberRepository 및 DiscountPolicy 를 주입
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService !!!");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 리펙터링으로
    // discountPolicy 를 어떤것을 사용하는지 보기 쉽게
    // 따로 빼준다 (리펙터링)
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }


    // 싱글톤에서의 의문
    /*
        @Bean memberService -> return new MemoryMemberRepository();
        @Bean orderService -> return new MemoryMemberRepository();

        이러면 MemoryMemberRepository 가 2번 생성되게 되어 실글톤이 깨지는것이 아닌가?
        결과적으로 각각 다른 2개의 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것 처럼 보인다.
        스프링 컨테이너는 이 문제를 어떻게 해결할까?
        >>>
     */
}
