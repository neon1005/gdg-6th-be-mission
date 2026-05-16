package gdg.hongik.mission.DTO;

import lombok.Getter;

@Getter
// 상품 구매 응답
public class PurchaseProductResponse {
    private final Long id;
    private final String name;
    private final double totalPrice;
    private final int quantity;

    public PurchaseProductResponse(Long id, String name, double totalPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }
}
