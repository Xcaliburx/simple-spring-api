package com.project.catalog.service.impl;

import antlr.StringUtils;
import com.project.catalog.domain.Author;
import com.project.catalog.domain.Book;
import com.project.catalog.dto.BookCreateRequestDTO;
import com.project.catalog.dto.BookDetailResponseDTO;
import com.project.catalog.dto.BookListResponseDTO;
import com.project.catalog.dto.BookUpdateRequestDTO;
import com.project.catalog.exception.ResourceNotFoundException;
import com.project.catalog.repository.AuthorRepository;
import com.project.catalog.repository.BookRepository;
import com.project.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<BookListResponseDTO> findBookAll(String title) {

        title = ObjectUtils.isEmpty(title)?"%":"%"+title+"%";
        List<Book> bookResponse = bookRepository.findAllByTitleLike(title);

        return bookResponse.stream().map((b)->{
            BookListResponseDTO dto = new BookListResponseDTO();
            dto.setTitle(b.getTitle());
            dto.setAuthor(b.getAuthor().getName());
            dto.setAuthorId(b.getAuthor().getId());
            dto.setId(b.getId());
            dto.setDescription(b.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewBook(BookCreateRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(()-> new ResourceNotFoundException("invalid.author_id"));
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());

        bookRepository.save(book);
    }

    @Override
    public BookDetailResponseDTO findBookDetail(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new ResourceNotFoundException("book.not.found"));
        BookDetailResponseDTO dto = new BookDetailResponseDTO();
        dto.setAuthor(book.getAuthor().getName());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        return dto;
    }

    @Override
    public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(()-> new ResourceNotFoundException("invalid.author_id"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new ResourceNotFoundException("book.not.found"));
        book.setAuthor(author);
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
