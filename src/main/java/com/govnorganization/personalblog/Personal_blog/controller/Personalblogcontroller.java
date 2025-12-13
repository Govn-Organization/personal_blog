package com.govnorganization.personalblog.Personal_blog.controller;

import com.govnorganization.personalblog.Personal_blog.model.Article;
import com.govnorganization.personalblog.Personal_blog.service.ArticleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class Personalblogcontroller {

    private final ArticleService articleService;

    public Personalblogcontroller(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "home";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable String id, Model model) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "redirect:/";
        }
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/new")
    public String newArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "new";
    }

    @PostMapping("/new")
    public String createArticle(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String content,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate) {
        
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setPublishDate(publishDate != null ? publishDate : LocalDate.now());
        
        articleService.saveArticle(article);
        return "redirect:/";
    }
}
