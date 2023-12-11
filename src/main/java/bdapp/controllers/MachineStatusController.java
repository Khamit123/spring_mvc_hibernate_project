package bdapp.controllers;

import bdapp.DAO.MachineStatusDAO;
import bdapp.DAO.MaterialDAO;
import bdapp.model.MachineStatus;
import bdapp.model.Material;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("machineStatus")
public class MachineStatusController {
    @Autowired
    private MachineStatusDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") MachineStatus obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "machineStatus/find";
    }

    @GetMapping("/update/{machineStatusId}")
    public String updateGet(@PathVariable("machineStatusId") int id, Model model){
        MachineStatus obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        return "machineStatus/update";

    }
    @PatchMapping("/update/{machineStatusId}")
    public String update(@PathVariable("machineStatusId") int id, @ModelAttribute("obj") @Valid MachineStatus obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "machineStatus/update";
        }
        DAO.update(obj);
        return "redirect:/machineStatus/find";

    }

    @DeleteMapping("/delete/{machineStatusId}")
    public String delete(@ModelAttribute("obj") MachineStatus obj,@PathVariable("machineStatusId") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого статуса необходимо:"," 1.Чтобы ни одно оборудования не принадлежало этому статусу"));
            return "/machineStatus/error";
        }

        return "redirect:/machineStatus/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") MachineStatus obj,Model model){
        return "/machineStatus/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid MachineStatus obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "machineStatus/add";
        }
        DAO.add(obj);
        return "redirect:/machineStatus/find";
    }
}
