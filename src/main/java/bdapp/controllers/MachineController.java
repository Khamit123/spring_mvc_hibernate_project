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

    @GetMapping("/findMachine")
    public String findingStaffs(Model model, @ModelAttribute("mac")@Valid Machinery machinery, BindingResult bindingResult){
        model.addAttribute("names",machineDAO.getNames());
        model.addAttribute("types",machineDAO.getType());
        model.addAttribute("statuses",machineDAO.getStatus());
        model.addAttribute("factories",machineDAO.getFactory());
        model.addAttribute("mains",machineDAO.getMain());
        model.addAttribute("macs",machineDAO.getFindMachine(machinery));
        return "machinery/machine";
    }

    @GetMapping("/updateMachine/{machineId}")
    public String updateMachineGet(@PathVariable("machineId") int id, Model model){
        Machinery machinery=machineDAO.getOneMachine(id);
        System.out.println("Machine control "+ machinery.getMachineId());
        System.out.println();
        model.addAttribute("mac",machinery);
        model.addAttribute("names",machineDAO.getNames());
        model.addAttribute("types",machineDAO.getType());
        model.addAttribute("statuses",machineDAO.getStatus());
        model.addAttribute("factories",machineDAO.getFactory());
        model.addAttribute("mains",machineDAO.getMain());
        return "machinery/updateMachine";

    }
    @PatchMapping("/updateMachine/{machineId}")
    public String updateMachine (@PathVariable("machineId") int id,@ModelAttribute("mac")@Valid Machinery machinery,BindingResult bindingResult,Model model){
        System.out.println("Machine control  patch "+ machinery.getMachineId());
        System.out.println();
        if(bindingResult.hasErrors()) {
            model.addAttribute("names",machineDAO.getNames());
            model.addAttribute("types",machineDAO.getType());
            model.addAttribute("statuses",machineDAO.getStatus());
            model.addAttribute("factories",machineDAO.getFactory());
            model.addAttribute("mains",machineDAO.getMain());
            return "machinery/updateMachine";
        }
        machineDAO.updateMachine(machinery);
        return "redirect:/machine/findMachine";

    }

    @DeleteMapping("/deleteMachine/{machineId}")
    public String deleteMachine(@ModelAttribute("mac") Machinery machinery,@PathVariable("machineId") int id,Model model){
        try{
            machineDAO.deleteMachine(machinery);
        }catch (Exception e){
            model.addAttribute("msg", List.of("Для удаления этой машины необходимо:"," 1.Удалить его из Склада"));
            return "/machinery/error";
        }

        return "redirect:/machine/findMachine";
    }

    @GetMapping("/addMachine")
    public String addMachineGet(@ModelAttribute("mac") Machinery machinery,Model model){
        model.addAttribute("names",machineDAO.getNames());
        model.addAttribute("types",machineDAO.getType());
        model.addAttribute("statuses",machineDAO.getStatus());
        model.addAttribute("factories",machineDAO.getFactory());
        model.addAttribute("mains",machineDAO.getMain());
        return "/machinery/addMachine";
    }

    @PostMapping("/addMachine")
    public String addMachine(@ModelAttribute("mac") @Valid Machinery machinery,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("names",machineDAO.getNames());
            model.addAttribute("types",machineDAO.getType());
            model.addAttribute("statuses",machineDAO.getStatus());
            model.addAttribute("factories",machineDAO.getFactory());
            model.addAttribute("mains",machineDAO.getMain());
            return "machinery/addMachine";
        }
        machineDAO.addMachine(machinery);
        return "redirect:/machine/findMachine";
    }
    @GetMapping("/auth")
    public String auth(){
        return "/auth";
    }



}
