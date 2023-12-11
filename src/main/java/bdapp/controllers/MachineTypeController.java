package bdapp.controllers;

import bdapp.DAO.MachineStatusDAO;
import bdapp.DAO.MachineTypeDAO;
import bdapp.model.MachineStatus;
import bdapp.model.MachineType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("machineType")
public class MachineTypeController {
    @Autowired
    private MachineTypeDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") MachineType obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "machineType/find";
    }

    @GetMapping("/update/{machineTypeId}")
    public String updateGet(@PathVariable("machineTypeId") int id, Model model){
        MachineType obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "machineType/update";

    }
    @PatchMapping("/update/{machineTypeId}")
    public String update(@PathVariable("machineTypeId") int id, @ModelAttribute("obj") @Valid MachineType obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "machineType/update";
        }
        DAO.update(obj);
        return "redirect:/machineType/find";

    }

    @DeleteMapping("/delete/{machineTypeId}")
    public String delete(@ModelAttribute("obj") MachineType obj,@PathVariable("machineTypeId") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого типа необходимо:"," 1.Чтобы ни одно оборудования не принадлежало этому типу"));
            return "/machineType/error";
        }

        return "redirect:/machineType/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") MachineType obj,Model model){
        return "/machineType/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid MachineType obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "machineType/add";
        }
        DAO.add(obj);
        return "redirect:/machineType/find";
    }
}
