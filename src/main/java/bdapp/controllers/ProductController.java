package bdapp.controllers;

import bdapp.DAO.DepartmentDAO;
import bdapp.DAO.ProductDAO;
import bdapp.DAO.StaffDAO;
import bdapp.model.Product;
import bdapp.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductDAO productDAO;



    @GetMapping("/findingProducts")
    public String findingStaffs(Model model, @ModelAttribute("product")Product product){
        model.addAttribute("names",productDAO.getNames());
        model.addAttribute("products",productDAO.getFindProduct(product));
        model.addAttribute("colores",productDAO.getColors());
        return "Product/findProduct";
    }

//    @GetMapping("/updateStaff/{id}")
//    public String updateStaffGet(@PathVariable("id") int id, Model model){
//        Staff staff=staffDAO.getOneStaff(id);
//        model.addAttribute("staff",staff);
//        model.addAttribute("dep",departmentDAO.allDep());
//        return "staff/updateStafff";
//
//    }
//    @PatchMapping("/updateStaff/{id}")
//    public String updateStaff(@PathVariable("id") int id,@ModelAttribute("staff")@Valid Staff staff,BindingResult bindingResult){
//        if(bindingResult.hasErrors()) return "staff/updateStafff";
//        staffDAO.updateStaff(staff);
//        return "redirect:/staff/findingStaffs";
//
//    }
//
//    @DeleteMapping("/deleteStaff/{id}")
//    public String deleteStaff(@ModelAttribute("staff") Staff staff,@PathVariable int id,Model model){
//        try{
//            staffDAO.deleteStaff(staff);
//        }catch (Exception e){
//            model.addAttribute("msg", List.of("Для удаления этого сотрудника необходимо:"," 1.Удалить его из таблицы Техобслуживние", " 2.Удалить его из таблицы Заводы"));
//            return "/staff/error";
//        }
//
//        return "redirect:/staff/findingStaffs";
//    }
//
//    @GetMapping("/addStaff")
//    public String addStaffGet(@ModelAttribute("staff") Staff staff,Model model){
//        model.addAttribute("dep",departmentDAO.allDep());
//        return "/staff/addStaff";
//    }
//
//    @PostMapping("/addStaff")
//    public String addStaff(@ModelAttribute("staff") @Valid Staff staff,BindingResult bindingResult,Model model){
//        if(bindingResult.hasErrors()) {
//            System.out.println(staff.birthday);
//            System.out.println(bindingResult.getAllErrors().get(0));
//            model.addAttribute("dep",departmentDAO.allDep());
//            return "staff/addStaff";
//        }
//        staffDAO.addStaff(staff);
//        return "redirect:/staff/findingStaffs";
//    }
}