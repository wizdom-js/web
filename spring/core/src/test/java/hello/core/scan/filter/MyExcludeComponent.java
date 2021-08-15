package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) // 중요 // 어디에 붙는지. TYPE이면 클래스 레벨에 붙는다. 
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 이 아이가 붙은건 컴포넌트 스캔에서 제외
public @interface MyExcludeComponent {
}
