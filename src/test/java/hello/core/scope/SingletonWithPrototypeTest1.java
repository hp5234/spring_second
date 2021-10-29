package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
    /*
        싱글톤 과 프로토타입을 함께 사용하면 발생하는
        문제점을 확인하기 위한 테스트
     */

    @Test
    void prototypeFind() {
        /*
            프로토타입 빈을 각각 생성해서
            addCount() 를 실행하면 각각 다른 객체의
            addCount() 가 실행되는것을 확인
         */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        /*
            싱글톤 빈에서 프로토타입 빈을 주입한 경우를 테스트
            -> 주입 시에만 프로타입을 생성해 주입하므로
            매번 호출 시 프로토타입을 새로 생성하는것이 아닌
            이후에는 싱글톤이 호출되어 이미 주입받은 프로토타입 빈이 사용된다.(싱글톤 처럼 동작 )
         */

        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        // 테스트
        Assertions.assertThat(count1). isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        // 테스트
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        // 싱글톤 빈에 프로토타입 빈을 주입한 경우

        // private final PrototypeBean prototypeBean; // 생성 시점에 주입

        @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        // javax.inject 의 Provider 사용
        private Provider<PrototypeBean> prototypeBeanProvider;

        /*
        // 생성자ㅣ
        // 이때 스프링 컨테이너가 프로토타입 빈을 만들어서 주입한다.
        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }
        */

        // 따라서 로직이 호출 되어도 이미 생성 시점에 만들어진 프로토타입 빈 이 대상이 된다.
        public int logic() {
            // PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount(); // inline 형태로 사용 가능 // return prototypeBean.getCount()
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
