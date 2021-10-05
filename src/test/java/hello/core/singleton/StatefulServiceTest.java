package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);


        // ThreadA : A 사용자가 10000원을 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B 사용자가 20000원을 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A 의 주문가격을 조회
        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);


    }

    // 테스트에서 사용하기 위한 Config 생성
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}