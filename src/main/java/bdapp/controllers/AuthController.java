package bdapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AuthController {
    @GetMapping()
    public String main(){
        return "main";
    }

    @GetMapping("/login")
    public String auth(){
        return "auth";
    }
}
