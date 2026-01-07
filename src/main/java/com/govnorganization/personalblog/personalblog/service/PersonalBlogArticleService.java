package com.govnorganization.personalblog.personalblog.service;

import com.govnorganization.personalblog.personalblog.entity.PersonalBlogArticle;
import com.govnorganization.personalblog.personalblog.repository.PersonalBlogArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalBlogArticleService {
  private final PersonalBlogArticleRepository personalBlogArticleRepository;

  @Autowired
  public PersonalBlogArticleService(PersonalBlogArticleRepository personalBlogArticleRepository) {
    System.out.println("Wired");
    this.personalBlogArticleRepository = personalBlogArticleRepository;
  }

  public List<PersonalBlogArticle> getAllArticles() {
    System.out.println("geted");
    return personalBlogArticleRepository.findAll();
  }

  public void save(PersonalBlogArticle article) {
    System.out.println("saved");
    personalBlogArticleRepository.save(article);
  }

  public PersonalBlogArticle findById(Long id) {
    System.out.println("finded");
    return personalBlogArticleRepository.findById(id).orElseThrow(() -> new RuntimeException());
  }

  public void deleteById(Long id) {
    System.out.println("deleted");
    personalBlogArticleRepository.deleteById(id);
  }
}
