package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            List<Author> authors = bookstoreService.fetchAuthorWithBooks();
            authors.forEach(a -> System.out.println("Author name: "
                    + a.getName() + " Books: " + a.getBooks()));

            List<Book> books = bookstoreService.fetchBookWithAuthor();
            books.forEach(b -> System.out.println("Book title: "
                    + b.getTitle() + " Author: " + b.getAuthor()));
        };
    }
}
/*
 * 
 * LEFT JOIN FETCH

See also:

How To Avoid LazyInitializationException Via JOIN FETCH
JOIN VS. JOIN FETCH
Description: Let's assume that we have two entities engaged in a one-to-many (or many-to-many) lazy bidirectional (or unidirectional) relationship (e.g., Author has more Book). And, we want to trigger a single SELECT that fetches all Author and the corresponding Book. This is a job for JOIN FETCH which is converted behind the scene into a INNER JOIN. Being an INNER JOIN, the SQL will return only Author that have Book. If we want to return all Author, including those that doesn't have Book, then we can rely on LEFT JOIN FETCH. Similar, we can fetch all Book, including those with no registered Author. This can be done via JOIN FETCH or JOIN.

Key points:

define two related entities (e.g., Author and Book in a one-to-many lazy bidirectional relationship)
write a JPQL LEFT JOIN FETCH to fetch all authors and books (fetch authors even if they don't have registered books)
write a JPQL LEFT JOIN FETCH to fetch all books and authors (fetch books even if they don't have registered authors)

 * 
 * 
 */
