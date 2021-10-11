package com.example.demo.presentation;

import com.example.demo.application.ApplicationService;
import com.example.demo.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ApplicationService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", service.getAll());
        return "index";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add_product")
    public String submitAddProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_product";
        }

        service.save(product);
        return "redirect:/";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Product product = service.getProductById(id);
        service.delete(product);
        return "redirect:/";
    }

//    public static void main(String[] args) {
//        logger.info("Start application");
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        ApplicationService service = (ApplicationService) context.getBean(ApplicationService.class);
//
//        try {
//            List<Product> allProducts = service.getAll();
//            System.out.println("Все продукты: " + allProducts);
//        } catch (Exception e) {
//            logger.error("Error: " + e);
//        }
//
//        try {
//            String name = "milk";
//            List<Product> productsByName = service.getProductsByName(name);
//            System.out.println("Все продукты с названием " + name + ": " + productsByName);
//        } catch (Exception e) {
//            logger.error("Error: " + e);
//        }
//
//        try {
//            List<Product> productsWithSameName = service.getProductsWithSameName();
//            System.out.println("Все продукты с повторяющимися именами: " + productsWithSameName);
//        } catch (Exception e) {
//            logger.error("Error: " + e);
//        }
//
//        logger.info("Stop application");
//    }
}
