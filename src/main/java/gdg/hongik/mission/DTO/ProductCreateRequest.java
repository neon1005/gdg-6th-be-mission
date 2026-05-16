package gdg.hongik.mission.DTO;


import gdg.hongik.mission.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 상품 등록 요청, 상품 생성
public class ProductCreateRequest {
    private String name;
    private double price;
    private int quantity;

    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}
