package com.govnorganization.personalblog.Personal_blog.service;

import com.govnorganization.personalblog.Personal_blog.model.Article;
import com.govnorganization.personalblog.Personal_blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleService {
    
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(String id) {
        return articleRepository.findById(id);
    }

    public Article saveArticle(Article article) {
        if (article.getPublishDate() == null) {
            article.setPublishDate(LocalDate.now());
        }
        
        return articleRepository.save(article);
    }
}
