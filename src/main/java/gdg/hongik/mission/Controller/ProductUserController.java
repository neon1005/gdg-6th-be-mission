package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
// 이 클래스가 REST API 요청을 처리하는 Controller이다.
// 각 메서드의 반환값은 HTML 화면이 아니라 JSON 응답 데이터로 반환
@RequestMapping("/user/products") // 이 Controller의 기본 URL 경로를 /user/products로 설정
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductUserService productUserService;



    // 소비자: 상품명으로 상품 조회
    //GET 요청으로 쿼리 파라미터로 상품명을 받는다.
    // 예시: GET /user/products?name=콜라
    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestParam String name) {
        Product product = productUserService.getProductByName(name);

        return ResponseEntity.ok(product);
    }

    // 소비자: 상품 구매
    // 여기서 requestProduct의 quantity는 "현재 재고"가 아니라 "구매 수량"으로 해석
    // 요청 예시:
    //[
    //   {
    //     "id": 1,
    //     "quantity": 2
    //   }
    //]
    @PostMapping
    public ResponseEntity<List<Product>> purchaseProduct(@RequestBody List<Product> requestProducts) {
        List<Product> purchasedProducts = productUserService.purchaseProducts(requestProducts);

        return ResponseEntity.ok(purchasedProducts);
    }

}