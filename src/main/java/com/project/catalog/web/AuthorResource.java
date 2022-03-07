package com.project.catalog.web;

import com.project.catalog.dto.AuthorBookResponseDTO;
import com.project.catalog.dto.AuthorCreateRequestDTO;
import com.project.catalog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AuthorResource {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<Void> createNewAuthor(@RequestBody AuthorCreateRequestDTO dto) throws URISyntaxException {
        authorService.createNewAuthor(dto);
        return ResponseEntity.created(new URI("/v1/author")).build();
    }

    @GetMapping("/author/{authorId}/book")
    public ResponseEntity<List<AuthorBookResponseDTO>> findAuthorBook(@PathVariable Long authorId){
        List<AuthorBookResponseDTO> result = authorService.findAuthorBook(authorId);
        return ResponseEntity.ok().body(result);
    }
}
