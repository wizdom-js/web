package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원 객체를 저장하는 저장소
    Member save(Member member); // 회원을 저장하면 저장된 회원이 반환됨
    // 찾는 회원이 없을 때, Null을 그대로 반환하지 않고 optional로 감싸서 반환.
    Optional<Member> findById(Long id); // ID로 회원찾기
    Optional<Member> findByName(String name); // 이름으로 회원 찾기
    List<Member> findAll(); // 저장된 회원리스트 다 반환

}
