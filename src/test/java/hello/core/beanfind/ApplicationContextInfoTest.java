package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

// JUnit5 부터 public 생략 가능
// public class ApplicationContextInfoTest {
class ApplicationContextInfoTest {

    // 컨테이너를 조회하는 방법에 대한 테스트

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    // public void findAllBean()
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            Object bean = ac.getBean(beanDefinitionName);
            // 타입지정을 안했기때문에 Object 가 나옴

            System.out.println("name = " + beanDefinitionName + "  object = "  + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 출력하기")
    // 내가 등록한 Bean만 출력
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            // 빈에 대한 메타데이터 정보
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 개발자가 애플리케이션을 개발하기위해 등록한 빈을 Role 이라고 한다.
            // 또는 외부 라이브러리

            // 현재 빈이 개발자가 등록한 빈인지 비교
            // getRole() 을 통해 비교 가능
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                // Role ROLE_APPLICATION : 직접 등록한 어플리케이션 빈
                // Role ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "  object = "  + bean);
            }
        }
    }

}
