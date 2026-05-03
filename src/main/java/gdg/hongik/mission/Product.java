package gdg.hongik.mission;

import lombok.Getter;
import lombok.Setter;

@Getter //Product 클래스의 모든 필드에 대해 getter 메서드를 자동 생성한다(getId() ...)
@Setter //Product 클래스의 모든 필드에 대해 setter 메서도를 자동 생성한다(setId() ...)
public class Product {

    private Long id;
    private String name;
    private double price;
    private int stockQuantity; //get,setStockQuantity 대문자S로 자동 변환, 자바 빈즈 네이밍 규칙

}
