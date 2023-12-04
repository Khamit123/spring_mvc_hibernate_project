package bdapp.controllers;

import bdapp.DAO.StaffDAO;
import bdapp.model.Department;
import bdapp.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffDAO staffDAO;
    @GetMapping("/allStaffs")
    public String allStaffs(Model model){
        model.addAttribute("names",staffDAO.getNames());
        model.addAttribute("staffs",staffDAO.getAllStaffs());
        return "table/allStaff";
    }



    @GetMapping("/findingStaffs")
    public String findingStaffs(Model model, @ModelAttribute ("staff")@Valid Staff staff, BindingResult bindingResult){
        model.addAttribute("names",staffDAO.getNames());
        model.addAttribute("staffs",staffDAO.getFindStaff(staff));
        return "table/findStaff";
    }

    @GetMapping("/updateStaff/{id}")
    public String updateStaffGet(@PathVariable("id") int id,Model model){
        Staff staff=staffDAO.getOneStaff(id);
        model.addAttribute("staff",staff);
        return "table/updateStafff";

    }
    @PatchMapping("/updateStaff/{id}")
    public String updateStaff(@PathVariable("id") int id,@ModelAttribute("staff")@Valid Staff staff,BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "table/updateStafff";
        staffDAO.updateStaff(staff);
        return "redirect:/staff/findingStaffs";

    }

    @DeleteMapping("/deleteStaff/{id}")
    public String deleteStaff(@ModelAttribute("staff") Staff staff,@PathVariable int id){
        System.out.println(staff);
    staffDAO.deleteStaff(staff);
    return "redirect:/staff/findingStaffs";
    }

    @GetMapping("/addStaff")
    public String addStaffGet(@ModelAttribute("staff") Staff staff){

        return "/table/addStaff";
    }

    @PostMapping("/addStaff")
    public String addStaff(@ModelAttribute("staff") @Valid Staff staff,BindingResult bindingResult){
       if(bindingResult.hasErrors()) return "table/addStaff";
        staffDAO.addStaff(staff);
        return "redirect:/staff/findingStaffs";
    }



}
