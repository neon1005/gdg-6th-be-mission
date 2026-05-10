package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductAdminService;
import gdg.hongik.mission.Service.ProductUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//UserController에서 어노테이션 설명
@RestController
@RequestMapping("/admin/prsoducts")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductUserService productUserService;
    @Getter
    @Setter
    static class AddStockRequest {
        private int addQuantity;
    }

    private final ProductAdminService productAdminService;

    // 관리자: 상품 등록
    // 요청 예시:
    // {
    //   "name": "콜라",
    //   "price": 1500,
    //   "stockQuantity": 10
    // }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productAdminService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // 관리자: 재고 추가
    // 특정 상품 ID에 해당하는 상품의 재고를 추가한다.
    // URL의 {productId}로 상품을 찾고,
    // RequestBody의 숫자를 추가할 재고 수량으로 사용한다.
    //
    // 요청 예시:
    // PATCH /product-admins/1
    // Body: 5
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> addStock(@PathVariable Long productId, @RequestBody AddStockRequest request) {
        Product product = productAdminService.addStock(productId, request.getAddQuantity());

        return ResponseEntity.ok(product);
    }

    // 관리자: 상품 삭제
    // 요청 예시:
    // [
    //   {
    //     "id": 1
    //   },
    //   {
    //     "id": 2
    //   }
    // ]
    //여러 상품을 한번에 삭제하기 위해 삭제할 상품 정보를 List<Product> 형태로 받는다.
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productAdminService.deleteProduct(productId);

        return ResponseEntity.ok("상품이 삭제되었습니다.");
    }

}