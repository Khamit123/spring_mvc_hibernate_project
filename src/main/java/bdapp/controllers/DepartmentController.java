package bdapp.controllers;

import bdapp.DAO.DepartmentDAO;
import bdapp.model.Department;
import bdapp.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentDAO departmentDAO;




    @GetMapping("/findDepartment")
    public String findingStaffs(Model model, @ModelAttribute("dep")@Valid Department department, BindingResult bindingResult){
        model.addAttribute("deps",departmentDAO.getFindDepatment(department));
        return "Dep/findDep";
    }

    @GetMapping("/updateDepartment/{id}")
    public String updateStaffGet(@PathVariable("id") int id, Model model){
        Department department=departmentDAO.getOneDepartment(id);
        model.addAttribute("dep",department);
        return "Dep/updateDep";

    }
    @PatchMapping("/updateDepartment/{id}")
    public String updateStaff(@PathVariable("id") int id,@ModelAttribute("dep")@Valid Department department,BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "Dep/updateDep";
        departmentDAO.updateDepartment(department);
        return "redirect:/department/findDepartment";

    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteStaff(@ModelAttribute("dep") Department department,@PathVariable int id,Model model){
        try{
            departmentDAO.deleteDepartment(department);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этого сотрудника необходимо:"," 1.Удалить его из таблицы Техобслуживние", " 2.Удалить его из таблицы Заводы"));
            return "/Dep/error";
        }

        return "redirect:/department/findDepartment";
    }

    @GetMapping("/addDepartment")
    public String addStaffGet(@ModelAttribute("dep") Department department){

        return "/Dep/addDep";
    }

    @PostMapping("/addStaff")
    public String addStaff(@ModelAttribute("dep") @Valid Department department,BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "Dep/addDep";
        departmentDAO.addDepartment(department);
        return "redirect:/department/findDepartment";
    }
}
