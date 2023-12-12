package bdapp.controllers;

import bdapp.DAO.CustomerDAO;
import bdapp.DAO.OrderDAO;
import bdapp.model.Customer;
import bdapp.model.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") Order obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("products",DAO.getAllProd());
        model.addAttribute("customers",DAO.getAllCustomer());
        model.addAttribute("objs",DAO.getFind(obj));
        return "order/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") Order obj,Model model){

        model.addAttribute("products",DAO.getAllProd());
        model.addAttribute("customers",DAO.getAllCustomer());
        return "/order/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid Order obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {

            model.addAttribute("products",DAO.getAllProd());
            model.addAttribute("customers",DAO.getAllCustomer());
            return "order/add";
        }
        DAO.add(obj);
        return "redirect:/order/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        Order obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        model.addAttribute("products",DAO.getAllProd());
        model.addAttribute("customers",DAO.getAllCustomer());
        return "order/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid Order obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("products",DAO.getAllProd());
            model.addAttribute("customers",DAO.getAllCustomer());
            return "order/update";
        }
        DAO.update(obj);
        return "redirect:/order/find";

    }
}
