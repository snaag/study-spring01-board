package com.snaag.board_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {
    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("date", getDate());
        return "list";
    }

    private String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @GetMapping("create")
    public String create(Model model) {
        return "create";
    }

    @GetMapping("update")
    public String update(Model model) {
        return "update";
    }
}
