package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        // TestBean.class 넣어주면 TestBean이 스프링 빈으로 등록된다. 컴포넌트 스캔 하는 것처럼 !!
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    // 임의의 테스트 클래스 만들기
    static class TestBean {

        // @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        @Autowired(required = false) // 여기 Member는 스프링 관련 빈이 아니다. 즉 스프링 컨테이너 관리되는거 없다는 뜻. 없는거 아무거나 집어넣은거임
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
