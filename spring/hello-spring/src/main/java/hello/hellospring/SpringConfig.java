package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;

@Configuration // 1. 스프링이 뜰때 configuration 읽고
public class SpringConfig {

    // spring data jpa가 구현체 만들어 놓은 것이 등록이 된다.
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Bean // 2. 스프링 빈에 등록하라는 의미네 ? 하고 멤버 서비스를 스프링 빈을 등록한다 !
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

//    @Bean // 3. repository도 스프링 빈에 등록한다. 그래서 멤버 서비스는 멤버 리포지토리를 사용하도록 넣어주었다.
//    public MemberRepository memberRepository() {
//
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
