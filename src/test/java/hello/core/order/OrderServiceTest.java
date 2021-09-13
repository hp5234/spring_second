package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    // 회원 관리와 주문 서비스
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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
