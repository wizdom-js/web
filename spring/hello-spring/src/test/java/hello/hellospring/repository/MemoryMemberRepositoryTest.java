package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 순서에 의존해서 짜지 않기 ! 테스트 순서 보장안됨
// 모든 테스트는 순서 상관없이 다 따로 동작하도록 메소드 짜야함.
// 다른 곳에서 가져다 쓸거 아니기 떄문에 굳이 public 안해도 됨
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // @AfterEach: 콜백메서드. 한 메서드가 끝날때마다 어떤 동작을 한다.
    // 한 메서드가 끝날 때마다 repository 지워주기
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test // test 어노테이션 붙이면 이 메소드 실행 할 수 있다.
    public void save() {
        Member member = new Member();
        member.setName("spring"); // 이름 설정

        repository.save(member);

        // 검증해보기
        // 넣은거 잘 들어갔는지 확인
        // 반환타입이 optional이다. optional에서 값을 꺼낼 때는 get을 쓸 수 있다.
        // get으로 바로 꺼내는 것이 좋은 것은 아닌데 테스트 코드니까 ㄱㅊ
        Member result = repository.findById(member.getId()).get();
        // new에서 한거랑 get으로 꺼낸거랑 같으면 참
        // print쓸 수 있지만, 글자로 보고 있을 수 없으니 assertions 쓴다.
        // Assertions.assertEquals (org.junit) 쓸 수 있는데
        // 요즘은 Assertions.assertThat(org.assertj) 많이 쓴다.
        // assertthat에서 멤버가 result랑 같니 ?
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        // 회원 가입
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       Member result =  repository.findByName("spring1").get();

       assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       List<Member> result = repository.findAll();

       assertThat(result.size()).isEqualTo(2);
    }
}
