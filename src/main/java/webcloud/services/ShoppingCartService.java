package webcloud.services;

import webcloud.entity.Product;
import webcloud.exeptions.ShoppingCartServiceException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws ShoppingCartServiceException;

    BigDecimal getTotal();
}