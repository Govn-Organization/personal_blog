package com.govnorganization.personalblog.Personal_blog.service;

import com.govnorganization.personalblog.Personal_blog.model.Article;
import com.govnorganization.personalblog.Personal_blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * ArticleService - handles business logic for articles
 * This class uses the repository to interact with the database
 */
@Service  // This tells Spring: "Hey, this is a service class, use it when needed"
public class ArticleService {
    
    private final ArticleRepository articleRepository;

    // Constructor - Spring automatically provides ArticleRepository
    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Get all articles from the database
     * @return List of all articles, sorted by date (newest first)
     */
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /**
     * Get one article by its ID
     * @param id - the article's ID
     * @return the article, or null if not found
     */
    public Article getArticleById(String id) {
        return articleRepository.findById(id);
    }

    /**
     * Save an article to the database
     * @param article - the article to save
     * @return the saved article
     */
    public Article saveArticle(Article article) {
        // If article doesn't have a date, use today's date
        if (article.getPublishDate() == null) {
            article.setPublishDate(LocalDate.now());
        }
        
        // Save using repository (repository handles ID generation if needed)
        return articleRepository.save(article);
    }
}





