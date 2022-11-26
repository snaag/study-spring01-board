package com.snaag.board_3.repository;

import com.snaag.board_3.domain.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryArticleRepositoryTest {
    public MemoryArticleRepository memoryArticleRepository = new MemoryArticleRepository();

    @AfterEach
    void afterEach() {
        memoryArticleRepository.deleteAll();
    }

    @Test
    void create() {
        // given 이러한 상황이 주어져서
        Article articleCreate1 = new Article();
        articleCreate1.setName("sang-a");
        articleCreate1.setTitle("spring");
        articleCreate1.setContext("demo 만들어보기");

        Article articleCreate2 = new Article();
        articleCreate2.setName("LEaps");
        articleCreate2.setTitle("C#");
        articleCreate2.setContext("unity 로 게임 만들어보기");

        // when 이걸 실행했을때
        Long id = memoryArticleRepository.create(articleCreate1);
        Article articleGet = memoryArticleRepository.loadOne(id).get();

        // then 이런 결과가 나와야함
        assertThat(articleCreate2).isNotEqualTo(articleGet);
        assertThat(articleCreate1).isEqualTo(articleGet);
    }


    @Test
    void loadAll() {
        // given 이러한 상황이 주어져서
        Article articleCreate1 = new Article();
        articleCreate1.setName("sang-a");
        articleCreate1.setTitle("spring");
        articleCreate1.setContext("demo 만들어보기");

        Article articleCreate2 = new Article();
        articleCreate2.setName("LEaps");
        articleCreate2.setTitle("C#");
        articleCreate2.setContext("unity 로 게임 만들어보기");

        memoryArticleRepository.create(articleCreate1);
        memoryArticleRepository.create(articleCreate2);

        // when 이걸 실행했을때
        Map<Long, Article> loadStore = memoryArticleRepository.loadAll();
        Integer size = loadStore.size();

        // then 이런 결과가 나와야함
        assertThat(size).isEqualTo(2);
        assertThat(size).isNotEqualTo(3);
    }

    @Test
    void loadOne() {
        // given 이러한 상황이 주어져서
        Article articleCreate1 = new Article();
        articleCreate1.setName("sang-a");
        articleCreate1.setTitle("spring");
        articleCreate1.setContext("demo 만들어보기");

        Article articleCreate2 = new Article();
        articleCreate2.setName("LEaps");
        articleCreate2.setTitle("C#");
        articleCreate2.setContext("unity 로 게임 만들어보기");

        memoryArticleRepository.create(articleCreate1);
        memoryArticleRepository.create(articleCreate2);

        // when 이걸 실행했을때
        Article articleGet1 = memoryArticleRepository.loadOne(1L).get();
        Article articleGet2 = memoryArticleRepository.loadOne(2L).get();

        // then 이런 결과가 나와야함
        assertThat(articleCreate1).isEqualTo(articleGet1);
        assertThat(articleCreate2).isEqualTo(articleGet2);
        assertThat(articleCreate1).isNotEqualTo(articleGet2);
    }

    @Test
    void updateOne() {
        // given 이러한 상황이 주어져서
        Article article = new Article();
        article.setName("sang-a");
        article.setTitle("spring");
        article.setContext("demo 만들어보기");

        Long id = memoryArticleRepository.create(article);

        // when 이걸 실행했을때
        Article articleUpdate = new Article();
        articleUpdate.setName("sang-a (수정됨)");
        articleUpdate.setTitle("spring (수정됨)");
        articleUpdate.setContext("demo 만들어보기 (수정됨)");

        Long updateId = memoryArticleRepository.updateOne(id, articleUpdate);

        // then 이런 결과가 나와야함
        assertThat(id).isEqualTo(updateId);
        assertThat(memoryArticleRepository.loadOne(id).get().getName())
            .isEqualTo("sang-a (수정됨)");
    }

    @Test
    void deleteAll() {
        // given 이러한 상황이 주어져서
        Article articleCreate1 = new Article();
        articleCreate1.setName("sang-a");
        articleCreate1.setTitle("spring");
        articleCreate1.setContext("demo 만들어보기");

        Article articleCreate2 = new Article();
        articleCreate2.setName("LEaps");
        articleCreate2.setTitle("C#");
        articleCreate2.setContext("unity 로 게임 만들어보기");

        memoryArticleRepository.create(articleCreate1);
        memoryArticleRepository.create(articleCreate2);

        // when 이걸 실행했을때
        memoryArticleRepository.deleteAll();

        // then 이런 결과가 나와야함
        Map<Long, Article> store = memoryArticleRepository.loadAll();
        assertThat(store.size()).isEqualTo(0);
    }

    @Test
    void deleteOne() {
        // given 이러한 상황이 주어져서
        Article articleCreate1 = new Article();
        articleCreate1.setName("sang-a");
        articleCreate1.setTitle("spring");
        articleCreate1.setContext("demo 만들어보기");

        Article articleCreate2 = new Article();
        articleCreate2.setName("LEaps");
        articleCreate2.setTitle("C#");
        articleCreate2.setContext("unity 로 게임 만들어보기");

        Long id1 = memoryArticleRepository.create(articleCreate1);
        memoryArticleRepository.create(articleCreate2);

        // when 이걸 실행했을때
        memoryArticleRepository.deleteOne(id1);

        // then 이런 결과가 나와야함
        assertThat(memoryArticleRepository.loadAll().size())
            .isEqualTo(1);
    }
}
