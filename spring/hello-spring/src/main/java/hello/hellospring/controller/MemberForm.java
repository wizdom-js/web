package hello.hellospring.controller;

public class MemberForm {
    private String name; // 여기의 name과 home.html의 name이랑 매칭이 되면서 값이 들어온다.
    // private이기 때문에 막 접근 못한다. 밑에 get set 호출해서 가져온다.

    // 값 꺼내오기
    public String getName() {
        return name;
    }

    // 값 넣어주기
    public void setName(String name) {
        this.name = name;
    }
}
