package gdg.hongik.mission;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductStore {
    public static List<Product> products = new ArrayList<>();
    public static Long sequence = 1L;
}
