package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    // MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    // @BeforeEach 에 의해
    // 테스트를 실행하기 전에 appConfig 를 생성해
    // memberService 에 할당
    // 테스트가 두개 있으면 두번 실행함
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    // junit 테스트 프레임워크를 사용
    @Test
    void join(){

        // given - 주어지는 환경
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when - 이럴 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then - 이런 결과가 나온다
        // assertj.core.api
        // Assertions.assertThat().isEqualTo()
        Assertions.assertThat(member).isEqualTo(findMember);
        // 정상 실행된다면 member 와 findMember가 같다는 의미
        // 같지 않다면 오류메세지가 뜨면서 실패
    }
}
