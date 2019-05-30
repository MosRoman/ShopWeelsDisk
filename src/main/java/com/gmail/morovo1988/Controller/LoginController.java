package com.gmail.morovo1988.Controller;

import com.gmail.morovo1988.Entity.Product;
import com.gmail.morovo1988.Service.ProductServiceImpl;
import com.gmail.morovo1988.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class LoginController {
    static final int ITEMS_PER_PAGE = 10;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private TypeService typeService;

    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        List<Product> products = productServiceImpl
                .findAllProduct(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("products",  products);
        model.addAttribute("allPages", getPageCount());
        return "login";
    }
    private long getPageCount() {
        long totalCount = productServiceImpl.countProduct();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
