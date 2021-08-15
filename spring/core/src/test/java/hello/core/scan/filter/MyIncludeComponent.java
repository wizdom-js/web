package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) // 중요 // 어디에 붙는지. TYPE이면 클래스 레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
