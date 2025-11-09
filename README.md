# Content Recommendation System

![Java](https://img.shields.io/badge/Java-17-blue) ![JPA](https://img.shields.io/badge/JPA-Hibernate-green) ![Maven](https://img.shields.io/badge/Maven-3.8-red)

## 📖 Description

This is an academic project developed for the Object-Oriented Programming (OOP) course. The goal is to build a content recommendation system in Java, applying fundamental OOP principles and using JPA for data persistence.

The system allows registered users to rate items (such as movies and musics) with a score from 1 to 5. Based on these ratings, the system generates personalized recommendations using two main approaches:

1. **Collaborative Filtering:** Recommends items based on ratings from users with similar preferences. [in progress]
2. **Content-Based Filtering:** Recommends items that share characteristics with items the user has positively rated. [in progress]

Additionally, the system can generate analytical reports, such as the list of most popular items and the accuracy of the generated recommendations. [in progress]

## ✨ Main Features

- **User and Item Management:** Full CRUD operations.
- **Item Rating:** Users can assign scores from 1 to 5.
- **Recommendation Generation:**
  - Collaborative Filtering algorithm
  - Content-Based Filtering algorithm
- **Report Generation:**
  - Most popular items (based on average rating and/or number of ratings)
  - Recommendation accuracy

## 🛠️ Technologies Used

- **Language:** Java 17+
- **Persistence:** JPA (Hibernate)
- **Dependencies:** Apache Maven
- **Database:** MySQL

## 📐 Applied OOP Concepts

- **Encapsulation:** Objects keep their internal state protected.
- **Abstraction:** Real-world entities represented by simplified classes.
- **Inheritance:** Possible specializations (e.g., `Film`, `Movie` inheriting from `Item`).
- **Polymorphism:** Recommendation strategies implementing a common `Recommender` interface.
- **SOLID Principles:** Ensuring clean, maintainable, and extensible code.

## ⚙️ Requirements

- JDK 17+
- Apache Maven
- Git
- A DBMS (e.g., PostgreSQL) or an in-memory DB like H2

## 🚀 How to Run the Project

1. **Clone the repository:**
    ```bash
    git clone https://github.com/anabeatm/sistema-de-recomendacao-de-conteudo
    ```

2. **Navigate into the project folder:**
    ```bash
    cd anabeatm/sistema-de-recomendacao-de-conteudo
    ```

3. **Configure the Database:**  
   Edit the connection settings in:
   ```bash  
   src/main/resources/META-INF/persistence.xml
   ```

5. **Build the project:**
    ```bash
    mvn clean install
    ```

6. **Run the application:**  
    ```bash
    java -jar target/your-artifact-name.jar
    ```

## 📂 Project Structure

```
/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── domain/             // Entidades JPA (User, Item, Rating)
│   │   │   └── dao/                // Classes de acesso a dados (DAO)
│   │   │   └── service/            // Lógica de negócio (RecommendationService)
│   │   │   └── view/
│   │   │   └── main      
│   │   │       └── Main.java       // Classe principal da aplicação
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml // Configuração do JPA
│   └── test/
│       └── resources/
└── pom.xml                         // Arquivo de configuração do Maven
```
## 👨‍💻 Author

| [<br><sub>**Ana Beatriz Tavares Malaquias**</sub>](https://github.com/anabeatm) |
|:-------------------------------------------------------------------------------:|

Made with ❤️ by Ana Beatriz Tavares Malaquias 👋🏽
