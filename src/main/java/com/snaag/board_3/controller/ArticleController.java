package com.snaag.board_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ArticleController {
    @GetMapping("create")
    public String create(Model model) {
        return "create";
    }

    @GetMapping("update")
    public String update(Model model) {
        return "update";
    }
}
