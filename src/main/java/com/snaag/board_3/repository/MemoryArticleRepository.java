package com.snaag.board_3.repository;

import com.snaag.board_3.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryArticleRepository implements ArticleRepository {
    private static Map<Long, Article> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Long create(Article article) {
        updateSequence();
        store.put(sequence, article);
        return sequence;
    }

    public void updateSequence() {
        sequence++;
    }

    @Override
    public Map<Long, Article> loadAll() {
        return store;
    }

    @Override
    public Optional<Article> loadOne(Long id) {
        // https://mangkyu.tistory.com/70
//        Optional<Article> optional = Optional.ofNullable(store.get(id));
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Long updateOne(Long id, Article article) {
        store.put(id, article);
        return id;
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public void deleteOne(Long id) {
        store.remove(id);
    }
}
