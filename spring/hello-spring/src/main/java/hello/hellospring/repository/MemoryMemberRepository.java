package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // save 할 때, 어딘가 저장해야한다. 메모리니까
    // 키는 회원 아이디니까 long, 값은 member
    private static Map<Long, Member> store = new HashMap<>();
    // sequence는 0, 1, 2 일케 키 값을 형성해주는 아이.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // sequence값 올려주기
        store.put(member.getId(), member); // id, 이름 넣어주기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get(id)해서 store에서 꺼내준다.
        // null이 반환될 가능성이 있으므로. Null이어도 감싸준다.
        // 감싸서 반환을 해주면 클라이언트에서 뭘 할 수 있음.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 람다 // 루프로 돌리는거.
                // member.get(Name)이 파라미터로 넘어온 name이랑 같은지 보는거
                // 같은 경우에만 필터링 된다.
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾으면 반환된다. 없으면 optional로 감싸진 null반환
    }

    @Override
    public List<Member> findAll() {
        // store의 member가 쫙 반환된다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
