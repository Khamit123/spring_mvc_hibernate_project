package bdapp.controllers;

import bdapp.DAO.MachineStatusDAO;
import bdapp.DAO.MachineStorageDAO;
import bdapp.model.MachineStatus;
import bdapp.model.MachineStorage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("machineStorage")
public class MachineStorageController {
    @Autowired
    private MachineStorageDAO DAO;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("mch", DAO.getMachines());
    }

    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") MachineStorage obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "machineStorage/find";
    }

//    @GetMapping("/update/{name}")
//    public String updateGet(@PathVariable("name") String id, Model model){
//        MachineStorage obj=DAO.getOne(id);
//        model.addAttribute("obj",obj);
//        return "machineStorage/update";
//
//    }
//    @PatchMapping("/update/{name}")
//    public String update(@PathVariable("name") String id, @ModelAttribute("obj") @Valid MachineStorage obj, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()) {
//            return "machineStorage/update";
//        }
//        DAO.update(obj);
//        return "redirect:/machineStorage/find";
//
//    }

    @DeleteMapping("/delete/{name}")
    public String delete(@ModelAttribute("obj") MachineStorage obj,@PathVariable("name") String id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого склада необходимо:"," 1.Чтобы ни одно оборудования не принадлежало этому складу"));
            return "/machineStorage/error";
        }

        return "redirect:/machineStorage/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") MachineStorage obj,Model model){

        return "/machineStorage/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid MachineStorage obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "machineStorage/add";
        }
        DAO.add(obj);
        return "redirect:/machineStorage/find";
    }
}
