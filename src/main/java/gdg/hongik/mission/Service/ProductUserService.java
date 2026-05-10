package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUserService {
    private final ProductRepository productRepository;

    // 소비자: 상품명으로 상품 조회
    public Product getProductByName(String name) {
        Product product = productRepository.findByName(name);

        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        return product;
    }

    // 소비자: 상품 구매
    public List<Product> purchaseProducts(List<Product> requestProducts) {
        List<Product> purchasedProducts = new ArrayList<>();

        for (Product requestProduct : requestProducts) {
            Product product = productRepository.findById(requestProduct.getId());

            if (product == null) {
                throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
            }

            int requestQuantity = requestProduct.getQuantity();

            if (requestQuantity <= 0) {
                throw new RuntimeException("구매 수량은 1개 이상이어야 합니다.");
            }

            if (product.getQuantity() < requestQuantity) {
                throw new RuntimeException("재고가 부족합니다.");
            }

            product.setQuantity(product.getQuantity() - requestQuantity);

            Product purchasedProduct = new Product();
            purchasedProduct.setId(product.getId());
            purchasedProduct.setName(product.getName());

            // price에는 "해당 상품의 총 구매 금액"을 담는다.
            purchasedProduct.setPrice(product.getPrice() * requestQuantity);

            // quantity에는 "구매 수량"을 담는다.
            purchasedProduct.setQuantity(requestQuantity);

            purchasedProducts.add(purchasedProduct);
        }

        return purchasedProducts;
    }
}
