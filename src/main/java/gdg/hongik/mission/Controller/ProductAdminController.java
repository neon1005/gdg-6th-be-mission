package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//UserController에서 어노테이션 설명
@RestController
@RequestMapping("/products-admin")
public class ProductAdminController {

    // 관리자: 상품 등록
    // 요청 예시:
    // {
    //   "name": "콜라",
    //   "price": 1500,
    //   "stockQuantity": 10
    // }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        for (Product savedProduct : ProductStore.products) {
            if (savedProduct.getName().equals(product.getName())) {
                throw new RuntimeException("이미 존재하는 상품명입니다.");
            }
        }
        product.setId(ProductStore.sequence++); //상품 등록시 값 증가
        ProductStore.products.add(product); // 상품 저장소에 등록

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
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
    public ResponseEntity<Product> addStock(@PathVariable Long productId, @RequestBody int quantity) {
        Product product = findProductById(productId); //ID로 제품 찾아서

        if (quantity <= 0) {
            throw new RuntimeException("추가할 재고 수량은 1개 이상이어야 합니다.");
        }

        product.setStockQuantity(product.getStockQuantity() + quantity); //재고 추가

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
    public ResponseEntity<List<Product>>  deleteProduct(@RequestBody List<Product> requestProducts) {
        for (Product requestProduct : requestProducts) { // 삭제 대상 상품들을 하나씩 꺼내서
            Product product = findProductById(requestProduct.getId()); // 대상 상품의 id로 실제 저장되어있는 상품찾음
            ProductStore.products.remove(product); //그 상품 객체를 제거한다
        }

        return ResponseEntity.ok(ProductStore.products);//현재 남아 있는 전체 상품 목록을 200ok 상태로 응답
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