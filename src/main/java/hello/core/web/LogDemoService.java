package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 자동으로 생성자를 만든다 + 생정자가 하나라 @Autowired 생략 가능
public class LogDemoService {
    /**
     * 서비스
     * MyLogger 를 주입받는다.
     */
    // private final ObjectProvider<MyLogger> myLoggerProvider; // 프록시 사용으로 인한 주석처리
    private final MyLogger myLogger;

    public void logic(String id) {
        // MyLogger myLogger = myLoggerProvider.getObject(); // 프록시 사용으로 인한 주석처리
        myLogger.log("sevice id = " + id );
    }
}
