package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 예제를 안전하게 유지하기 위한 자동 스캔 제외 항목 등록
        // 다른 예제에서 진행했던 Configuration 이 붙은 요소들이 등록되는것을 방지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppconfig {
    // @ComponentScan 은 @Component 애노테이션이 붙은 클래스를 찾아 자동으로 스프링 빈으로 등록해준다.

    /**
     *  컴포넌트 스캔을 통한
     *  컴포넌트 자동화를 구현하기 위한
     *  AppConfig
     */
}
