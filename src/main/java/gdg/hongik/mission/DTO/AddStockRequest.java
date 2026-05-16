package gdg.hongik.mission.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 재고 추가 요청
public class AddStockRequest {
    private int addQuantity;
}
