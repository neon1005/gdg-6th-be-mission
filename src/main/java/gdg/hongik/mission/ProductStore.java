package gdg.hongik.mission;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
// 원래는 DB에 접근하는 저장소 역할의 클래스에 붙이는 어노테이션
// "상품 데이터를 저장하는 역할"을 하므로 Repository로 표시
public class ProductStore {
    public static List<Product> products = new ArrayList<>(); //상품 저장소, static 사용으로 ProductStore.products로 접근가능
    public static Long sequence = 1L; // 상품 id 등록을 위함 상품을 새로 등록할때마다 sequence값을 id로 넣고 sequence++ 으로 다음 id로 증가시킴
}
