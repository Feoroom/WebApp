package webcloud.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webcloud.entity.Product;
import webcloud.exeptions.ProductServiceException;
import webcloud.repositories.ProductRepo;
import webcloud.services.ProductService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Log4j2
public class ProductServiceImp implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Long addProduct(Product product) {

        var p = Product.builder()
                .name(product.getName())
                .shortDesc(product.getShortDesc())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
        productRepo.save(p);

        return p.getId();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(ProductServiceException::new);
    }

    @Override
    public void changeQuantity(Long productId, int quantity) {

        Product product = productRepo.findById(productId)
                .orElseThrow(ProductServiceException::new);
    }

    @Override
    public void deleteProductById(Long id) {
        if(!productRepo.existsById(id)){
            throw new ProductServiceException();
        }
        productRepo.deleteById(id);
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepo.findById(productId);
    }
}
