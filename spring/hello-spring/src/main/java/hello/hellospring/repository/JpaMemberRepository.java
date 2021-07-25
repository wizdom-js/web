package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // Jpa는 entitymanager라는 것으로 모든 것을 동작한다.
    // build.gradle에 jpa 라이브러리 받아오면 스프링 부트가 자동으로 entitymanager을 생성해준다.
    // 현재 데이터베이스랑 연결해서 만들어준다. 그럼 우리는 만들어진걸 인젝션하면된다 !
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 영구 저장.
        // 이렇게만하면 jpa가 insert쿼리 다 짜서 db에 집어놓고
        // member에 setid까지 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회
        return Optional.ofNullable(member); // 값이 없을수도 있으니 optional로 감싸기
    }

    // Pk기반이 아닌 나머지는 jpql을 짜줘야한다.
    @Override
    public Optional<Member> findByName(String name) {
        // findbyname은 약간 특이하다.
        // Jpql이라는 객체 지향 쿼리 언어를 사용해야한다.
        // 거의 sql이랑 같으나,
        // 테이블 대상으로 sql을 날리는데 이건 객체를 대상으로 쿼리를 날린다. 그럼 sql로 번역된다.
        // 멤버 엔티티를 조회해 select의 대상이 엔티티자체이다.
        // Member (as) m 라는 의미
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
