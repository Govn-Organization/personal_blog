package com.govnorganization.personalblog.Personal_blog.controller;

import com.govnorganization.personalblog.Personal_blog.model.Article;
import com.govnorganization.personalblog.Personal_blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller - handles web requests
 * This is like a "traffic director" - when someone visits a URL, this decides what to show them
 */
@Controller  // This tells Spring: "This class handles web pages"
public class Personalblogcontroller {

    // This automatically gives us the ArticleService (Spring does this for us)
    @Autowired
    private ArticleService articleService;

    /**
     * Home page - shows list of all articles
     * When someone visits: http://localhost:8080/
     */
    @GetMapping("/")  // This means: "When someone visits the root URL (/), run this method"
    public String home(Model model) {
        // Get all articles from the service
        // Model is like a box we put data in to send to the HTML page
        model.addAttribute("articles", articleService.getAllArticles());
        
        // Return the name of the HTML template to show (home.html)
        return "home";
    }

    /**
     * Article page - shows one specific article
     * When someone visits: http://localhost:8080/article/123
     */
    @GetMapping("/article/{id}")  // {id} means it's a variable - like /article/abc123
    public String article(@PathVariable String id, Model model) {
        // Get the article with this ID
        Article article = articleService.getArticleById(id);
        
        // If article doesn't exist, go back to home page
        if (article == null) {
            return "redirect:/";  // redirect means "go to this URL instead"
        }
        
        // Put the article in the model so the HTML page can use it
        model.addAttribute("article", article);
        
        // Show the article.html template
        return "article";
    }

    /**
     * New article form page
     * When someone visits: http://localhost:8080/new
     */
    @GetMapping("/new")  // GET request = show the form page
    public String newArticleForm(Model model) {
        // Create an empty article object (not really needed, but good practice)
        model.addAttribute("article", new Article());
        
        // Show the new.html template (the form)
        return "new";
    }

    /**
     * Create article - handles form submission
     * When someone submits the form on /new page
     */
    @PostMapping("/new")  // POST request = form was submitted
    public String createArticle(
            @RequestParam String title,           // Get "title" from the form
            @RequestParam String description,      // Get "description" from the form
            @RequestParam String content,          // Get "content" from the form
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate) {
        
        // Create a new Article object
        Article article = new Article();
        
        // Fill it with data from the form
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        
        // If no date provided, use today's date
        if (publishDate != null) {
            article.setPublishDate(publishDate);
        } else {
            article.setPublishDate(LocalDate.now());
        }
        
        // Save the article to a file
        articleService.saveArticle(article);
        
        // Redirect to home page after saving
        return "redirect:/";
    }
}
