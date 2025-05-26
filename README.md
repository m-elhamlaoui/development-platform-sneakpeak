# 🚀 Space News Aggregator Backend

This is the backend for the **Space News Aggregator** platform — a Spring Boot-based API that provides access to real-time space-related news, article management for authenticated users, and intelligent responses via an integrated Gemini AI chatbot.
Devops project in this github repo : https://github.com/hibaalaoui/DevOps-Space-news-aggregator

## 🌌 Features — In Detail

### 🔭 Live NASA News
The application fetches up-to-date articles from NASA and other space news providers using public APIs. These articles are displayed dynamically on the homepage, ensuring that users always see the latest developments in space exploration and astronomy without needing to refresh or search manually.

### 🛰️ Mission Data Integration
Real-time mission and launch data is retrieved using APIs from The Space Devs, offering users access to detailed information on upcoming, ongoing, and past space missions. This includes launch times, mission names, involved agencies, and live status updates — bringing authoritative mission data into one centralized platform.

### 📅 Keyword & Date Filter
Users can refine their article feed using powerful and intuitive filters:
- **Keyword Search**: Search for specific topics (e.g., “Mars”, “Black Hole”).
- **Date Filter**: Display articles published within a specific timeframe.
This ensures users find exactly what they're looking for, improving relevance and user satisfaction.

### 📦 Save-for-Later
Logged-in users can bookmark articles by clicking a “Save” or “Bookmark” icon. These articles are stored in their personal profile, enabling them to return and read later. This is ideal for research, study, or casual catching up.

### 🪐 View Saved Mode
A special **toggle switch** allows users to quickly shift the view from all articles to only those they have saved. This “Saved Mode” provides a focused reading experience, acting like a curated library or reading list.

### 🧠 Enhanced UX
The frontend offers a smooth and modern user experience, thanks to:
- **Sorted by Recency**: Most recent articles appear first.
- **Smooth Animations**: UI transitions are pleasant and professional.
- **Lazy Loading**: Articles load as the user scrolls, improving performance.
- **"Back to Top" Button**: Easily return to the top of the page after scrolling.
All of this ensures that users interact with content fluidly and comfortably.

### 🖼️ Single Article View
Each article can be opened in a **dedicated detail page** showing:
- Full article content
- Images and metadata
- Original source link
This allows users to get in-depth knowledge without leaving the platform while still retaining access to the source for verification or further reading.

### 🌍 Public & Auth-Only Access
- **Public Access**: Browsing news and mission data does not require login.
- **Authenticated Access**: Saving articles, accessing the chatbot, and managing preferences require user authentication.
This dual-level access encourages wider usage while still offering enhanced features to registered users.

### 💬 Space Q&A Chatbot
An interactive chatbot powered by **Gemini API** allows users to:
- Ask any space-related question (e.g., “What is the next NASA mission?”)
- Receive AI-generated answers based on live article context
This feature makes the platform educational and interactive, offering a unique blend of information retrieval and conversational AI.


### ✅ User Authentication
- Registration and login via JWT-secured endpoints
- User-specific article management and actions

### 🛰️ News Aggregation
- Fetches up-to-date space news articles from public APIs
- Parses and stores selected articles in a structured format

### 🗂️ Article Management
- Save/un-save articles per user
- View saved articles and retrieve details on demand

### 🤖 Gemini AI Integration
- Intelligent chatbot support through Gemini AI
- Chat endpoint allows contextual question-answering based on fetched articles
---

## 🧱 Project Structure

```
backend/
├── src/
│   └── main/
│       ├── java/com/space/news/
│       │   ├── auth/             # Authentication logic
│       │   ├── config/           # Application-wide configuration (CORS, beans)
│       │   ├── controller/       # REST API endpoints
│       │   ├── model/            # Entity definitions
│       │   ├── repository/       # Spring JPA interfaces
│       │   ├── service/          # Business logic and integrations
│       │   └── util/             # Utility functions
│       └── resources/            # application.properties, etc.
├── pom.xml                       # Maven project definition
```

---

## 🔐 Authentication

JWT-based security for all protected routes under `/api/v1/saved-articles`. Login and signup endpoints are public.

```
POST   /api/v1/auth/register
POST   /api/v1/auth/authenticate
```

---

## 🔧 API Endpoints

### News
- `GET /api/v1/news`: Fetch latest space articles

### Saved Articles
- `POST /api/v1/saved-articles`: Save an article
- `GET /api/v1/saved-articles`: Get all saved articles by user
- `DELETE /api/v1/saved-articles/{id}`: Delete saved article

### Gemini AI Chat
- `POST /api/v1/gemini/chat`: Send a prompt and receive a generated response

---

## ⚙️ Configuration

Edit `application.properties` or `application.yml` for:
- Database connection settings
- JWT secret
- Gemini API key

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL (or modify application.properties for your DB)

### Run the app

```bash
./mvnw spring-boot:run
```

### Build the project

```bash
./mvnw clean install
```

---

## 🧪 Testing

Run all unit and integration tests:

```bash
./mvnw test
```

---

## 📌 Technologies Used

- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- Gemini API (AI chatbot)
- PostgreSQL
- Maven

---


## 🙌 Contributions

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## 👨‍💻 Author

**Space Dev Team**  
<a href="https://github.com/rachidait996">Rachid Ait Lmaati</a><br>
<a href="https://github.com/YasBa">Yassir BABA</a><br>
<a href="https://github.com/asmaaitnasser">Asmaa ait nasser</a><br>
<a href="https://github.com/hibaalaoui">Hiba alaoui</a>


