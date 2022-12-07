package com.snaag.board_3.repository;

import com.snaag.board_3.domain.Article;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcArticleRepository implements ArticleRepository {

    private final DataSource dataSource;

    public JdbcArticleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Long create(Article article) {
        String sql = "insert into article(name, title, context) values(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, article.getName());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContext());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                article.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return article.getId();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    };

    @Override
    public List<Article> loadAll() {
        String sql = "select * from article";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Article> articles = new ArrayList<>();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setName(rs.getString("name"));
                article.setTitle(rs.getString("title"));
                article.setContext(rs.getString("context"));
                articles.add(article);
            }

            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, pstmt, rs);
        }
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

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
