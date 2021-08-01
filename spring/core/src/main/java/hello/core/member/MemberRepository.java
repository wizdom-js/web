package hello.core.member;

public interface MemberRepository {

    // 저장
    void save(Member member);

    // id로 찾는기능
    Member findById(Long memberId);
}
