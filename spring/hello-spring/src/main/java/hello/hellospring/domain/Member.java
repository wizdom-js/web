package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 entity이다. 명시
// 데이터베이스와 매핑해놓는 거임. 이 정보를 가지고 insert, select문 등등 다 만든다. 이렇게 jpa가 동작한다 1!!
public class Member {

    // Pk를 알려줘야한다.
    // id가 계속 생기는데, 쿼리에 Id를 넣는게 아니라 db에 값을 넣으면 db가 id를 자동으로 생성해주는 것을 identity 전략이라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id식별자. 시스템이 정하는 id. 임의의 값.

    private String name;

    // getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
