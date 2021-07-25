package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너와 테스트를 함께 실행한다.
@SpringBootTest
// 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
// 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
@Transactional
class MemberServiceIntegrationTest {

    // 테스트 코드 작성할때는 편한거 해도된다. 다른곳에서 가져다 쓸거 아니기 때문에
    // 필드 기반으로 편하게 해도 된다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // 테스트는 한글로 가능
    // 테스트는 실제코드에 포함되지 않으니까
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when 뭘 검증할거냐  !
        Long saveId = memberService.join(member);

        //then 검증하기
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 테스트는 정상 플로우도 중요하지만, 예외 플로우가 훨씬 중요하다.
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}