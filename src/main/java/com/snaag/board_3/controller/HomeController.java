package com.snaag.board_3.controller;

import com.snaag.board_3.domain.Article;
import com.snaag.board_3.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {
    private final ArticleService articleService;

    @Autowired
    public HomeController(ArticleService articleService) {
        this.articleService = articleService;

        addSampleArticles();
    }

    private void addSampleArticles() {
        Article articleCreate1 = new Article();
        articleCreate1.setName("sang-a");
        articleCreate1.setTitle("spring");
        articleCreate1.setContext("demo 만들어보기");

        Article articleCreate2 = new Article();
        articleCreate2.setName("LEaps");
        articleCreate2.setTitle("C#");
        articleCreate2.setContext("unity 로 게임 만들어보기");

        this.articleService.createArticle(articleCreate1);
        this.articleService.createArticle(articleCreate2);
    }

    @GetMapping("/")
    public String main(Model model) {
        List<Article> articleList = articleService.loadAllArticles();
        
        model.addAttribute("date", getDate("yyyy.MM.dd HH:mm"));
        model.addAttribute("articleList", articleList);

        for (Article article :
                articleList) {
            System.out.println(article.getName());
            System.out.println(article.getTitle());
            System.out.println(article.getContext());
            System.out.println(article.getId());
        }

        return "main";
    }

    private String getDate(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
