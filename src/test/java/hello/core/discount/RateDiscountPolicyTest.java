package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 성공에 대한 테스트 : 할인 금액이 정확이 나오나
    @Test
    @DisplayName("VIP는 10% 활인이 적용되어야 한다.")
    void vip_o() {

        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000 );

        // then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니라면 할인이 적용되지 않아야 한다. ")
    void vip_x() {

        // given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000 );

        // then
        Assertions.assertThat(discount).isEqualTo(0);

        /*
        Assertions.assertThat(discount).isEqualTo(1000);
        기대했던것과 다르다는 에러 메세지 출력
        org.opentest4j.AssertionFailedError:
        expected: 1000
        but was : 0
         */

    }
}