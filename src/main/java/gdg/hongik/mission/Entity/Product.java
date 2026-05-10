package gdg.hongik.mission.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


//Getter -> Product 클래스의 모든 필드에 대해 getter 메서드를 자동 생성한다(getId() ...)
//Setter -> Product 클래스의 모든 필드에 대해 setter 메서도를 자동 생성한다(setId() ...)

@Entity
@Getter
@Setter
public class Product {
    @Id //이 필드가 테이블의 기본키, DB 테이블에서 각 상품을 구분하기 위한 고유 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 그 기본기 값을 자동으로 생성
    private Long id;

    private String name;
    private double price;
    private int quantity; //get,set Quantity 대문자Q로 자동 변환, 자바 빈즈 네이밍 규칙


}
