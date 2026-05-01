package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
public class ProductAdminController {

    // 관리자: 상품 등록
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(ProductStore.sequence++);
        ProductStore.products.add(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // 관리자: 재고 추가
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> addStock(
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        Product product = findProductById(productId);

        if (quantity <= 0) {
            throw new RuntimeException("추가할 재고 수량은 1개 이상이어야 합니다.");
        }

        product.setStockQuantity(product.getStockQuantity() + quantity);

        return ResponseEntity.ok(product);
    }

    // 관리자: 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        Product product = findProductById(productId);

        ProductStore.products.remove(product);

        return ResponseEntity.noContent().build();
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