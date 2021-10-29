package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // final 이 있는 필드의 생성자를 자동 생성 // 자동으로 생성자를 만든다 + 생정자가 하나라 @Autowired 생략 가능
public class LogDemoController {
    /**
     * 컨트롤러
     * logDemoService 와 MyLogger 를 주입받는다.
     */
    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // 오류 발생
    // private final ObjectProvider<MyLogger> myLoggerProvider; // Provider 를 사용하도록 변경

    @RequestMapping("log-demo") // "log-demo" 라는 요청이 오면 동작
    @ResponseBody // return 을 응답으로 보내겠다.
    // HttpServletRequest 자바표준서블릿 규약에 의한 http request 정보를 받을 수 있다.
    public String logDemo(HttpServletRequest request) {
        // request.getRequestURL() 에 의해 client 가 어떤 URL 로 요청을 했는지 알 수 있다.
        String requestURL = request.getRequestURL().toString();

        // Provider 를 통해 필요한 시점에 DL 을 통해 컨테이너에서 찾아서 주입받는다.
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId");
        return "OK";
    }
}
