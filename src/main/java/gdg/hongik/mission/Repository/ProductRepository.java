package gdg.hongik.mission.Repository;


import gdg.hongik.mission.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// 원래는 DB에 접근하는 저장소 역할의 클래스에 붙이는 어노테이션
// "상품 데이터를 저장하는 역할"을 하므로 Repository로 표시
@Repository
public class ProductRepository {
    public static List<Product> products = new ArrayList<>();
    //상품 저장소, static 사용으로 ProductStore.products로 접근가능
    public static Long sequence = 1L;
    // 상품 id 등록을 위함 상품을 새로 등록할때마다 sequence값을 id로 넣고 sequence++ 으로 다음 id로 증가시킴

    @PersistenceContext
    private EntityManager em;

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }


    public List<Product> findAll() {
        return em.createQuery("SELECT m FROM Product m", Product.class)
                .getResultList();
    }

    public Product findByProductId(String productId) {
        List<Product> result = em.createQuery(
                        "SELECT m FROM Product m WHERE m.id = :productId", Product.class)
                .setParameter("productId", productId).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Product product) {
        em.persist(product);
    }

    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }

}