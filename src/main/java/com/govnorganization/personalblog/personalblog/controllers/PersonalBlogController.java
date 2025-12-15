package com.govnorganization.personalblog.personalblog.controllers;

import com.govnorganization.personalblog.personalblog.model.PersonalBlogArticle;
import com.govnorganization.personalblog.personalblog.service.PersonalBlogArticleService;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonalBlogController implements CommandLineRunner {
  private final PersonalBlogArticleService personalBlogArticleService;

  public PersonalBlogController(PersonalBlogArticleService personalBlogArticleService) {
    this.personalBlogArticleService = personalBlogArticleService;
  }


  @GetMapping("/admin")
  public String admin(Model model) {


    List<PersonalBlogArticle> articles = personalBlogArticleService.findAll();
      model.addAttribute("all_articles",articles);
    return "admin";
  }

  @PostMapping("/new_article")
  public String new_article() {

    return "new";
  }
  @GetMapping("/article/{id}")
  public String article_by_id(@PathVariable Long id, Model model){
    PersonalBlogArticle article = personalBlogArticleService.findById(id).orElseThrow(() -> new RuntimeException("Article doesn't exist"));
    String format =article.getContent().replace("\n","<br>");
    article.setContent(format);
    model.addAttribute("article", article);

    return "article/article";
  }
    @Override
  public void run(String... args) throws Exception {
    personalBlogArticleService.save(
            new PersonalBlogArticle(
                    "Title1",
                    "some information",
                    "data",
                    """
                      some content lmaoooo
                      loooool
                      kk  eeeeekkk
              """));
    personalBlogArticleService.save(
            new PersonalBlogArticle(
                    "Title2",
                    "some information",
                    "data",
                    """
                              some conasdftent lmaoooo
                              looooasdfol
                              kk  eeeasdfasdf
                              asdfeekkk
                      """));
    personalBlogArticleService.save(
            new PersonalBlogArticle(
                    "Title3",
                    "some information",
                    "daasdfta",
                    """
                              some coasdfntent lmaoooo
                              looooasd

                              fol
                              kk  eeeasdfeekkk
                      """));
  }

}
