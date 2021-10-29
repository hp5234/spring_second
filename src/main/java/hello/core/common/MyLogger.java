package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
// @Scope(value = "request")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 인자로 여러개 들어갈때의 형태 // proxyMode 지정
public class MyLogger {

    private String uuid;
    private String requestURL;

    // requestURL 는 중간에 값을 받도록 설정
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    // 로그메세지 출력
    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct // 의존관계 주입이 끝나면 호출
    public void init(){
        uuid = UUID.randomUUID().toString(); // uuid 값을 생성 // UUID.randomUUID() 랜덤값
        System.out.println("[" + uuid + "] request scope bean create:" + this);
        // requestURL 은 생성 시점에 알 수 없다.
        // 따라서 requestURL 는 외부의 setter 를 통해 입력받는다.
    }

    @PreDestroy // 요청이 빠져나가 종료 될 시점에 호출
    public void close(){

        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }

}
