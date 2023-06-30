package webcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import webcloud.entity.Product;
import webcloud.services.impl.ProductServiceImp;
import webcloud.utils.Pager;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsPageController {
    private final ProductServiceImp productService;
    private final static int INITIAL_PAGE = 0;

    @GetMapping
    public ModelAndView productsPage(
            @RequestParam("page")Optional<Integer> page
            ){
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAllProducts(PageRequest
                .of(evalPage, 5));
        Pager pager = new Pager(products);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/products");

        return modelAndView;
    }
}
