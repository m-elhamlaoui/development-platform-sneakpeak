package com.space.news.repository;

import com.space.news.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByTitle(String title);

    Optional<Object> findByUrl(String url);
}