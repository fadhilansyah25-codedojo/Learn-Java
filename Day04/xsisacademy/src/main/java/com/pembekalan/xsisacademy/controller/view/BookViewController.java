package com.pembekalan.xsisacademy.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.pembekalan.xsisacademy.config.ApiConfig;
import com.pembekalan.xsisacademy.dto.request.BookRequestDto;
import com.pembekalan.xsisacademy.dto.response.ApiResponse;
import com.pembekalan.xsisacademy.dto.response.AuthorResponseDto;
import com.pembekalan.xsisacademy.dto.response.BookResponseDto;
import com.pembekalan.xsisacademy.dto.response.PublisherResponseDto;
import com.pembekalan.xsisacademy.entity.Category;
import com.pembekalan.xsisacademy.repository.CategoryRepository;
import com.pembekalan.xsisacademy.service.AuthorService;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Configuration
@RequestMapping("/book")
public class BookViewController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    @SuppressWarnings("null")
    public String getAllBooks(Model model) {
        String title = "Books Page";

        ResponseEntity<ApiResponse<List<BookResponseDto>>> response = restTemplate.exchange(ApiConfig.BOOKS,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResponse<List<BookResponseDto>>>() {
                });

        model.addAttribute("books", response.getBody().getData());
        model.addAttribute("title", title);
        return "book/index";
    }

    @GetMapping("/form")
    @SuppressWarnings("null")
    public String bookForm(Model model) {
        BookRequestDto bookRequestDto = new BookRequestDto();

        ResponseEntity<ApiResponse<List<PublisherResponseDto>>> response = restTemplate.exchange(ApiConfig.PUBLISHERS,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResponse<List<PublisherResponseDto>>>() {
                });

        List<PublisherResponseDto> publishers = response.getBody().getData();

        List<AuthorResponseDto> authors = authorService.getAllAuthors();

        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("book", bookRequestDto);
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);

        return "book/form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute BookRequestDto bookRequestDto, BindingResult result) {
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build the request body
        HttpEntity<BookRequestDto> requestEntity = new HttpEntity<>(bookRequestDto, headers);

        ResponseEntity<ApiResponse<BookResponseDto>> response = restTemplate.exchange(
                ApiConfig.BOOKS,
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<ApiResponse<BookResponseDto>>() {
                });

        return "redirect:/book";
    }

    @SuppressWarnings("null")
    @GetMapping("/edit/{id}")
    public String editBook(Model model, @PathVariable("id") Integer id) {
        ResponseEntity<ApiResponse<List<PublisherResponseDto>>> Publisherresponse = restTemplate.exchange(
                ApiConfig.PUBLISHERS,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResponse<List<PublisherResponseDto>>>() {
                });

        List<PublisherResponseDto> publishers = Publisherresponse.getBody().getData();

        List<AuthorResponseDto> authors = authorService.getAllAuthors();

        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);

        ResponseEntity<ApiResponse<BookResponseDto>> response = restTemplate.exchange(ApiConfig.BOOKS + "/" + id,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResponse<BookResponseDto>>() {
                });

        BookResponseDto bookResponseDto = response.getBody().getData();

        model.addAttribute("book", bookResponseDto);
        model.addAttribute("selectedAuthorId", bookResponseDto.getAuthor().getId());
        model.addAttribute("selectedPublisherId",
                bookResponseDto.getPublisher().getId());
        model.addAttribute("selectedCategoryId",
                bookResponseDto.getCategory().getId());

        return "book/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(Model model, @PathVariable Integer id) {
        ResponseEntity<ApiResponse<?>> response = restTemplate.exchange(
                ApiConfig.BOOKS + "/" + id,
                HttpMethod.DELETE, null,
                new ParameterizedTypeReference<ApiResponse<?>>() {
                });

        return "redirect:/book";
    }

}
