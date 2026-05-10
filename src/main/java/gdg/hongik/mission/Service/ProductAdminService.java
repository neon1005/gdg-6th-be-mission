package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List; //사용되지 않음

@Service
@RequiredArgsConstructor //final 필드를 매개변수로 받는 생성자를 lombok이 자동으로 만들어준다
public class ProductAdminService {
    private final ProductRepository productRepository; //생성자 주입, @RequiredArgsConstructor 해도 필수 선언

    public Product createProduct(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());

        if (existingProduct != null) {
            throw new RuntimeException("이미 존재하는 상품입니다.");
        }

        productRepository.save(product);

        return product;
    }
    /*
    // 사용되지 않음
    // 관리자: 전체 상품 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // 사용되지 않음
    // 관리자: 상품 ID로 상품 조회
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        return product;
    }
    */




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
