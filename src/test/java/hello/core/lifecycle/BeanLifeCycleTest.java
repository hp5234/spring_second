package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    /*
        Bean 생명주기를 확인하기 위한 테스트

        - 해당 테스트는 객체를 생성할 때는 정보가 없고
        객체가 생성된 이후에 정보가 들어와 초기화 하여 사용할 수 있다.
     */

    @Test
    public void lifeCycleTest () {
        // 닫는 메서드 close() 를 사용하기위해
        // ConfigurableApplicationContext 를 사용
        // ConfigurableApplicationContext 가 AnnotationConfigApplicationContext 의 상위 인터페이스
        // 이므로 담을 수 있다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    /*
        객체를 생성하는 단계에는 url 이 없고,
        객체를 생성한 다음에 외부에서 수정자 주 입을 통해서 setUrl() 이 호출되어야 url 이 존재하게 된다.
     */

    @Configuration
    static class LifeCycleConfig {

        // @Bean(initMethod, destroyMethod) 를 통해 콜백메서드를 정의
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }


}
