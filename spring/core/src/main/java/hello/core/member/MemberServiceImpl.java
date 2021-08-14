package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 회원을 가입하고 찾으려면 뭐가 필요해 memberrepository 필요

    // 아래 코드는 실제 할당하는 부분이 구현체에도 의존하고 있음
    // MemberRepository에도 의존하고 MemoryMemberRepository에도 의존하고 있는 것이다.
    // 추상화에도 의존하고 구체화에도 의존하는 것이다. 나중 변경 있을때 문제가 된다. -> DIP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 이 코드는 오로지 MemberRepository라는 인터페이스만 있다. 추상화에만 의존한다!!(구현체는 X)
    private final MemberRepository memberRepository; // 4번

    // 생성자 // 2번
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 3번
    }

    // join해서 save하면 다형성에 의해 memorymemberrepository가 아니라
    // 그 안에 있는 save가 호출이 된다. 오버라이드 한거 호출된다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 깨지는지 안깨지는지 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
