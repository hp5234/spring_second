package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    /*
    * AppConfig 에서
    * 즉 스프링이 싱글톤을 보장하는것을 확인하기 위한 테스트
    * */

    @Test
    void configurationTest() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        // 직접 꺼내본다.
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        // 각각의 구현체에서 생성된 MemberRepository 를 반환
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        // 확인
        // 두 대상이 같은 결과를 반환한다는것을 확인할 수 있다 -> 스프링이 싱글톤을 보장
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);

        System.out.println("memberRepository = " + memberRepository);

        /*
            세 대상이 모두 같은 대상임을 알 수 있다. -> 싱글톤
         */

        // 테스트화
        Assertions.assertThat(memberRepository1).isEqualTo(memberRepository);
        Assertions.assertThat(memberRepository2).isEqualTo(memberRepository);
    }

    @Test
    void deepConfiguration() {

        /*
         * 스프링컨테이너에 AppConfig 가 등록되어
         * 어떻게 싱글톤을 보장하는지 확인하기 위한
         * 테스트
         */

        // AppConfig 도 스프링 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // 출력결과
        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$68616a59
        // >>> 순수한 class 라면 class hello.core.AppConfig 와 같이 출력되어야 한다.


    }

}
