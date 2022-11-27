package com.snaag.board_3.service;

import com.snaag.board_3.domain.Article;
import com.snaag.board_3.repository.MemoryArticleRepository;

import java.util.Map;
import java.util.Optional;

public class MemberService {
    private final MemoryArticleRepository memoryArticleRepository;

    public MemberService(MemoryArticleRepository memoryArticleRepository) {
        this.memoryArticleRepository = memoryArticleRepository;
    }

    // 글 쓰기
    public Long createArticle(Article article) {
        Long id = memoryArticleRepository.create(article);
        return id;
    }

    // 글 모두 불러오기
    public Map<Long, Article> loadAllArticles() {
        return memoryArticleRepository.loadAll();
    }

    // 글 하나만 불러오기
    public Optional<Article> loadOneArticle(Long id) {
        Article article = memoryArticleRepository.loadOne(id).get();
        return Optional.ofNullable(article);
    }

    // 글 업데이트
    public Long updateOneArticle(Long updateId, Article article) {
        Long id = memoryArticleRepository.updateOne(updateId, article);
        return id;
    }

    // 글 삭제
    public void deleteOneArticle(Long id) {
        memoryArticleRepository.deleteOne(id);
    }

    // 글 모두 삭제
    public void deleteAllArticles() {
        memoryArticleRepository.deleteAll();
    }
}
