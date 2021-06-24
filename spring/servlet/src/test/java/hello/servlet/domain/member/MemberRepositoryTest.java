package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트 끝날때마다 테스트 초기화 하기 위함
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given 이런게 주어졌을때
        Member member = new Member("hello", 20);

        // when 이런게 수행했을때
        Member saveMember = memberRepository.save(member);

        // then 결과가 이거여야해
        // 저장했던 멤버에서 id 꺼내서 찾은다음에
        Member findMember = memberRepository.findById(saveMember.getId());
        // 찾아온 멤버랑 저장한 멤버랑 같은지 보기
        assertThat(findMember).isEqualTo(saveMember);
    }

    // 모든걸 조회하는 테스트
    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        // result 안에 member1번과 member2번이 있는가 확인
        assertThat(result).contains(member1, member2);
    }
}
