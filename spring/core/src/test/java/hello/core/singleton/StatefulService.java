package hello.core.singleton;

// 상태 유지할 경우 발생하는 문제점 예시
public class StatefulService {

    private int price; // 상태를 유지하는 필드 (10000 -> 20000 바뀜)

    // 주문할 때 가격을 저장
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제
    }

    // 주문을 해놓고 위의 필드에 저장해놓고 꺼내온다.
    public int getPrice() {
        return price;
    }

}
