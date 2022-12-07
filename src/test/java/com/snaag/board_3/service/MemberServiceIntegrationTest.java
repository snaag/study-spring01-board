package com.snaag.board_3.service;

import com.snaag.board_3.domain.Article;
import com.snaag.board_3.repository.ArticleRepository;
import com.snaag.board_3.repository.MemoryArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;


    @Test
    public void 글쓰기() {
        // given 이러한 상황이 주어져서
        Article article = new Article();
        article.setName("sang-a");
        article.setTitle("spring");
        article.setContext("board demo");

        articleService.createArticle(article);

        // when 이걸 실행했을때
        Integer size = articleService.loadAllArticles().size();

        // then 이런 결과가 나와야함
        assertThat(size).isEqualTo(1);
    }

    @Test
    public void 글_모두_불러오기() {
        // given 이러한 상황이 주어져서
        Article article1 = new Article();
        article1.setName("sang-a");
        article1.setTitle("spring");
        article1.setContext("board demo");

        Article article2 = new Article();
        article2.setName("sang-a (2)");
        article2.setTitle("spring (2)");
        article2.setContext("board demo (2)");

        Article article3 = new Article();
        article3.setName("sang-a (3)");
        article3.setTitle("spring (3)");
        article3.setContext("board demo (3)");

        articleService.createArticle(article1);
        articleService.createArticle(article2);
        articleService.createArticle(article3);

        // when 이걸 실행했을때
        Integer size = articleService.loadAllArticles().size();

        // then 이런 결과가 나와야함
        assertThat(size).isEqualTo(3);
    }

}
