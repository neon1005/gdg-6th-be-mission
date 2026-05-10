package gdg.hongik.mission.Repository;


import gdg.hongik.mission.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// 원래는 DB에 접근하는 저장소 역할의 클래스에 붙이는 어노테이션
// "상품 데이터를 저장하는 역할"을 하므로 Repository로 표시
//ProductStore -> ProductRepository
@Repository
public class ProductRepository {
    public List<Product> products = new ArrayList<>();
    //상품 저장소, static 사용으로 ProductStore.products로 접근가능

    @PersistenceContext
    private EntityManager em;

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }


    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    // 상품명으로 상품 1개 조회
    public Product findByName(String name) {
        List<Product> result = em.createQuery(
                        "SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Product product) {
        em.persist(product);
    }

    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null)
            em.remove(product);

    }

}