package bdapp.controllers;

import bdapp.DAO.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "table/datas";
    }
}
