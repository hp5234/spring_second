package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        // 만들어진 주문 도메인을 테스트
        // 테스트프레임워크를 사용하지 않고 테스트를 구현


        // 스프링으로 변경
        // 해당코드로 인해 ApplicationContext 컨테이너에 AppConfig 와 @Bean 이 붙은
        // 메서드들을 전부 등록하여 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // getBean()을 통해 메스드를 호출
        // 메서드는 메서드 이름으로 등록된다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);



        // 회원관리와 주문정책
        // AppConfig 생성 이후 변경된 코드
        // appConfig 를 통해 생성
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

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
