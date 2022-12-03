package com.snaag.board_3.repository;

import com.snaag.board_3.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    // C
    Long create(Article article);

    // R _ all
    List<Article> loadAll();

    // R _ one
    Optional<Article> loadOne(Long id);

    // U
    Long updateOne(Long id, Article article);

    // D _ all
    void deleteAll();

    // D _ one
    void deleteOne(Long id);
}
