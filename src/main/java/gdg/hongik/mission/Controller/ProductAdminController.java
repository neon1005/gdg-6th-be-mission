package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // 여기서 requestProduct의 stockQuantity는 "추가할 수량"으로 해석한다.
    //
    // 요청 예시:
    // {
    //   "id": 1,
    //   "stockQuantity": 5
    // }
    @PatchMapping("/stock")
    public ResponseEntity<Product> addStock(@RequestBody Product requestProduct) {
        Product product = findProductById(requestProduct.getId());
        int quantity = requestProduct.getStockQuantity();

        if (quantity <= 0) {
            throw new RuntimeException("추가할 재고 수량은 1개 이상이어야 합니다.");
        }

        product.setStockQuantity(product.getStockQuantity() + quantity);

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
    @DeleteMapping
    public ResponseEntity<List<Product>>  deleteProduct(@RequestBody List<Product> requestProducts) {
        for (Product requestProduct : requestProducts) {
            Product product = findProductById(requestProduct.getId());
            ProductStore.products.remove(product);
        }

        return ResponseEntity.ok(ProductStore.products);
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