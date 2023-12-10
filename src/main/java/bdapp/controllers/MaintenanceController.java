package bdapp.controllers;

import bdapp.DAO.DepartmentDAO;
import bdapp.DAO.MaintenanceDAO;
import bdapp.model.Department;
import bdapp.model.Maintenance;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceDAO maintenanceDAO;




    @GetMapping("/findMaintenance")
    public String findMaintenance(Model model, @ModelAttribute("main")@Valid Maintenance maintenance, BindingResult bindingResult){
        model.addAttribute("mains",maintenanceDAO.getFindMaintenance(maintenance));
        return "maintenance/findMain";
    }

    @GetMapping("/updateMaintenance/{maintenanceId}")
    public String updateMaintenanceGet(@PathVariable("maintenanceId") int id, Model model){
        Maintenance maintenance=maintenanceDAO.getOneMaintenance(id);
        model.addAttribute("main",maintenance);
        return "maintenance/updateMain";

    }
    @PatchMapping("/updateMaintenance/{maintenanceId}")
    public String updateMaintenance(@PathVariable("maintenanceId") int id,@ModelAttribute("main")@Valid Maintenance maintenance,BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "maintenance/updateMain";
        maintenanceDAO.updateMaintenance(maintenance);
        return "redirect:/maintenance/findMaintenance";

    }

    @DeleteMapping("/deleteMaintenance/{maintenanceId}")
    public String deleteMaintenance(@ModelAttribute("dep") Maintenance maintenance,@PathVariable("maintenanceId") int id,Model model){
        try{
            maintenanceDAO.deleteMaintenance(maintenance);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления Техобслуживания:"," 1.Необходимо убрать из этого отдела  всех сотрудников"));
            return "maintenance/findMain";
        }

        return "redirect:/maintenance/findMaintenance";
    }

    @GetMapping("/addMaintenancet")
    public String addMaintenanceGet(@ModelAttribute("main") Maintenance maintenance){

        return "maintenance/addMain";
    }

    @PostMapping("/addMaintenance")
    public String addMaintenance(@ModelAttribute("main") @Valid Maintenance maintenance,BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "maintenance/addMain";
        maintenanceDAO.addMaintenance(maintenance);
        return "redirect:/maintenance/findMaintenance";
    }
}
