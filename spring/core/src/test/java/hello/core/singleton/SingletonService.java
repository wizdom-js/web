package hello.core.singleton;

// tc 케이스 아니고 순수한 어플리케이션에 영향 안주고 테스트 하기 위함.
public class SingletonService {

    // 자기자신을 내부에 private으로 하나 static으로 가지고 있다.
    // 이렇게 되면 class 레벨에 올라가기 때문에 딱 하나만 존재하게 된다.
    // static이라고 되어있으면 static 영역에 딱 이거 하나만 만들어져서 올라간다.
    // 아래 코드를 실행하면 자기 자신을 생성해서 instance에 참조로 넣어놓는다.
    private static final SingletonService instance = new SingletonService();

    // 조회 (인스턴스의 참조를 꺼낸다.)
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성을 막는다.
    // 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
