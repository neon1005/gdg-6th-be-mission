package gdg.hongik.mission.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 상품 구매 요청
public class PurchaseProductRequest {
    private Long id;
    private int quantity;
}
