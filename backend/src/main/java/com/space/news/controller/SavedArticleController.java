package com.space.news.controller;

import com.space.news.model.Article;
import com.space.news.auth.model.User;
import com.space.news.service.SavedArticleService;
import com.space.news.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saved")
public class SavedArticleController {

    @Autowired
    private SavedArticleService savedArticleService;

    @Autowired
    private UserUtil userUtil;

    @PostMapping("/{articleId}/save")
    public ResponseEntity<String> save(@PathVariable Long articleId) {
        User user = userUtil.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        boolean success = savedArticleService.saveArticle(user, articleId);
        return success
                ? ResponseEntity.ok("Article saved.")
                : ResponseEntity.badRequest().body("Article already saved or not found.");
    }

    @DeleteMapping("/{articleId}/remove")
    public ResponseEntity<String> remove(@PathVariable Long articleId) {
        User user = userUtil.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        boolean success = savedArticleService.removeSavedArticle(user, articleId);
        return success
                ? ResponseEntity.ok("Article removed.")
                : ResponseEntity.badRequest().body("Article not found or not saved.");
    }

    @GetMapping("/list")
    public ResponseEntity<?> listSavedArticles() {
        User user = userUtil.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        return ResponseEntity.ok(
                savedArticleService.getSavedArticlesForUser(user)
                        .stream()
                        .map(saved -> saved.getArticle())
                        .toList()
        );
    }

}
