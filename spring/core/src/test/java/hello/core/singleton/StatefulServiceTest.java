package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        // 이 컨테이너는 아래의 statefulService 빈 하나만 생성해서 사용하는 것
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        // 빈 조회
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문 (A가 주문하고 조회하는 사이에 B가 끼어든상황)
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
        // 10000원을 기대했지만, 20000이 나온다.
        int price = statefulService1.getPrice(); 
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
