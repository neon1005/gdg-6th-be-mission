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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private double price;
    private int quantity; //get,set Quantity 대문자Q로 자동 변환, 자바 빈즈 네이밍 규칙

    public Product(Long id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    //Getter와 Setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

}
