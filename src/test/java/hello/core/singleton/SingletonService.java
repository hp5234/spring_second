package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 instance 를 미리 생성
    private static final SingletonService instance = new SingletonService();

    // 2. 조회 필요시 public 으로 열어 static 메서드 getInstance() 를 통해서만 조회가 가능하도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 해당 생성자의 private 로 인해 외부에서 new 를 통한 새로운 객체의 생성을 막는다.
    // >>> 싱글톤을 보장
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 로직 호출");
    }


    public static void main(String[] args) {

    }

}
