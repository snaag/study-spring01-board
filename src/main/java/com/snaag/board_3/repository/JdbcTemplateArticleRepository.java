package com.snaag.board_3.repository;

import com.snaag.board_3.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateArticleRepository implements ArticleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long create(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", article.getName());
        parameters.put("title", article.getTitle());
        parameters.put("context", article.getContext());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setId(key.longValue());
        return article.getId();
    }

    @Override
    public List<Article> loadAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public Optional<Article> loadOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Long updateOne(Long id, Article article) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteOne(Long id) {

    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setName(rs.getString("name"));
            article.setTitle(rs.getString("title"));
            article.setContext(rs.getString("context"));
            return article;
        };
    }
}
