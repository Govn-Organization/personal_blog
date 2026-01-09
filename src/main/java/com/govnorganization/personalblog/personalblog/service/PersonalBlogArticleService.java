package com.govnorganization.personalblog.personalblog.service;

import com.govnorganization.personalblog.personalblog.dto.ArticleSummaryDto;
import com.govnorganization.personalblog.personalblog.entity.PersonalBlogArticle;
import com.govnorganization.personalblog.personalblog.repository.PersonalBlogArticleRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalBlogArticleService {
  private static final Logger logger = LoggerFactory.getLogger(PersonalBlogArticleService.class);
  private final PersonalBlogArticleRepository personalBlogArticleRepository;

  @Autowired
  public PersonalBlogArticleService(PersonalBlogArticleRepository personalBlogArticleRepository) {
    this.personalBlogArticleRepository = personalBlogArticleRepository;
    logger.info("PersonalBlogArticleService initialized");
  }

  public List<ArticleSummaryDto> getAllArticles() {
    logger.debug("Fetching all articles");
    List<ArticleSummaryDto> articles =
        personalBlogArticleRepository.findAll().stream()
            .map(
                article ->
                    new ArticleSummaryDto(article.getId(), article.getTitle(), article.getDate()))
            .collect(Collectors.toList());
    logger.debug("Found {} articles", articles.size());
    return articles;
  }

  public void save(PersonalBlogArticle article) {
    logger.debug("Saving article: {}", article.getTitle());
    personalBlogArticleRepository.save(article);
    logger.debug("Article saved with id: {}", article.getId());
  }

  public PersonalBlogArticle findById(Long id) {
    logger.debug("Finding article by id: {}", id);
    return personalBlogArticleRepository
        .findById(id)
        .orElseThrow(
            () -> {
              logger.error("Article with id {} not found", id);
              return new RuntimeException("Article not found");
            });
  }

  public void deleteById(Long id) {
    logger.debug("Deleting article by id: {}", id);
    personalBlogArticleRepository.deleteById(id);
    logger.debug("Article with id {} deleted", id);
  }
}
