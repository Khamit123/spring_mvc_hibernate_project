package bdapp.controllers;

import bdapp.DAO.MachineStorageDAO;
import bdapp.DAO.MaterialStorageDAO;
import bdapp.model.MachineStorage;
import bdapp.model.MaterialStorage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("materialStorage")
public class MaterialStorageController {
    @Autowired
    private MaterialStorageDAO DAO;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("mats", DAO.getMaterials());
    }

    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") MaterialStorage obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "materialStorage/find";
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
    public String delete(@ModelAttribute("obj") MaterialStorage obj,@PathVariable("name") String id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого склада необходимо:"," 1.Чтобы ни одно оборудования не принадлежало этому складу"));
            return "/materialStorage/error";
        }

        return "redirect:/materialStorage/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") MaterialStorage obj,Model model){

        return "/materialStorage/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid MaterialStorage obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "materialStorage/add";
        }
        DAO.add(obj);
        return "redirect:/materialStorage/find";
    }
}
