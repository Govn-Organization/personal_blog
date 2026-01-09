package com.govnorganization.personalblog.personalblog.controllers;

import com.govnorganization.personalblog.personalblog.dto.ArticleSummaryDto;
import com.govnorganization.personalblog.personalblog.entity.PersonalBlogArticle;
import java.util.List;

import com.govnorganization.personalblog.personalblog.service.PersonalBlogArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonalBlogController {
  private static final Logger logger = LoggerFactory.getLogger(PersonalBlogController.class);
  private final PersonalBlogArticleService personalBlogArticleService;

  @Autowired
  public PersonalBlogController(PersonalBlogArticleService personalBlogArticleService) {
    this.personalBlogArticleService = personalBlogArticleService;
    logger.info("PersonalBlogController initialized");
  }

  @RequestMapping("/")
  public String redirect_to_main_page(){
    logger.debug("Redirecting from root to /home");
    return "redirect:/home";
  }

  @GetMapping("/home")
  public String home(Model model) {
    logger.info("Loading home page");
    List<ArticleSummaryDto> articles = personalBlogArticleService.getAllArticles();
    logger.debug("Loaded {} articles for home page", articles.size());
    model.addAttribute("all_articles", articles);
    return "home";
  }

  @GetMapping("/article/{id}")
  public String get_article(@PathVariable Long id, Model model) {
    logger.info("Loading article with id: {}", id);
    PersonalBlogArticle article = personalBlogArticleService.findById(id);
    logger.debug("Article loaded: {}", article.getTitle());
    model.addAttribute("article", article);

    return "article/article";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    logger.info("Loading admin page");
    List<ArticleSummaryDto> articles = personalBlogArticleService.getAllArticles();
    logger.debug("Loaded {} articles for admin page", articles.size());
    model.addAttribute("all_articles", articles);

    return "admin";
  }

  @GetMapping("/article_update/{id}")
  public String article_update(@PathVariable Long id, Model model) {
    logger.info("Loading article update form for id: {}", id);
    PersonalBlogArticle article = personalBlogArticleService.findById(id);
    model.addAttribute("article_update", article);
    return "edit/update_article";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable Long id, @ModelAttribute PersonalBlogArticle article) {
    logger.info("Updating article with id: {}", id);
    PersonalBlogArticle updated_article = personalBlogArticleService.findById(id);
    updated_article.setTitle(article.getTitle());
    updated_article.setDescription(article.getDescription());
    updated_article.setDate(article.getDate());
    updated_article.setContent(article.getContent());

    personalBlogArticleService.save(updated_article);
    logger.info("Article with id {} updated successfully", id);
    return "redirect:/admin";
  }

  @GetMapping("/new_article")
  public String new_article(Model model) {
    logger.info("Loading new article form");
    PersonalBlogArticle new_article = new PersonalBlogArticle();
    model.addAttribute("new_article", new_article);
    return "new_article";
  }

  @PostMapping("/add_article")
  public String add_article(@ModelAttribute PersonalBlogArticle article) {
    logger.info("Adding new article: {}", article.getTitle());
    personalBlogArticleService.save(article);
    logger.info("Article '{}' added successfully", article.getTitle());
    return "redirect:/admin";
  }

  @PostMapping("/delete/{id}")
  public String delete_article(@PathVariable Long id) {
    logger.info("Deleting article with id: {}", id);
    personalBlogArticleService.deleteById(id);
    logger.info("Article with id {} deleted successfully", id);
    return "redirect:/admin";
  }
}
