package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminService {
    private final ProductRepository productRepository;

    // 관리자: 상품 등록
    public Product createProduct(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());

        if (existingProduct != null) {
            throw new RuntimeException("이미 존재하는 상품명입니다.");
        }

        productRepository.save(product);

        return product;
    }

    // 관리자: 전체 상품 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 관리자: 상품 ID로 상품 조회
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        return product;
    }

    // 관리자: 재고 추가
    public Product addStock(Long id, int quantity) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        if (quantity <= 0) {
            throw new RuntimeException("추가할 재고 수량은 1개 이상이어야 합니다.");
        }

        product.setQuantity(product.getQuantity() + quantity);

        return product;
    }

    // 관리자: 상품 삭제
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        productRepository.deleteById(id);
    }
}
