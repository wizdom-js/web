package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) // 중요 // 어디에 붙는지. TYPE이면 클래스 레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 컴포넌트 스캔 대상에 추가할 애노테이션
public @interface MyIncludeComponent {
}
