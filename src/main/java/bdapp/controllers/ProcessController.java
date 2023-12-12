package bdapp.controllers;

import bdapp.DAO.CustomerDAO;
import bdapp.DAO.ProcessDAO;
import bdapp.model.Customer;
import bdapp.model.Process;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private ProcessDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") Process obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        model.addAttribute("materials",DAO.getAllMaterial());
        model.addAttribute("products",DAO.getAllProduct());
        return "process/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        Process obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        model.addAttribute("materials",DAO.getAllMaterial());
        model.addAttribute("products",DAO.getAllProduct());
        return "process/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid Process obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("materials",DAO.getAllMaterial());
            model.addAttribute("products",DAO.getAllProduct());
            return "process/update";
        }
        DAO.update(obj);
        return "redirect:/process/find";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("obj") Process obj,@PathVariable("id") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого процесса необходимо:"," 1.Чтобы он не участвовал в таблице Этапы процесса"));
            return "/process/error";
        }

        return "redirect:/process/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") Process obj,Model model){
        model.addAttribute("materials",DAO.getAllMaterial());
        model.addAttribute("products",DAO.getAllProduct());
        return "/process/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid Process obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("materials",DAO.getAllMaterial());
            model.addAttribute("products",DAO.getAllProduct());
            return "process/add";
        }
        DAO.add(obj);
        return "redirect:/process/find";
    }
}
