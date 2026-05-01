package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductUserController {

    // 소비자: 상품명으로 상품 조회
    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestParam String name) {
        for (Product product : ProductStore.products) {
            if (product.getName().equals(name)) {
                return ResponseEntity.ok(product);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다.");
    }

    // 소비자: 상품 구매
    @PostMapping("/{productId}/purchase")
    public ResponseEntity<Product> purchaseProduct(@PathVariable Long productId) {
        Product product = findProductById(productId);

        if (product.getStockQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");
        }

        product.setStockQuantity(product.getStockQuantity() - 1);

        return ResponseEntity.ok(product);
    }

    private Product findProductById(Long productId) {
        for (Product product : ProductStore.products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다.");
    }
}