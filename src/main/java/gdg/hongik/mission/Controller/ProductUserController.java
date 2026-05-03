package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// 이 클래스가 REST API 요청을 처리하는 Controller이다.
// 각 메서드의 반환값은 HTML 화면이 아니라 JSON 응답 데이터로 반환
@RequestMapping("/products-user") // 이 Controller의 기본 URL 경로를 /products-user로 설정
public class ProductUserController {

    // 소비자: 상품명으로 상품 조회
    //GET 요청으로 쿼리 파라미터로 상품명을 받는다.
    // 예시: GET /products-user?name=콜라
    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestParam String name) { //상품명을 받아 조회
        for (Product product : ProductStore.products) { // 저장소에서 하나씩 꺼내면서 비교
            if (product.getName().equals(name)) {
                return ResponseEntity.ok(product);
            }
        }

        throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
    }

    // 소비자: 상품 구매
    // 여기서 requestProduct의 stockQuantity는 "현재 재고"가 아니라 "구매 수량"으로 해석
    // 요청 예시:
    //
    //   {
    //     "id": 1,
    //     "stockQuantity": 2
    //   }
    //
    @PatchMapping
    public ResponseEntity<List<Product>> purchaseProduct(@RequestBody List<Product> requestProducts) {
        List<Product> purchasedProducts = new ArrayList<>(); // 구입한 상품 정보 저장

        for (Product requestProduct : requestProducts) { // 구입한 상품리스트에서 하나씩 꺼내서
            Product product = findProductById(requestProduct.getId());

            int requestQuantity = requestProduct.getStockQuantity();// 상품당 구매 수량

            if (requestQuantity <= 0) {
                throw new RuntimeException("구매 수량은 1개 이상이어야 합니다.");
            }
            if (product.getStockQuantity() < requestQuantity) {
                throw new RuntimeException("재고가 부족합니다.");
            }
            
            product.setStockQuantity(product.getStockQuantity() - requestQuantity); //구매 수량만큼 감소

            Product purchasedProduct = new Product();

            purchasedProduct.setId(product.getId());
            purchasedProduct.setName(product.getName());
            purchasedProduct.setPrice(product.getPrice() * requestQuantity); //구입한 상품의 총 금액 계산
            purchasedProduct.setStockQuantity(requestQuantity);

            purchasedProducts.add(purchasedProduct);
        }

        return ResponseEntity.ok(purchasedProducts);
    }

    private Product findProductById(Long productId) {
        for (Product product : ProductStore.products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }

        throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
    }
}