package com.govnorganization.personalblog.personalblog.repository;

import com.govnorganization.personalblog.personalblog.entity.PersonalBlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalBlogArticleRepository extends JpaRepository<PersonalBlogArticle, Long> {}
