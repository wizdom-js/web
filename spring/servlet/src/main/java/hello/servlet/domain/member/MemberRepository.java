package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // 싱글톤이기 때문에 static 없어두 된다. 하나인게 보장되니까 (일단은 그냥 해놓음)
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id 하나씩 증

    // 싱글톤으로 만들기
    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤 만들때는 private으로 생성자 막기 (아무나 생성하지 못하게)
    // 무조건 getInstance로 조회해야함
    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store에 있는 모든 값들을 다 꺼내서 새로운 arraylist에 담아 넘겨준다.
        // 왜 이렇게 하냐면 new arraylist를 값을 넣거나 밖에서 조작해도
        // store에 있는 value list를 건들고 싶지 않기 때문
        // 물론 이렇게 해도 store에 있는 member를 직접 가져와서 안에 있는 값 수정하면 수정된다.
        // 이건 store 자체를 보호하기 위함
        return new ArrayList<>(store.values());
    }

    // 이건 테스트같은 곳에서 주로 쓰이는데 store를 날려버리는 것.
    public void clearStore() {
        store.clear();
    }
}
