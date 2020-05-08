package ru.graduation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootContoller {
    @GetMapping("/")
    public String root() {
        return "index";
    }
}
