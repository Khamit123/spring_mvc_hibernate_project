package bdapp.controllers;

import bdapp.DAO.StaffDAO;
import bdapp.model.Department;
import bdapp.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if(bindingResult.hasErrors()) return "table/findStaff";

        model.addAttribute("names",staffDAO.getNames());
        model.addAttribute("staffs",staffDAO.getFindStaff(staff));
        return "table/findStaff";
    }
}
