package com.govnorganization.personalblog.Personal_blog.repository;

import com.govnorganization.personalblog.Personal_blog.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Article> ROW_MAPPER = (rs, rowNum) -> {
        Article article = new Article();
        article.setId(rs.getString("id"));
        article.setTitle(rs.getString("title"));
        article.setDescription(rs.getString("description"));
        article.setContent(rs.getString("content"));
        article.setPublishDate(rs.getDate("publish_date").toLocalDate());
        return article;
    };

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM articles ORDER BY publish_date DESC", ROW_MAPPER);
    }

    public Article findById(String id) {
        return jdbcTemplate.query("SELECT * FROM articles WHERE id = ?", ROW_MAPPER, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Article save(Article article) {
        if (article.getId() == null || article.getId().isEmpty()) {
            article.setId(UUID.randomUUID().toString());
        }

        String sql = findById(article.getId()) != null
                ? "UPDATE articles SET title = ?, description = ?, content = ?, publish_date = ? WHERE id = ?"
                : "INSERT INTO articles (id, title, description, content, publish_date) VALUES (?, ?, ?, ?, ?)";

        if (sql.startsWith("UPDATE")) {
            jdbcTemplate.update(sql, article.getTitle(), article.getDescription(),
                    article.getContent(), article.getPublishDate(), article.getId());
        } else {
            jdbcTemplate.update(sql, article.getId(), article.getTitle(),
                    article.getDescription(), article.getContent(), article.getPublishDate());
        }

        return article;
    }

    public void deleteById(String id) {
        jdbcTemplate.update("DELETE FROM articles WHERE id = ?", id);
    }
}
