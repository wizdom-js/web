package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 적어줘야 AOP쓸 수 있다.
@Component // 스프링빈에 등록. 이렇게 컴포넌트 적어줘도 되는데 스프링빈에 직접 등록하는것이 좋다.
public class TimeTraceAop {

    // 공통 관심사항을 어디에 적용할지 타겟팅
    // 원하는 조건 다 적용할 수 있다.
    // 패키지 하위에 있는거 다 적용해 (* hello.hellospring..*(..))
    @Around("execution(* hello.hellospring..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // 다음 메소드 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
