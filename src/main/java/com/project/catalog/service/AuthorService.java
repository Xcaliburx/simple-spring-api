package com.project.catalog.service;

import com.project.catalog.dto.AuthorBookResponseDTO;
import com.project.catalog.dto.AuthorCreateRequestDTO;

import java.util.List;

public interface AuthorService {

    public void createNewAuthor(AuthorCreateRequestDTO dto);

    public List<AuthorBookResponseDTO> findAuthorBook(Long authorId);
}
