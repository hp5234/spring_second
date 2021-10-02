package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    // 스프링 빈을 조회하는 방법에 대한 테스트

    // getBean 을 이용하여 조회
    // getBean( 이름, 타입 )
    // getBean( 타입 )

    // 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    // 성공테스트
    @Test
    @DisplayName("빈 이름으로 조회 ")
    void findBeanName() {

        // 이름과 타입으로 조회
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // static import 로 변경
        // memberService 가 MemberServiceImpl 의 인스턴스 이면 테스트 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회 ")
    void findBeanByType() {

        // 이름을 명시하지 않고 타입으로만 조회
        MemberService memberService = ac.getBean(MemberService.class);

        // Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // static import 로 변경
        // memberService 가 MemberServiceImpl 의 인스턴스 이면 테스트 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("인터페이스가 아닌 구체 타입으로 조회")
    void findBeanName2() {

        // 구체 타이으로 조회는 가능하다.
        // 단 구체클래스를 명시하는것은 좋지 않은 행동

        // 이름과 타입으로 조회
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        // Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // static import 로 변경
        // memberService 가 MemberServiceImpl 의 인스턴스 이면 테스트 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    // 실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // 없는 값을 조회 시도
        // MemberService xxxx = ac.getBean("xxxx", MemberService.class);
        // NoSuchBeanDefinitionException 에러 발생

        // 테스트로 구현
        // org.junit.jupiter.api.Assertions.assertThrows()
        // 2번째인자(람다)를 실행했을때 첫번째 인자(해당 예외)가 터져야 테스트 성공
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }

}
