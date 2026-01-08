package com.govnorganization.personalblog.personalblog.controllers;

import com.govnorganization.personalblog.personalblog.entity.PersonalBlogArticle;
import com.govnorganization.personalblog.personalblog.repository.PersonalBlogArticleRepository;
import java.util.List;

import com.govnorganization.personalblog.personalblog.service.PersonalBlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonalBlogController {
  private final PersonalBlogArticleService personalBlogArticleService;

  @Autowired
  public PersonalBlogController(PersonalBlogArticleService personalBlogArticleService) {
    System.out.println("herer");
    this.personalBlogArticleService = personalBlogArticleService;
  }

  @RequestMapping("/")
  public String redirect_to_main_page(){
    return "redirect:/home";
  }

  @GetMapping("/home")
  public String home(Model model) {
    List<PersonalBlogArticle> articles = personalBlogArticleService.getAllArticles();
    model.addAttribute("all_articles", articles);
    return "home";
  }

  @GetMapping("/article/{id}")
  public String get_article(@PathVariable Long id, Model model) {
    PersonalBlogArticle article = personalBlogArticleService.findById(id);
    model.addAttribute("article", article);

    return "article/article";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    List<PersonalBlogArticle> articles = personalBlogArticleService.getAllArticles();
    model.addAttribute("all_articles", articles);

    return "admin";
  }

  @GetMapping("/article_update/{id}")
  public String article_update(@PathVariable Long id, Model model) {
    PersonalBlogArticle article = personalBlogArticleService.findById(id);
    model.addAttribute("article_update", article);

    return "edit/update_article";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable Long id, @ModelAttribute PersonalBlogArticle article) {
    PersonalBlogArticle updated_article = personalBlogArticleService.findById(id);
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
