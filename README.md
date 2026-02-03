 🔗 URL Shortener Backend

A **Spring Boot** backend application that converts long URLs into short URLs and redirects users to the original URLs using RESTful APIs.

## 🚀 Features
- Generate short URLs from long URLs  
- Redirect short URL to original URL  
- RESTful API design  
- Layered architecture (Controller, Service, Repository)  
- MySQL database integration  

## 🛠 Tech Stack
Java | Spring Boot | Spring Web | Spring Data JPA | MySQL | Maven | Git

## 🔗 API Endpoints

**Create Short URL**  
`POST /api/url/shorten`

# URL Shortener API

Simple Spring Boot backend for a URL shortener with auth endpoints.

## Base URL
`http://localhost:8080`

## Authentication Endpoints
\- **Register**  
  - Method: `POST`  
  - Path: `/api/auth/public/register`  
  - Auth: public  
  - Request JSON example:
    ```json
    {
      "username": "alice",
      "email": "alice@example.com",
      "password": "securePassword"
    }
    ```
  - Response: `200 OK` message on success

\- **Login**  
  - Method: `POST`  
  - Path: `/api/auth/public/login`  
  - Auth: public  
  - Request JSON example:
    ```json
    {
      "username": "alice",
      "password": "securePassword"
    }
    ```
  - Response: authentication token / user info (depending on implementation)

## URL Shortening Endpoints (typical)
Use these if implemented in the backend.

\- **Create Short URL**  
  - Method: `POST`  
  - Path: `/api/urls`  
  - Auth: required  
  - Request JSON example:
    ```json
    {
      "originalUrl": "https://example.com/very/long/url",
      "customAlias": "optional-alias"
    }
    ```
  - Response: shortened URL object (shortCode, shortUrl, originalUrl, createdAt)

\- **Redirect Short URL**  
  - Method: `GET`  
  - Path: `/{shortCode}`  
  - Auth: public  
  - Behavior: HTTP 302 redirect to `originalUrl`

\- **Get URL Stats**  
  - Method: `GET`  
  - Path: `/api/urls/{shortCode}/stats`  
  - Auth: required (owner or admin)  
  - Response: clicks, createdAt, lastAccessed, referrers (if tracked)

\- **List User URLs**  
  - Method: `GET`  
  - Path: `/api/user/urls`  
  - Auth: required  
  - Response: list of user's shortened URLs

\- **Delete URL**  
  - Method: `DELETE`  
  - Path: `/api/urls/{id}`  
  - Auth: required (owner or admin)  
  - Response: deletion confirmation

## Notes
\- Replace base URL and auth details as needed for production.  
\- Ensure CORS and security configuration allow the React frontend to call these endpoints.


⚙️ Run Locally
git clone https://github.com/your-username/url-shortener-backend.git
cd url-shortener-backend
mvn spring-boot:run
📌 Future Enhancements
JWT auth, URL expiry, analytics, custom URLs, Redis caching

👨‍💻 Author
Priyanshu raj
