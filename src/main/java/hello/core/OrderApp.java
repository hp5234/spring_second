package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        // 만들어진 주문 도메인을 테스트
        // 테스트프레임워크를 사용하지 않고 테스트를 구현

        // 회원관리와 주문정책
        // AppConfig 생성 이후 변경된 코드
        // appConfig 를 통해 생성
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        // AppConfig 생성 이전 코드
        // 직접 생성한 모습
        // 회원관리와 주문정책
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        // 테스트할 회원 정보 생성 및 저장
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 정보 생성
        Order order = orderService.createOrder(memberId, "memberA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
