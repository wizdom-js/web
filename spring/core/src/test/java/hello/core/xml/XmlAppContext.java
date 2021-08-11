package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
        // ApplicationContext가 부모니까 ApplicationContext ac = ~ 해준다.
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        // ApplicationContext에서 Bean 조회하고 꺼내기
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }

}
