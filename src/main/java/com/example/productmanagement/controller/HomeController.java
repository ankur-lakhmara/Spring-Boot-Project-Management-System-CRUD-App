package com.example.productmanagement.controller;

import com.example.productmanagement.entity.Products;
import com.example.productmanagement.repository.ProductRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
        @Autowired
    private ProductRepository productRepo;
@GetMapping("/")
    public String home(Model m){
    List<Products> list = productRepo.findAll();
    m.addAttribute("all_products", list);
        return "index";
    }
    @GetMapping("/load_form")
    public String loadform(){
    return "add";
    }
    @GetMapping("/edit_form/{id}")
    public String editform(@PathVariable(value = "id") long id, Model m){
    Optional<Products> product = productRepo.findById(id);
    Products pro = product.get();
    m.addAttribute("product", pro);
    return "edit";
    }
    @PostMapping("/save_products")
    public String saveProducts(@ModelAttribute Products products, HttpSession session){
     productRepo.save(products);
     session.setAttribute("msg","product added successfully...");
    return "redirect:/load_form";
    }

    @PostMapping("/update_products")
    public String updateProduct(@ModelAttribute Products products, HttpSession session){
    productRepo.save(products);
    session.setAttribute("msg","product updated Successfully...");
    return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id")long id, HttpSession session){
    productRepo.deleteById(id);
    session.setAttribute("msg","product deleted successfully");
    return "redirect:/";
    }

}
