package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    // @BeforeEach 에 의해
    // 테스트를 실행하기 전에 appConfig 를 생성해
    // memberService 와 orderService 에 할당
    // 테스트가 두개 있으면 두번 실행함
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    // 회원 관리와 주문 서비스
    // MemberService memberService = new MemberServiceImpl();
    // OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // org.assertj.core.api.Assertions
        // 회원 등급이 Grade.VIP 이므로 고정 할인금액이 1000 이 나오는지 검사
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
