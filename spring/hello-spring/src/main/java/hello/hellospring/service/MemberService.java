package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    // 먼저 회원 저장소 필요
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 받지 X
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
         return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // findbyname-> optional.member니까 -> optional.member.ifpresent
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // null이 아니라 멤버(m)에 값이 있으면 아래 로직 동작
                    // optional이기 때문에 가능하다.
                    // if null이면 이렇게 코드 짜도 되지만, null일 가능성이 있으면 optional로 감싸서 반환해주고
                    // 그 덕에 ifpresent 쓸 수 있는 것.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
