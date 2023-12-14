package bdapp.controllers;

import bdapp.DAO.CustomerDAO;
import bdapp.DAO.DeliveryCompanyDAO;
import bdapp.model.Customer;
import bdapp.model.DeliveryCompany;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/deliveryCompany")
public class DeliveryCompanyController {

    @Autowired
    private DeliveryCompanyDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") DeliveryCompany obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "deliveryCompany/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        DeliveryCompany obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "deliveryCompany/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid DeliveryCompany obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "deliveryCompany/update";
        }
        DAO.update(obj);
        return "redirect:/deliveryCompany/find";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("obj") DeliveryCompany obj,@PathVariable("id") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этой компании:"," 1.Чтобы не было заказа на материалы с данной компанией"));
            return "/deliveryCompany/error";
        }

        return "redirect:/deliveryCompany/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") DeliveryCompany obj,Model model){
        return "/deliveryCompany/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid DeliveryCompany obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "deliveryCompany/add";
        }
        DAO.add(obj);
        return "redirect:/deliveryCompany/find";
    }
}
