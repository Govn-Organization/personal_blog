package com.govnorganization.personalblog.personalblog.controllers;

import com.govnorganization.personalblog.personalblog.model.PersonalBlogArticle;
import com.govnorganization.personalblog.personalblog.service.PersonalBlogArticleService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonalBlogController {
  private final PersonalBlogArticleService personalBlogArticleService;

  public PersonalBlogController(PersonalBlogArticleService personalBlogArticleService) {
    this.personalBlogArticleService = personalBlogArticleService;
  }

  @GetMapping("/home")
  public String home(Model model) {
    List<PersonalBlogArticle> articles = personalBlogArticleService.findAll();
    model.addAttribute("all_articles", articles);
    return "home";
  }

  @GetMapping("/article/{id}")
  public String get_article(@PathVariable Long id, Model model) {
    PersonalBlogArticle article =
        personalBlogArticleService
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Article doesn't exist"));
    model.addAttribute("article", article);

    return "article/article";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    List<PersonalBlogArticle> articles = personalBlogArticleService.findAll();
    model.addAttribute("all_articles", articles);

    return "admin";
  }

  @GetMapping("/article_update/{id}")
  public String article_update(@PathVariable Long id, Model model) {
    PersonalBlogArticle article =
        personalBlogArticleService
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Article doesn't exist"));
    model.addAttribute("article_update", article);

    return "edit/update_article";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable Long id, @ModelAttribute PersonalBlogArticle article) {
    PersonalBlogArticle updated_article =
        personalBlogArticleService
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Article doesn't exist"));
    personalBlogArticleService.save(article);
    updated_article.setTitle(article.getTitle());
    updated_article.setDescription(article.getDescription());
    updated_article.setDate(article.getDate());
    updated_article.setContent(article.getContent());

    personalBlogArticleService.save(updated_article);
    return "redirect:/admin";
  }

  @GetMapping("/new_article")
  public String new_article(Model model) {
    PersonalBlogArticle new_article = new PersonalBlogArticle();
    model.addAttribute("new_article", new_article);
    return "new_article";
  }

  @PostMapping("/add_article")
  public String add_article(@ModelAttribute PersonalBlogArticle article) {
    personalBlogArticleService.save(article);
    return "redirect:/admin";
  }

  @PostMapping("/delete/{id}")
  public String delete_article(@PathVariable Long id) {

    personalBlogArticleService.deleteById(id);

    return "redirect:/admin";
  }
}
