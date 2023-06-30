package webcloud.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webcloud.entity.Product;

import java.util.Optional;

public interface ProductService {

    Long addProduct(Product product);
    Product getProductById(Long id);
    void changeQuantity(Long productId, int quantity);
    void deleteProductById(Long id);

    Page<Product> findAllProducts(Pageable pageable);

    Optional<Product> findById(Long productId);
}
