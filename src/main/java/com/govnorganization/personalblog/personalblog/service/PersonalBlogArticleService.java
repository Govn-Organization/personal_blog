package com.govnorganization.personalblog.personalblog.service;

import com.govnorganization.personalblog.personalblog.model.PersonalBlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalBlogArticleService extends JpaRepository<PersonalBlogArticle, Long> {}
