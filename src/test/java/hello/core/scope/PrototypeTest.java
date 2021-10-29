package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        // 둘이 같지 않은지 확인
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 프로토타입에서는 ac.close() 가 이루어져도  destroy() 가 호출되지 않은 것을 알 수 있다.
        // 아래처럼 직접 닫아주어야함
        // prototypeBean1.destroy()
        // prototypeBean2.destroy()

        ac.close(); //종료

    }

    @Scope("prototype")
    // @Component // AnnotationConfigApplicationContext() 에 인자로 넣어주는 행위가
    // @Component 를 명시해둔것처럼 동작하기에 생략
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy () {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
