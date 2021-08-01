package hello.core.order;

public class Order {

    private Long memberId;
    private String itemName;
    private int itemPrice; // item가격
    private int discountPrice; // 할인 가격

    // 주문에서 할인 다 끝나고 만들어지는 객체
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    // 계산 로직. 비즈니스 로직. 최종 계산된 금액
    public int calcuratePrice() {
        return itemPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    // 객체를 출력할때 나오는데 보기 쉽게 하기 위함
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';

            // order 객체 자체가 출력되는데 거기 있는 tostring이 출력된다.
        // 근데 편하게 보기 위해 위에껄 사용하는 거임 !!
//        System.out.println("order = " + order);
    }
}
