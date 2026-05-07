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

    public Long createProduct(Product product) {

        Product existingMember = productRepository.findById(product.getId);
        if (existingMember == null) {
                throw new RuntimeException("Member not found"+ request.getId);
        }

        /*Product product = new Product(
                request.getProductId(),
                request.getName(),
                request.getPrice(),
                request.getStockQty()
        ); */
        productRepository.save(product);
        return product.getId();

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        return product;

    }

    public void updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }


        product.updateInfo(request.getName(), request.getPrice(), request.getStockQty());
    }


    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("해당 상품을 찾을 수 없습니다.");
        }

        productRepository.deleteById(id);

    }
}
