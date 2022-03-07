package com.project.catalog.service;

import com.project.catalog.dto.BookCreateRequestDTO;
import com.project.catalog.dto.BookDetailResponseDTO;
import com.project.catalog.dto.BookListResponseDTO;
import com.project.catalog.dto.BookUpdateRequestDTO;

import java.util.List;


public interface BookService {

    public List<BookListResponseDTO> findBookAll(String title);

    public void createNewBook(BookCreateRequestDTO dto);

    public BookDetailResponseDTO findBookDetail(Long bookId);

    public void updateBook(Long bookId, BookUpdateRequestDTO dto);

    public void deleteBook(Long bookId);
}
