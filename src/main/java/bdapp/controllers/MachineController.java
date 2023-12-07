package bdapp.controllers;

import bdapp.DAO.DepartmentDAO;
import bdapp.DAO.MachineDAO;
import bdapp.DAO.StaffDAO;
import bdapp.model.Machinery;
import bdapp.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    private MachineDAO machineDAO;
    private StaffDAO staffDAO;

    @GetMapping("/findMachine")
    public String findingStaffs(Model model, @ModelAttribute("mac")@Valid Machinery machinery, BindingResult bindingResult){
        model.addAttribute("names",machineDAO.getNames());
        model.addAttribute("macs",machineDAO.getFindMachine(machinery));
        return "machinery/machine";
    }

//    @GetMapping("/updateStaff/{id}")
//    public String updateStaffGet(@PathVariable("id") int id, Model model){
//        Staff staff=staffDAO.getOneStaff(id);
//        model.addAttribute("staff",staff);
//        return "table/updateStafff";
//
//    }
//    @PatchMapping("/updateStaff/{id}")
//    public String updateStaff(@PathVariable("id") int id,@ModelAttribute("staff")@Valid Staff staff,BindingResult bindingResult){
//        if(bindingResult.hasErrors()) return "table/updateStafff";
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
//            return "/table/error";
//        }
//
//        return "redirect:/staff/findingStaffs";
//    }
//
//    @GetMapping("/addStaff")
//    public String addStaffGet(@ModelAttribute("staff") Staff staff){
//
//        return "/table/addStaff";
//    }
//
//    @PostMapping("/addStaff")
//    public String addStaff(@ModelAttribute("staff") @Valid Staff staff,BindingResult bindingResult){
//        if(bindingResult.hasErrors()) return "table/addStaff";
//        staffDAO.addStaff(staff);
//        return "redirect:/staff/findingStaffs";
//    }
    @GetMapping("/auth")
    public String auth(){
        return "/auth";
    }



}
