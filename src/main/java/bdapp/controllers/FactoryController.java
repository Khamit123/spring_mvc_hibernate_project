package bdapp.controllers;

import bdapp.DAO.FactoryDAO;
import bdapp.DAO.MachineStatusDAO;
import bdapp.DAO.ManufactureDAO;
import bdapp.model.Factory;
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
@RequestMapping("/factory")
public class FactoryController {
    @Autowired
    private FactoryDAO DAO;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("staffs", DAO.getStaffs());
    }

    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") Factory obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "factory/find";
    }

    @GetMapping("/update/{factoryId}")
    public String updateGet(@PathVariable("factoryId") int id, Model model){
        Factory obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "factory/update";

    }
    @PatchMapping("/update/{factoryId}")
    public String update(@PathVariable("factoryId") int id, @ModelAttribute("obj") @Valid Factory obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "factory/update";
        }
        DAO.update(obj);
        return "redirect:/factory/find";

    }

    @DeleteMapping("/delete/{factoryId}")
    public String delete(@ModelAttribute("obj") Factory obj,@PathVariable("factoryId") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этой записи необходимо:"," 1.Чтобы в таблице производство не было этого завода"));
            return "/factory/error";
        }

        return "redirect:/factory/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") Factory obj,Model model){

        return "/factory/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid Factory obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "factory/add";
        }
        DAO.add(obj);
        return "redirect:/factory/find";
    }
}
