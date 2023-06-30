package webcloud.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import webcloud.entity.Product;
import webcloud.exeptions.ShoppingCartServiceException;
import webcloud.repositories.ProductRepo;
import webcloud.services.ShoppingCartService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@AllArgsConstructor
public class ShoppingCartServiceImp implements ShoppingCartService {
    private final ProductRepo productRepo;
    private Map<Product, Integer> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        if(products.containsKey(product)){
            products.replace(product, products.get(product) + 1);
        }else products.put(product, 1);
    }

    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public void checkout()
            throws ShoppingCartServiceException {
        Optional<Product> product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {

            product = productRepo.findById(entry.getKey().getId());
            if (product.get().getQuantity() < entry.getValue())
                throw new ShoppingCartServiceException();
            entry.getKey().setQuantity(product.get().getQuantity() - entry.getValue());
        }
        productRepo.saveAll(products.keySet());
        productRepo.flush();
        products.clear();
    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
