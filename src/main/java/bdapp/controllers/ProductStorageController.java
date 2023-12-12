package bdapp.controllers;

import bdapp.DAO.MachineStorageDAO;
import bdapp.DAO.MaterialStorageDAO;
import bdapp.DAO.ProductStorageDAO;
import bdapp.model.MachineStorage;
import bdapp.model.MaterialStorage;
import bdapp.model.ProductStorage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("productStorage")
public class ProductStorageController {
    @Autowired
    private ProductStorageDAO DAO;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("mats", DAO.getMaterials());
    }

    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") ProductStorage obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        return "productStorage/find";
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
    public String delete(@ModelAttribute("obj") ProductStorage obj,@PathVariable("name") String id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого склада необходимо:"," 1.Чтобы ни одно оборудования не принадлежало этому складу"));
            return "/productStorage/error";
        }

        return "redirect:/productStorage/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") ProductStorage obj,Model model){

        return "/productStorage/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid ProductStorage obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "productStorage/add";
        }
        DAO.add(obj);
        return "redirect:/productStorage/find";
    }
}
