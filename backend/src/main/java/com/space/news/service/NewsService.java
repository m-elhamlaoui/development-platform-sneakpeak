package com.space.news.service;

import com.space.news.model.Article;
import com.space.news.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void importArticlesFromSpaceflightAPI() {
        String apiUrl = "https://api.spaceflightnewsapi.net/v4/articles";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");

        for (Map<String, Object> articleData : results) {
            String title = (String) articleData.get("title");
            String summary = (String) articleData.get("summary");
            String imageUrl = (String) articleData.get("image_url");
            String url = (String) articleData.get("url");

            // Skip if article with the same URL already exists
            if (articleRepository.findByUrl(url).isPresent()) continue;

            Article article = new Article();
            article.setTitle(title);
            article.setContent(summary);
            article.setImageUrl(imageUrl);
            article.setUrl(url);
            article.setSummary(summary); // optional field

            articleRepository.save(article);
        }
    }
}
