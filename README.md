# 🚀 **Spring Boot MovieAPI Backend**

---

The Spring Boot MovieAPI Backend is a powerful Java 17 application crafted to manage movie data efficiently. It prioritizes performance, security, and modularity, incorporating features like file handling, pagination, JWT authentication, and a three-layered architecture. Explore a seamless movie data experience with this backend! 🎥🍿

# 🚀 **Technology Used:**
---
- ☕ Java
- 🍃 Spring Boot
- 🌐 Spring MVC
- 🛢️ Spring Data JPA
- 📑 Pagination and Sorting
- 📁 File Handling
- 🔐 JWT Authentication
- 🐬 MySQL Database

# 🌟 Key Features

---

- **File Handling:**

  - 📁 Seamless media storage for efficient retrieval.
  - 🖼️ Manages images and other movie-related assets.

- **Pagination:**

  - 🔍 Enhances data presentation.
  - 🚀 Optimizes resource usage.
  - 📑 Retrieves data in manageable chunks for improved performance.

- **JWT Authentication:**

  - 🔐 Prioritizes security.
  - 🔑 Implements JSON Web Token (JWT) authentication.
  - 🛡️ Ensures only authorized users access and interact with sensitive movie data.

- **Three-Layered Architecture:**
  - 🏗️ Modular codebase for maintainability and scalability.
  - 🎯 Separation into presentation, business logic, and data access layers.
  - 🔍 Facilitates easier debugging, testing, and future enhancements.

## 🚀 **MovieAPI Endpoints**

---

- 🔗 **Add Movie:**

  - `POST http://localhost:8080/api/v1/movie/add-movie`

- 🔍 **Get All Movies:**

  - `GET http://localhost:8080/api/v1/movie/all`

- 🔍 **Get Movie by ID:**

  - `GET http://localhost:8080/api/v1/movie/1`

- 📁 **Get Profile Picture:**

  - `GET http://localhost:8080/file/profile-pic`

- 🔄 **Update Movie by ID:**

  - `PUT http://localhost:8080/api/v1/movie/update/1`

- ❌ **Delete Movie by ID:**

  - `DELETE http://localhost:8080/api/v1/movie/delete/2`

- 📑 **Get Movies with Pagination:**

  - `GET http://localhost:8080/api/v1/movie/allMoviesPage?pageNumber=0&pageSize=2`

- 📑 **Get Movies with Sorting:**

  - `GET http://localhost:8080/api/v1/movie/allMoviesPageSort?sortBy=title`

- 📝 **User Registration:**

  - `POST http://localhost:8080/api/v1/auth/register`

- 🔒 **User Login:**
  - `POST http://localhost:8080/api/v1/auth/login`

# 🚀 **Cloning the Project**

---

To kickstart your journey with the MovieAPI project, follow these simple steps:

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Vikashkatiyar/Movie_Api_Backend.git
   ```

2. **Navigate to the Project Directory:**

   ```bash
   cd Movie_Api_Backend
   ```

3. **Configure Application Properties:**

   - Open `src/main/resources/application.properties`.
   - Modify the database connection details to match your MySQL setup.

4. **Create MySQL Database:**

   - Execute the SQL script provided in `src/main/resources/sql-scripts` to set up the necessary database tables.
     ```bash
     mysql -u your_username -p < create_database.sql
     ```

5. **Build and Run the Application:**

   - Using Maven:
     ```bash
     ./mvnw clean install
     ./mvnw spring-boot:run
     ```

6. **Access the API Endpoints:**
   - Once the application is running, explore the MovieAPI by accessing the defined endpoints.

Now you're all set! Dive into the MovieAPI project, manage movie-related data effortlessly, and feel free to contribute or report issues. Happy coding! 🎬🍿

# 🌟 **Project Author**

## **<span style="color:red;">Vikash Katiyar</span>**

📧 **Email:** vikashktr018@gmail.com

Feel free to reach out to the author for any inquiries, feedback, or collaboration opportunities related to the MovieAPI project. Your input is valuable! 🚀
