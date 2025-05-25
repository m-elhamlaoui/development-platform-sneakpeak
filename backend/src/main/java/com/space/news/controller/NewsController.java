package com.space.news.controller;

import com.space.news.model.Article;
import com.space.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<Article> getAllArticles() {
        return newsService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return newsService.getArticleById(id);
    }

    @GetMapping("/import")
    public String importArticles() {
        newsService.importArticlesFromSpaceflightAPI();
        return "Articles imported successfully!";
    }
}
