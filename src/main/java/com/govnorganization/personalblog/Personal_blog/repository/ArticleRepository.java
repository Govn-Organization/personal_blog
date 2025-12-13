package com.govnorganization.personalblog.Personal_blog.repository;

import com.govnorganization.personalblog.Personal_blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * ArticleRepository - handles database operations using Spring JDBC
 * This class uses JdbcTemplate to interact with the database
 */
@Repository  // This tells Spring: "This is a repository class for database operations"
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor - Spring automatically provides JdbcTemplate
    @Autowired
    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * RowMapper - converts database rows to Article objects
     * This is like a translator: database row -> Java object
     */
    private static class ArticleRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setId(rs.getString("id"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            article.setContent(rs.getString("content"));
            // Convert SQL DATE to LocalDate
            article.setPublishDate(rs.getDate("publish_date").toLocalDate());
            return article;
        }
    }

    /**
     * Get all articles from the database, sorted by date (newest first)
     */
    public List<Article> findAll() {
        String sql = "SELECT * FROM articles ORDER BY publish_date DESC";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    /**
     * Find an article by its ID
     */
    public Article findById(String id) {
        String sql = "SELECT * FROM articles WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), id);
        } catch (Exception e) {
            // If article not found, return null
            return null;
        }
    }

    /**
     * Save an article to the database
     * If article has no ID, creates a new one
     * If article exists, updates it; otherwise inserts new record
     */
    public Article save(Article article) {
        // If article doesn't have an ID, create one
        if (article.getId() == null || article.getId().isEmpty()) {
            article.setId(UUID.randomUUID().toString());
        }

        // Check if article already exists
        Article existing = findById(article.getId());
        
        if (existing != null) {
            // Update existing article
            String sql = "UPDATE articles SET title = ?, description = ?, content = ?, publish_date = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    article.getTitle(),
                    article.getDescription(),
                    article.getContent(),
                    article.getPublishDate(),
                    article.getId());
        } else {
            // Insert new article
            String sql = "INSERT INTO articles (id, title, description, content, publish_date) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    article.getId(),
                    article.getTitle(),
                    article.getDescription(),
                    article.getContent(),
                    article.getPublishDate());
        }
        
        return article;
    }

    /**
     * Delete an article by ID
     */
    public void deleteById(String id) {
        String sql = "DELETE FROM articles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

