package com.snaag.board_3.service;

import com.snaag.board_3.domain.Article;
import com.snaag.board_3.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SpringConfig 에서 Spring bean 에 등록을 해줬기 때문에 삭제함
//@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 글 쓰기
    public Long createArticle(Article article) {
        Long id = articleRepository.create(article);
        return id;
    }

    // 글 모두 불러오기
    public List<Article> loadAllArticles() {
        return articleRepository.loadAll();
    }

    // 글 하나만 불러오기
    public Optional<Article> loadOneArticle(Long id) {
        return articleRepository.loadOne(id);
    }

    // 글 업데이트
    public Long updateOneArticle(Long updateId, Article article) {
        Long id = articleRepository.updateOne(updateId, article);
        return id;
    }

    // 글 삭제
    public void deleteOneArticle(Long id) {
        articleRepository.deleteOne(id);
    }

    // 글 모두 삭제
    public void deleteAllArticles() {
        articleRepository.deleteAll();
    }
}
