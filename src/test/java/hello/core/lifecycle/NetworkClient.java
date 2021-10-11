package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    // 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // connect();
        // call("초기화 연결 메시지");
    }

    // 세터 url
    public void setUrl(String url) {
        this.url = url;
    }

    // 연결
    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 연결이 된 상태에서 호출하는 call
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // afterPropertiesSet() 과 같은 역할
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");

        connect();
        call("초기화 연결 메시지");
    }

    // destroy() 와 같은 역할
    @PreDestroy
    public void close()  {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    /*

    // InitializingBean 를 상속받음으로 사용하는 메서드
    // 의존관계 주입이 끝나면 호출해주겠다는 메서드 afterPropertiesSet()
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        // NetworkClient 생성자에서 진행했던 부분을
        // 콜백함수로 옮겨온다.
        connect();
        call("초기화 연결 메시지");
    }

    // DisposableBean 를 상속 받음으로 사용하는 메서드
    // Bean 이 종료될때 호출되는 매서드 destroy()
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    */
}
