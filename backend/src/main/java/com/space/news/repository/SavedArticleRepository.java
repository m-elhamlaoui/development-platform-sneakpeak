package com.space.news.repository;

import com.space.news.model.SavedArticle;
import com.space.news.auth.model.User;
import com.space.news.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedArticleRepository extends JpaRepository<SavedArticle, Long> {

    List<SavedArticle> findByUser(User user);

    boolean existsByUserAndArticle(User user, Article article);

    Optional<SavedArticle> findByUserAndArticle(User user, Article article);
}
