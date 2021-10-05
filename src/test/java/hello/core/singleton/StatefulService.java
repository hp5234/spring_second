package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    // price 의 값을 변경하는 메서드
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
    }

    // price 값을 반환
    public int getPrice() {
        return price;
    }
}
