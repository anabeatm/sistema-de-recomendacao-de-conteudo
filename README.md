# Sistema de Recomendação de Conteúdo

![Java](https://img.shields.io/badge/Java-17-blue) ![JPA](https://img.shields.io/badge/JPA-Hibernate-green) ![Maven](https://img.shields.io/badge/Maven-3.8-red)

## 📖 Descrição

Este é um projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos (POO). O objetivo é construir um sistema de recomendação de conteúdo em Java, aplicando os princípios fundamentais da POO e utilizando o JPA para a persistência de dados.

O sistema permite que usuários cadastrados avaliem diferentes itens (como filmes, livros, etc.) com uma nota de 1 a 5. Com base nessas avaliações, o sistema gera recomendações personalizadas utilizando duas abordagens principais:

1.  **Filtragem Colaborativa:** Recomenda itens com base nas avaliações de usuários com gostos similares.
2.  **Filtragem Baseada em Conteúdo:** Recomenda itens com características parecidas com as dos itens que o usuário já avaliou positivamente.

Além disso, o sistema é capaz de gerar relatórios para análise, como a lista de itens mais populares e a taxa de acerto das recomendações geradas.

## ✨ Funcionalidades Principais

- **Cadastro de Usuários e Itens:** Gerenciamento completo (CRUD) de usuários e itens no sistema.
- **Avaliação de Itens:** Permite que um usuário atribua uma nota de 1 a 5 a um item.
- **Geração de Recomendações:**
    - Implementação de algoritmo de Filtragem Colaborativa.
    - Implementação de algoritmo de Filtragem Baseada em Conteúdo.
- **Geração de Relatórios:**
    - Listagem dos itens mais populares (com base na média de notas e/ou número de avaliações).
    - Cálculo e exibição da taxa de acerto das recomendações.

## 🛠️ Tecnologias Utilizadas

- **Linguagem Principal:** [Java (Versão 17 ou superior)](https://www.oracle.com/java/)
- **Persistência de Dados:** [JPA (Java Persistence API)](https://javaee.github.io/jpa-spec/) com implementação do [Hibernate](https://hibernate.org/).
- **Gerenciador de Dependências:** [Apache Maven](https://maven.apache.org/)
- **Banco de Dados:** MySQL

## 📐 Conceitos de POO Aplicados

Este projeto foi estruturado para seguir as melhores práticas e os pilares da Programação Orientada a Objetos:

- **Encapsulamento:** Os dados dos objetos são protegidos e acessados apenas através de métodos públicos, garantindo a integridade do estado interno das classes.
- **Abstração:** As classes são modeladas para representar entidades do mundo real (Usuário, Item, Avaliação) de forma simplificada, focando nos atributos e comportamentos essenciais.
- **Herança:** Pode ser utilizada para criar especializações de itens (ex: `Livro`, `Filme` herdando de `Item`).
- **Polimorfismo:** Pode ser aplicado em estratégias de recomendação, onde diferentes algoritmos implementam uma mesma interface `Recomendador`.
- **Princípios SOLID:** O código busca seguir os princípios SOLID para criar um software mais limpo, manutenível e extensível.

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [JDK 17 ou superior](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)
- Um SGBD de sua escolha (ex: PostgreSQL) ou nenhum, se for usar um banco em memória como o H2.

## 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/anabeatm/sistema-de-recomendacao-de-conteudo](https://github.com/anabeatm/sistema-de-recomendacao-de-conteudo)
    ```

2.  **Acesse a pasta do projeto:**
    ```bash
    cd anabeatm/sistema-de-recomendacao-de-conteudo
    ```

3.  **Configure o Banco de Dados:**
    As configurações de conexão com o banco de dados estão no arquivo:
    `src/main/resources/META-INF/persistence.xml`


4.  **Compile o projeto com o Maven:**
    ```bash
    mvn clean install
    ```

5.  **Execute a aplicação:**
    (A forma de execução pode variar. Se for uma aplicação de console, pode ser um comando específico)
    ```bash
    java -jar target/nome-do-seu-artefato.jar
    ```

## 📂 Estrutura do Projeto

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

## 👨‍💻 Autor

| [<br><sub>**Ana Beatriz Tavares Malaquias**</sub>](https://github.com/anabeatm) |
|:-------------------------------------------------------------------------------:|

Feito com ❤️ por Ana Beatriz Tavares Malaquias 👋🏽