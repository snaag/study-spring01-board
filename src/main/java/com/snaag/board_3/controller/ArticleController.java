package com.snaag.board_3.controller;

import com.snaag.board_3.domain.Article;
import com.snaag.board_3.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/create")
    public String create() {
        return "article/create";
    }

    @GetMapping("/update")
    public String update() {
        return "article/update";
    }

    @PostMapping("/article/create")
    public String createArticle(ArticleForm form) {
        Article article = new Article();
        article.setName(form.getName());
        article.setTitle(form.getTitle());
        article.setContext(form.getContext());

        System.out.println("article.getName() = " + article.getName());
        System.out.println("article.getTitle() = " + article.getTitle());
        System.out.println("article.getContext() = " + article.getContext());

        articleService.createArticle(article);

        return "redirect:/";
    }
}
