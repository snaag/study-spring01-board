package com.snaag.board_3.service;

import com.snaag.board_3.domain.Article;
import com.snaag.board_3.repository.MemoryArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {
    public MemoryArticleRepository memoryArticleRepository = new MemoryArticleRepository();
    public MemberService memberService = new MemberService(memoryArticleRepository);

    @AfterEach
    public void afterEach() {
        memberService.deleteAllArticles();
    }

    @Test
    public void 글쓰기() {
        // given 이러한 상황이 주어져서
        Article article = new Article();
        article.setName("sang-a");
        article.setTitle("spring");
        article.setContext("board demo");

        memberService.createArticle(article);

        // when 이걸 실행했을때
        Integer size = memberService.loadAllArticles().size();

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

        memberService.createArticle(article1);
        memberService.createArticle(article2);
        memberService.createArticle(article3);

        // when 이걸 실행했을때
        Integer size = memberService.loadAllArticles().size();

        // then 이런 결과가 나와야함
        assertThat(size).isEqualTo(3);
    }

    @Test
    public void 글_1개_불러오기() {
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

        Long id1 = memberService.createArticle(article1);
        memberService.createArticle(article2);
        memberService.createArticle(article3);

        // when 이걸 실행했을때
        Article loadArticle = memberService.loadOneArticle(id1).get();

        // then 이런 결과가 나와야함
        assertThat(loadArticle).isEqualTo(article1);
    }

    @Test
    public void 글_수정() {
        // given 이러한 상황이 주어져서
        Article article1 = new Article();
        article1.setName("sang-a");
        article1.setTitle("spring");
        article1.setContext("board demo");

        Article article2 = new Article();
        article2.setName("sang-a (2)");
        article2.setTitle("spring (2)");
        article2.setContext("board demo (2)");


        Long id1 = memberService.createArticle(article1);
        memberService.createArticle(article2);

        // when 이걸 실행했을때
        Article newArticle1 = new Article();
        newArticle1.setName("sang-a (updated)");
        newArticle1.setTitle("spring (updated)");
        newArticle1.setContext("board demo (updated)");

        Long updateArticleId = memberService.updateOneArticle(id1, newArticle1);

        // then 이런 결과가 나와야함
        assertThat(updateArticleId).isEqualTo(id1);
        assertThat(memberService.loadOneArticle(id1).get().getName())
            .isEqualTo("sang-a (updated)");
    }

    @Test
    public void 글_모두_삭제() {
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

        memberService.createArticle(article1);
        memberService.createArticle(article2);
        memberService.createArticle(article3);

        // when 이걸 실행했을때
        memberService.deleteAllArticles();

        // then 이런 결과가 나와야함
        assertThat(memberService.loadAllArticles().size())
            .isEqualTo(0);
    }

    @Test
    public void 글_1개_삭제() {
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

        Long id1 = memberService.createArticle(article1);
        memberService.createArticle(article2);
        memberService.createArticle(article3);

        // when 이걸 실행했을때
        memberService.deleteOneArticle(id1); // nullable...

        // then 이런 결과가 나와야함
        assertThat(memberService.loadAllArticles().size())
            .isEqualTo(2);
    }
}
