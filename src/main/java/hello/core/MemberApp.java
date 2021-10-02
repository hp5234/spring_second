package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // 만들어진 회원도메인을 테스트
        // 테스트프레임워크를 사용하지 않고 테스트를 구현

        // 스프링으로 변경
        // 해당코드로 인해 ApplicationContext 컨테이너에 AppConfig 와 @Bean 이 붙은
        // 메서드들을 전부 등록하여 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // getBean()을 통해 메스드를 호출
        // 메서드는 메서드 이름으로 등록된다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        // AppConfig 추가 이후 변경
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // AppConfig 추가 이전
        // 직접 생성한 모습
        // MemberService memberService = new MemberServiceImpl();

        // member 생성
        Member member = new Member(1L, "memberA", Grade.VIP);

        // member 저장
        memberService.join( member );

        // 저장된 member를 memberId로 검색
        Member findMember = memberService.findMember(1L);

        // 검색 결과 비교
        System.out.println("new member = " + member.getName()); // 저장 전 맴버
        System.out.println("findMember = " + findMember.getName()); // 저장 후 검색한 맴버

        /**
         * 현재까지 (자바코드로만 작성된 ) 회원 도메인 설계의 문제점
         * 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요? : 개방- 폐쇄 원칙
         * DIP를 잘 지키고 있을까요? : 의존관계 역전의 원칙
         * 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음 >>> DIP 를 위반하고 있다.
         */
    }
}
