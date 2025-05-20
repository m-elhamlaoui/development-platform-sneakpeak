# 🚀 Smart Space News Aggregator

The Smart Space News Aggregator is a full-stack web platform that aggregates the latest space-related news and data from NASA and other APIs. It supports user accounts with a modern frontend and a Spring Boot backend.

---

## 🌌 Features

- 🔭 **Live NASA News** — Articles imported via a public API and served dynamically.
- 🛰️ **Mission Data Integration** — Real-time data on launches and expeditions retrieved via two public APIs from The Space Devs.
- 📅 **Keyword & Date Filter** — Intuitive frontend filters to narrow down space news by keyword or date.
- 📦 **Save-for-Later** — Logged-in users can bookmark articles to revisit later.
- 🪐 **View Saved Mode** — Toggle to show only saved articles.
- 🧠 **enhanced UX** — Articles are sorted by latest, with smooth animations, lazy loading, and a "Back to Top" experience.
- 🖼️ **Single Article View** — Dedicated page with full content and original source link.
- 🌍 **Public & Auth-Only Access** — Core data is publicly available; advanced features require login.

---

## 🛠️ Tech Stack

### Frontend
- HTML/CSS (custom + responsive grid)
- JavaScript (vanilla)
- Dynamic UI/UX: Lazy loading, scroll animation, live filtering

### Backend
- **Spring Boot**
- **Spring Security** (with in-memory & DB authentication)
- **JPA / Hibernate**
- **PostgreSQL**
- **REST API** to expose news, save/remove features
- **Third-party API Integration:**
  -- Nasa API for space-related articles.
  -- The Space Devs APIs for real-time launch and expedition data.
  -- APOD is a nasa api for Astronomy Picture Of the Day
  -- Nasa API for information about asteroids close to earth
---

## 🔐 User Features

- **Register / Login** system
- **Session-based Auth**
- **Save / Unsave Articles**
- Articles persist per user in database
- Feedback messages (ex: saved, already saved, must log in)

---

## 📦 Endpoints (API)

| Method | Endpoint                  | Description                     |
|--------|---------------------------|---------------------------------|
| GET    | `/api/articles`           | Fetch all articles              |
| POST   | `/api/articles/import`    | Import latest articles  |
| POST   | `/api/saved/{id}/save`    | Save article (auth required)   |
| DELETE | `/api/saved/{id}/remove`  | Unsave article (auth required) |
| GET    | `/api/saved/list`         | List saved articles             |
| GET    | `/api/launches`           | Get launch events               |
| GET    | `/api/expeditions`        | Get ongoing or planned expeditions              |

---


