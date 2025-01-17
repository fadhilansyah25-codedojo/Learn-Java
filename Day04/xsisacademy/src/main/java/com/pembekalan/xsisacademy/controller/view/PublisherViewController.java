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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.pembekalan.xsisacademy.config.ApiConfig;
import com.pembekalan.xsisacademy.dto.request.PublisherRequestDto;
import com.pembekalan.xsisacademy.dto.response.ApiResponse;
import com.pembekalan.xsisacademy.dto.response.PublisherResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Configuration
@RequestMapping("/publisher")
public class PublisherViewController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("")
    @SuppressWarnings("null")
    public String getAllPublisher(Model model) {
        String title = "Publishers Page";

        ResponseEntity<ApiResponse<List<PublisherResponseDto>>> response = restTemplate.exchange(ApiConfig.PUBLISHERS,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResponse<List<PublisherResponseDto>>>() {
                });

        List<PublisherResponseDto> publishers = response.getBody().getData();

        model.addAttribute("publishers", publishers);
        model.addAttribute("title", title);

        return "publisher/index";
    }

    @GetMapping("/form")
    public String bookForm(Model model) {
        PublisherRequestDto publisherRequestDto = new PublisherRequestDto();

        model.addAttribute("publisher", publisherRequestDto);

        return "publisher/form";
    }

    @PostMapping("/save")
    public String savePublisher(@ModelAttribute PublisherRequestDto publisherRequestDto, BindingResult result) {
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build the request body
        HttpEntity<PublisherRequestDto> requestEntity = new HttpEntity<>(publisherRequestDto, headers);

        ResponseEntity<ApiResponse<PublisherResponseDto>> response = restTemplate.exchange(
                ApiConfig.PUBLISHERS,
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<ApiResponse<PublisherResponseDto>>() {
                });

        return "redirect:/publisher";
    }

    @GetMapping("/edit/{id}")
    @SuppressWarnings("null")
    public String editPublisher(Model model, @PathVariable Integer id) {
        ResponseEntity<ApiResponse<PublisherResponseDto>> response = restTemplate.exchange(
                ApiConfig.PUBLISHERS + "/" + id,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResponse<PublisherResponseDto>>() {
                });

        model.addAttribute("publisher", response.getBody().getData());
        return "publisher/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(Model model, @PathVariable Integer id) {
        ResponseEntity<ApiResponse<?>> response = restTemplate.exchange(
                ApiConfig.PUBLISHERS + "/" + id,
                HttpMethod.DELETE, null,
                new ParameterizedTypeReference<ApiResponse<?>>() {
                });

        return "redirect:/publisher";
    }

}
