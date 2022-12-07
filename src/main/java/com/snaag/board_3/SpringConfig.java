package com.snaag.board_3;

import com.snaag.board_3.repository.ArticleRepository;
import com.snaag.board_3.repository.JdbcArticleRepository;
import com.snaag.board_3.repository.JdbcTemplateArticleRepository;
import com.snaag.board_3.repository.MemoryArticleRepository;
import com.snaag.board_3.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // spring bean 에 등록해라
    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public ArticleRepository articleRepository() {
//        return new MemoryArticleRepository();
//        return new JdbcArticleRepository(dataSource);
        return new JdbcTemplateArticleRepository(dataSource);
    }

}
