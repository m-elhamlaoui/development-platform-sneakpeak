package com.space.news.service;

import com.space.news.auth.model.User;
import com.space.news.model.Article;
import com.space.news.model.SavedArticle;
import com.space.news.repository.ArticleRepository;
import com.space.news.repository.SavedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedArticleService {

    @Autowired
    private SavedArticleRepository savedArticleRepo;

    @Autowired
    private ArticleRepository articleRepo;

    public List<SavedArticle> getSavedArticlesForUser(User user) {
        return savedArticleRepo.findByUser(user);
    }

    public boolean saveArticle(User user, Long articleId) {
        Article article = articleRepo.findById(articleId).orElse(null);
        if (article == null || savedArticleRepo.existsByUserAndArticle(user, article)) {
            return false;
        }
        savedArticleRepo.save(new SavedArticle(user, article));
        return true;
    }

    public boolean removeSavedArticle(User user, Long articleId) {
        Article article = articleRepo.findById(articleId).orElse(null);
        if (article == null) return false;

        var saved = savedArticleRepo.findByUserAndArticle(user, article);
        if (saved.isPresent()) {
            savedArticleRepo.delete(saved.get());
            return true;
        }
        return false;
    }
}
