package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;
import lombok.Getter;



@Getter
// 상품 정보 응답
public class ProductResponse {
    private final Long id;
    private final String name;
    private final double price;
    private final int quantity;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

}
