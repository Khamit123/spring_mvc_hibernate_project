package bdapp.controllers;

import bdapp.DAO.MaterialDeliveryDAO;
import bdapp.DAO.OrderDAO;
import bdapp.model.MaterialDelivery;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materialDelivery")
public class MaterialDeliveryController {

    @Autowired
    private MaterialDeliveryDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") MaterialDelivery obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("materials",DAO.getAllMaterial());
        model.addAttribute("companies",DAO.getAllProdDeliveryCompany());
        model.addAttribute("objs",DAO.getFind(obj));
        return "materialDelivery/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") MaterialDelivery obj,Model model){

        model.addAttribute("materials",DAO.getAllMaterial());
        model.addAttribute("companies",DAO.getAllProdDeliveryCompany());
        return "/materialDelivery/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid MaterialDelivery obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {

            model.addAttribute("materials",DAO.getAllMaterial());
            model.addAttribute("companies",DAO.getAllProdDeliveryCompany());
            return "materialDelivery/add";
        }
        DAO.add(obj);
        return "redirect:/materialDelivery/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        MaterialDelivery obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "materialDelivery/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid MaterialDelivery obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("materials",DAO.getAllMaterial());
            model.addAttribute("companies",DAO.getAllProdDeliveryCompany());
            return "materialDelivery/update";
        }
        DAO.update(obj);
        return "redirect:/materialDelivery/find";

    }
}
