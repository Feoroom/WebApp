package webcloud.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webcloud.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepo
        extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

}
