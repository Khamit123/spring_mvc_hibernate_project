package bdapp.controllers;

import bdapp.DAO.DepartmentDAO;
import bdapp.DAO.ProductDAO;
import bdapp.DAO.StaffDAO;
import bdapp.model.Product;
import bdapp.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductDAO productDAO;



    @GetMapping("/findingProducts")
    public String findingStaffs(Model model, @ModelAttribute("product") Product product){
        model.addAttribute("names",productDAO.getNames());
        model.addAttribute("products",productDAO.getFindProduct(product));
        model.addAttribute("colors",productDAO.getColors());
        return "Product/findProduct";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateStaffGet(@PathVariable("id") int id, Model model){
        Product product=productDAO.getProduct(id);
        model.addAttribute("product",product);
        model.addAttribute("colors",productDAO.getColors());
        return "Product/updateProduct";

    }
    @PatchMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") int id,@ModelAttribute("product") @Valid Product product,BindingResult bindingResult,Model model){
        System.out.println(product.getPrice() + "Patch request update controler");
        if(bindingResult.hasErrors()) {
            model.addAttribute("colors",productDAO.getColors());
            return "Product/updateProduct";
        }
       productDAO.updateProduct(product,id);
        return "redirect:/product/findingProducts";

    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@ModelAttribute("product") Product product,@PathVariable int id,Model model){
        try{
            productDAO.deleteProduct(product);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого продукта необходимо:"," 1.Удалить его из таблицы Склад продуктов", " 2.Удалить его из таблицы Состав продукта"));
            return "/Product/error";
        }

        return "redirect:/product/findingProducts";
    }

    @GetMapping("/addProduct")
    public String addProductGet(@ModelAttribute("product") Product product,Model model){
        model.addAttribute("colors",productDAO.getColors());
        return "/Product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") @Valid Product product,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("colors",productDAO.getColors());
            return "Product/addProduct";
        }
       productDAO.addProduct(product);
        return "redirect:/product/findingProducts";
    }
}
