package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 첫 도메인 localhost:8080 했을때 딱 뜨는거
    public String home() {
        return "home" ;
    }
}
