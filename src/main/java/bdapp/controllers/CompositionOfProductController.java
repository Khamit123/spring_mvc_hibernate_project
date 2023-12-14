package bdapp.controllers;

import bdapp.DAO.CompositionOfProductDAO;
import bdapp.model.CompositionOfProduct;
import bdapp.model.Material;
import bdapp.model.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/compositionOfProduct")
public class CompositionOfProductController {

    @Autowired
    private CompositionOfProductDAO compositionOfProductDAO;

    @GetMapping("/find")
    public String findingStaffs(Model model, @ModelAttribute("compositionOfProduct") CompositionOfProduct compositionOfProduct){
        model.addAttribute("names",compositionOfProductDAO.getNames());
        model.addAttribute("compositionOfProducts",compositionOfProductDAO.getFind(compositionOfProduct));
        model.addAttribute("materials",compositionOfProductDAO.getAllMat());
        model.addAttribute("products",compositionOfProductDAO.getAllProd());
        return "compositionOfProduct/find";
    }

    @GetMapping("/update/{matId}/{prodId}")
    public String updateGet(@PathVariable("matId") int matId,@PathVariable("prodId") int prodId, Model model){
        CompositionOfProduct compositionOfProduct=compositionOfProductDAO.getOne(matId,prodId);
        model.addAttribute("compositionOfProduct",compositionOfProduct);
        model.addAttribute("materials",compositionOfProductDAO.getAllMat());
        model.addAttribute("products",compositionOfProductDAO.getAllProd());
        return "compositionOfProduct/update";

    }
    @PatchMapping("/update/{matId}/{prodId}")
    public String update(@PathVariable("matId") int matId,@PathVariable("prodId") int prodId,
                         @ModelAttribute("compositionOfProduct") @Valid CompositionOfProduct compositionOfProduct, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("materials",compositionOfProductDAO.getAllMat());
            model.addAttribute("products",compositionOfProductDAO.getAllProd());
            return "compositionOfProduct/update";
        }
        try {
            compositionOfProductDAO.update(compositionOfProduct,matId,prodId);
        }
        catch (Exception e){
            model.addAttribute("materials",compositionOfProductDAO.getAllMat());
            model.addAttribute("products",compositionOfProductDAO.getAllProd());
            model.addAttribute("error","Такой состав уже существует");
            return "compositionOfProduct/update";
        }

        return "redirect:/compositionOfProduct/find";

    }

    @DeleteMapping("/delete/{matId}/{prodId}")
    public String delete(@ModelAttribute("compositionOfProduct") CompositionOfProduct compositionOfProduct,@PathVariable("matId") int matId,@PathVariable("prodId") int prodId,Model model){
        try{
            compositionOfProductDAO.delete(matId,prodId);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg", List.of("Для удаления этого матриала необходимо:"," 1.Удалить его из таблицы Склад материалов", " 2.Удалить его из таблицы Состав продукта"));
            return "/compositionOfProduct/error";
        }

        return "redirect:/compositionOfProduct/find";
    }

    @GetMapping("/add")
    public String addtGet(@ModelAttribute("compositionOfProduct") CompositionOfProduct compositionOfProduct,Model model){
        model.addAttribute("materials",compositionOfProductDAO.getAllMat());
        model.addAttribute("products",compositionOfProductDAO.getAllProd());
        return "/compositionOfProduct/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("compositionOfProduct") @Valid CompositionOfProduct compositionOfProduct,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("materials",compositionOfProductDAO.getAllMat());
            model.addAttribute("products",compositionOfProductDAO.getAllProd());
            return "compositionOfProduct/add";
        }
        try {
            compositionOfProductDAO.add(compositionOfProduct);
        }
        catch (Exception e){
            model.addAttribute("materials",compositionOfProductDAO.getAllMat());
            model.addAttribute("products",compositionOfProductDAO.getAllProd());
            model.addAttribute("error","Такой состав уже существует");
            return "compositionOfProduct/add";
        }
        return "redirect:/compositionOfProduct/find";
    }


}
