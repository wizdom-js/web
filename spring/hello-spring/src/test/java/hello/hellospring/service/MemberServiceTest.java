package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // new로 다른 객체 repository생성되면 혹시라도 다른 인스턴스이기 떄문에 내용 달라지거나 할 수 있다.
        // 같은 Repository로 테스트 하는 게 맞는건데, 다른 repository를 이용하고 있는 것임

        // 따라서 같은 인스턴스 쓰도록 만들어야한다.
        // 외부에서 넣어주도록 바꿔주기 ! 직접 new해서 생성하는 것이 아니고 !!!
        // @BeforeEach로 동작하기 전에 넣어준다.

        // 이렇게 하면 테스트 하기 전에 각각 생성해준다.
        // memorymemberrepository를 만들고 위에 넣어놓고 memberservice에 넣어준다. (서비스파일에 있는거)
        // 그럼 같은 memorymemberrepository를 사용하게 되는 것

        // 이렇게하면 memberservice입장에서 직접 new하지 않고 외부에서 memberrepository를 넣어준다.
        // 이런것을 DI라고 한다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 끝나고나면 db값 날리기
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    // 테스트는 한글로 가능
    // 테스트는 실제코드에 포함되지 않으니까

    // 반쪽짜리 테스트. 저장되는 것도 중요하지만 예외도 중요하다(아래테스트참고)
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when 뭘 검증할거냐 !
        Long saveId = memberService.join(member);

        //then 검증하기
        // 위에서 저장한게 repository에 있는게 맞니 ?
        // 그럼 repository에서 꺼내야한다.
        Member findMember = memberService.findOne(saveId).get();
        // 멤버의 이름이 findmember의 이름과 같냐
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 테스트는 정상 플로우도 중요하지만, 예외 플로우가 훨씬 중요하다.
    // 중복회원예약 케이스 짜서 터트려봐야함.
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 아래 try catch보다 좋은 문법
        // () -> 다음의 로직을 실행할건데, illegalstateexception.class 이 예외가 터져야해
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // try catch 하는 방법 있다. 하지만 애매하다.
        /*try {
            memberService.join(member2);
            fail(); // 저장되면 실패한거
        } catch (IllegalStateException e) {
            // 정상적으로 작동되는 것
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.2345");
        }*/

        // e를 받아서 이 메시지가 같은지
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}