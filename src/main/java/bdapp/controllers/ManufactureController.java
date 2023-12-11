package bdapp.controllers;

import bdapp.DAO.MachineStatusDAO;
import bdapp.DAO.ManufactureDAO;
import bdapp.model.MachineStatus;
import bdapp.model.Manufacture;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacture")
public class ManufactureController {
    @Autowired
    private ManufactureDAO DAO;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("factories", DAO.getFactories());
        model.addAttribute("products", DAO.getProducts());
    }

    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") Manufacture obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "manufacture/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        Manufacture obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "manufacture/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid Manufacture obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "manufacture/update";
        }
        DAO.update(obj);
        return "redirect:/manufacture/find";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("obj") Manufacture obj,@PathVariable("id") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этой записи необходимо:"," 1.Чтобы ни одно оборудования не принадлежало этому статусу"));
            return "/manufacture/error";
        }

        return "redirect:/manufacture/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") Manufacture obj,Model model){

        return "/manufacture/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid Manufacture obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "manufacture/add";
        }
        DAO.add(obj);
        return "redirect:/manufacture/find";
    }
}
