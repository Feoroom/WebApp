package webcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@AllArgsConstructor
@Controller
public class MainPageController {

    @GetMapping("/")
    public String mainPage(){
        return "mainPage";
    }
}
