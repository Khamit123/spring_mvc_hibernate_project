package bdapp.controllers;

import bdapp.DAO.MaterialDAO;
import bdapp.model.Material;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/material")
public class MaterialController {

     @Autowired
    private MaterialDAO materialDAO;



    @GetMapping("/find")
    public String findingStaffs(Model model, @ModelAttribute("material") Material material){
        model.addAttribute("names",materialDAO.getNames());
        model.addAttribute("materials",materialDAO.getFind(material));
        model.addAttribute("units",materialDAO.getUnits());
        return "material/find";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model){
        Material material=materialDAO.getOne(id);
        model.addAttribute("material",material);
        model.addAttribute("units",materialDAO.getUnits());
        return "material/update";

    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("material") @Valid Material material, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("units",materialDAO.getUnits());
            return "material/update";
        }
        materialDAO.update(material,id);
        return "redirect:/material/find";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("material") Material material,@PathVariable int id,Model model){
        try{
            materialDAO.delete(material);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого матриала необходимо:"," 1.Удалить его из таблицы Склад материалов", " 2.Удалить его из таблицы Состав продукта"));
            return "/material/error";
        }

        return "redirect:/material/find";
    }

    @GetMapping("/add")
    public String addtGet(@ModelAttribute("material") Material material,Model model){
        model.addAttribute("units",materialDAO.getUnits());
        return "/material/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("material") @Valid Material material,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("units",materialDAO.getUnits());
            return "material/add";
        }
        materialDAO.add(material);
        return "redirect:/material/find";
    }
}
