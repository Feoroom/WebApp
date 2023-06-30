package webcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webcloud.exeptions.ShoppingCartServiceException;
import webcloud.services.ProductService;
import webcloud.services.ShoppingCartService;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class ShoppingCartController {
    private ShoppingCartService cartService;
    private ProductService productService;

    @GetMapping
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("products", cartService.getProductsInCart());
        modelAndView.addObject("total", cartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(cartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(cartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        try {
            cartService.checkout();
        } catch (ShoppingCartServiceException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
