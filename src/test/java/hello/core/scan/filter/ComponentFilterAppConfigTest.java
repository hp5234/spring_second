package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    // 애노테이션 include 와 exclude 에 따른 결과 확인을 위한 테스트
    // filter test

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // 테스트

        // include 한 BeanA 를 테스트
        // getBean 으로 이름 조회시 첫글자 소문자 주의
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        // exclude 로 인해 BeanB 조회 시도 시 에러 발생
        //BeanB beanB = ac.getBean("beanB", BeanB.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class , () -> ac.getBean("beanB", BeanB.class)
        );
    }


    // 테스트에서 사용할 AppConfig
    // AppConfig 에서 필터를 설정하는 모습
    // type = FilterType.ANNOTATION 는 기본값이므로 생략이 가능하다.
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                    MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                    MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
