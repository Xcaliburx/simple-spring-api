package com.project.catalog.repository;

import com.project.catalog.domain.Author;
import com.project.catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //select * from Book where title LIKE 'nama';
    List<Book> findAllByTitleLike(String title);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByAuthorId(Long authorId);
}
