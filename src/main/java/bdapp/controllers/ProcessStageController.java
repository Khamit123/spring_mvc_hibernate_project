package bdapp.controllers;

import bdapp.DAO.ProcessStageDAO;
import bdapp.model.Process;
import bdapp.model.ProcessStage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/processStage")
public class ProcessStageController {

    @Autowired
    private ProcessStageDAO DAO;



    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("obj") ProcessStage obj){
        model.addAttribute("names",DAO.getNames());
        model.addAttribute("objs",DAO.getFind(obj));
        model.addAttribute("proceses",DAO.getAllProcess());
        model.addAttribute("mactypes",DAO.getAllMacType());
        return "processStage/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        ProcessStage obj=DAO.getOne(id);
        model.addAttribute("obj",obj);
        model.addAttribute("proceses",DAO.getAllProcess());
        model.addAttribute("mactypes",DAO.getAllMacType());
        return "processStage/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("obj") @Valid ProcessStage obj, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("proceses",DAO.getAllProcess());
            model.addAttribute("mactypes",DAO.getAllMacType());
            return "processStage/update";
        }
        DAO.update(obj);
        return "redirect:/processStage/find";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("obj") ProcessStage obj,@PathVariable("id") int id,Model model){
        try{
            DAO.delete(obj);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого заказчика необходимо:"," 1.Чтобы не было заказа с данным заказчиком"));
            return "/processStage/error";
        }

        return "redirect:/processStage/find";
    }

    @GetMapping("/add")
    public String addGet(@ModelAttribute("obj") ProcessStage obj,Model model){
        model.addAttribute("proceses",DAO.getAllProcess());
        model.addAttribute("mactypes",DAO.getAllMacType());
        return "/processStage/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("obj") @Valid ProcessStage obj,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("proceses",DAO.getAllProcess());
            model.addAttribute("mactypes",DAO.getAllMacType());
            return "processStage/add";
        }
        DAO.add(obj);
        return "redirect:/processStage/find";
    }
}
