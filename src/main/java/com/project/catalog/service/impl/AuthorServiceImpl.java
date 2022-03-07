package com.project.catalog.service.impl;

import com.project.catalog.domain.Author;
import com.project.catalog.domain.Book;
import com.project.catalog.dto.AuthorBookResponseDTO;
import com.project.catalog.dto.AuthorCreateRequestDTO;
import com.project.catalog.exception.ResourceNotFoundException;
import com.project.catalog.repository.AuthorRepository;
import com.project.catalog.repository.BookRepository;
import com.project.catalog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createNewAuthor(AuthorCreateRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));

        authorRepository.save(author);
    }

    @Override
    public List<AuthorBookResponseDTO> findAuthorBook(Long authorId) {
//        Author author = authorRepository.findById(authorId)
//                        .orElseThrow(()-> new ResourceNotFoundException("authorId.invalid"));
//        List<Book> books = bookRepository.findAllByAuthor(author);

//        List<Book> books = bookRepository.findAllByAuthorId(authorId);

        Author author = authorRepository.findById(authorId)
                        .orElseThrow(()-> new ResourceNotFoundException("authorId.invalid"));
        List<Book> books = author.getBooks();

        List<AuthorBookResponseDTO> dtos = books.stream().map((b)->{
            AuthorBookResponseDTO dto = new AuthorBookResponseDTO();
            dto.setBookName(b.getTitle());
            dto.setDescription(b.getDescription());
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }
}
