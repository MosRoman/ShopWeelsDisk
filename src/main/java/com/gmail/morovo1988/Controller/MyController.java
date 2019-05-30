package com.gmail.morovo1988.Controller;

import com.gmail.morovo1988.Service.BasketService;
import com.gmail.morovo1988.Service.TypeService;
import com.gmail.morovo1988.Service.UserService;
import com.gmail.morovo1988.Entity.*;
import com.gmail.morovo1988.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class MyController {
    static final int DEFAULT_PRODUCT_ID = -1;
    static final int ITEMS_PER_PAGE = 10;

    private final UserService userService;

    private final ProductServiceImpl productServiceImpl;

    private final BasketService basketService;

    private final TypeService typeService;

    @Autowired
    public MyController(UserService userService, ProductServiceImpl productServiceImpl, BasketService basketService, TypeService typeService) {
        this.userService = userService;
        this.productServiceImpl = productServiceImpl;
        this.basketService = basketService;
        this.typeService = typeService;
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        List<Product>  products = productServiceImpl
                .findAllProduct(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("products",  products);
        model.addAttribute("allPages", getPageCount());



        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "index";
    }

    @RequestMapping("/basket_page")
    public String basket(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        List<BasketOrder>  basketOrders = productServiceImpl
                .findAllOrder(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("basketOrders",  basketOrders);
        model.addAttribute("allPages", getPageCountOrder());

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "basket_page";
    }

    @RequestMapping("/basket_page2")
    public String basket2(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

        List<BasketOrder>  basketOrders =  productServiceImpl
                .findByOrder(dbUser,new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));


        model.addAttribute("basketOrders",  basketOrders);
        model.addAttribute("allPages", getPageCountOrder());

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "basket_page2";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);
        dbUser.setEmail(email);
        dbUser.setPhone(phone);

        userService.updateUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderProduct(@RequestParam(required = false) Long id) {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);


            productServiceImpl.order(dbUser,id);


        return "redirect:/";
    }
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public String deleteProduct(@RequestParam(required = false) long goods) {
//
//        productService.deleteProduct(goods);
//
//
//        return "redirect:/";
//    }
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public ResponseEntity<Void> delete(@RequestParam( required = false) long goods) {
//
//            productService.deleteProduct(goods);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         Model model) {
        if (userService.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);

        User dbUser = new User(login, passHash, UserRole.USER, email, phone);
        userService.addUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/product_add_page")
    public String productAddPage(Model model) {
        model.addAttribute("types", typeService.findAllTypes());
        return "product_add_page";
    }

    @RequestMapping("/admin")
    public String admin(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
            if (page < 0) page = 0;

            List<Product>  products = productServiceImpl
                    .findAllProduct(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

            model.addAttribute("types", typeService.findAllTypes());
            model.addAttribute("products",  products);
            model.addAttribute("users",userService.findAllUsers());
            model.addAttribute("allPages", getPageCount());

            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String login = user.getUsername();

            User dbUser = userService.getUserByLogin(login);

            model.addAttribute("login", login);
            model.addAttribute("roles", user.getAuthorities());
            model.addAttribute("email", dbUser.getEmail());
            model.addAttribute("phone", dbUser.getPhone());
        return "admin";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) Long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            productServiceImpl.deleteListProducts(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/order/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteOrder(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            productServiceImpl.deleteBasketOrder(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/product/order", method = RequestMethod.POST)
    public ResponseEntity<Void> order(@RequestParam(value = "toDelete[]", required = false) long[] basket) {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

            if (basket != null && basket.length > 0) {
              productServiceImpl.orderProducts(dbUser,basket);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping(value = "/add/order", method = RequestMethod.POST)
    public ResponseEntity<Void> orderAdd() {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);
        Order order = new Order(new Date(), dbUser);
        dbUser.addOrder(order);

        List<BasketOrder>  basketOrders =  productServiceImpl.findByOrder(dbUser);
        for (BasketOrder a: basketOrders ) {
            a.setOrder(order);
            basketService.addBasketOrder(a);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value="/product/add", method = RequestMethod.POST)
    public String productAdd(@RequestParam(value = "type") long typeId,
                             @RequestParam String brand,
                             @RequestParam double diametr,
                             @RequestParam double price )
    {

        Type type = (typeId != DEFAULT_PRODUCT_ID) ? typeService.findTypeById(typeId) : null;
        Product product = new Product(type, brand, diametr, price);
        productServiceImpl.addProduct(product);

        return "redirect:/";
    }

    @RequestMapping("/type/{id}")
    public String listGroup(
            @PathVariable(value = "id") long typeId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model)
    {
        Type type = (typeId != DEFAULT_PRODUCT_ID) ? typeService.findTypeById(typeId) : null;
        if (page < 0) page = 0;

        List<Product> products = productServiceImpl
                .findProductsByType(type, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("products", products);
        model.addAttribute("byTypePages", getPageCount(type));
        model.addAttribute("typeId", typeId);

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "index";
    }
    @RequestMapping("/admin/type/{id}")
    public String listGroupAdmin(
            @PathVariable(value = "id") long typeId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model)
    {
        Type type = (typeId != DEFAULT_PRODUCT_ID) ? typeService.findTypeById(typeId) : null;
        if (page < 0) page = 0;

        List<Product> products = productServiceImpl
                .findProductsByType(type, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("products", products);
        model.addAttribute("byTypePages", getPageCount(type));
        model.addAttribute("typeId", typeId);

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "admin";
    }
    @RequestMapping("/order")
    public String order(Model model) {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        User dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "order";
    }



    private long getPageCount() {
        long totalCount = productServiceImpl.countProduct();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
    private long getPageCountOrder() {
        long totalCount = productServiceImpl.countOrder();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCount(Type type) {
        long totalCount = typeService.countByType(type);
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
