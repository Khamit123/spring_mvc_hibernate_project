package bdapp.controllers;

import bdapp.DAO.CustomerDAO;
import bdapp.DAO.MachineStatusDAO;
import bdapp.model.Customer;
import bdapp.model.MachineStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") Customer obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "customer/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        Customer obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "customer/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid Customer obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "customer/update";
        }
        DAO.update(obj);
        return "redirect:/customer/find";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("obj") Customer obj,@PathVariable("id") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого заказчика необходимо:"," 1.Чтобы не было заказа с данным заказчиком"));
            return "/customer/error";
        }

        return "redirect:/customer/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") Customer obj,Model model){
        return "/customer/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid Customer obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "customer/add";
        }
        DAO.add(obj);
        return "redirect:/customer/find";
    }
}
