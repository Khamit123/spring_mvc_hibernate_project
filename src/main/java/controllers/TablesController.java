package controllers;

import DAO.tableDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("table")
public class TablesController {
    private tableDAO tableDAO;
    @Autowired
    public TablesController(tableDAO td){
        this.tableDAO=td;
    }
    @GetMapping()
    public String index(Model model, @RequestParam(value = "tableName",required = false)
    String tableName){
        model.addAttribute("data", tableDAO.index(tableName));
        List<String> columns =new ArrayList<>();
        columns.add("department_id");
        columns.add("name");
        model.addAttribute("columns",columns);
        return "table/datas";
    }
}
