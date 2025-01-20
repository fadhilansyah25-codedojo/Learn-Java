package com.pembekalan.xsisacademy.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pembekalan.xsisacademy.dto.request.AuthorRequestDto;
import com.pembekalan.xsisacademy.dto.response.AuthorResponseDto;
import com.pembekalan.xsisacademy.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorViewController {
    @Autowired
    AuthorService authorService;

    @GetMapping("")
    public ModelAndView getAllAuthors() {
        ModelAndView view = new ModelAndView("author/index");

        List<AuthorResponseDto> authorResponseDtos = authorService.getAllAuthors();

        view.addObject("authors", authorResponseDtos);

        String title = "Authors Page";

        view.addObject("title", title);

        return view;
    }

    @GetMapping("/form")
    public ModelAndView authorForm() {
        ModelAndView view = new ModelAndView("author/form");
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        view.addObject("author", authorResponseDto);

        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute AuthorRequestDto authorRequestDto, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                authorService.saveAuthor(authorRequestDto);
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        return new ModelAndView("redirect:/author");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editAuthor(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("author/form");

        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);

        view.addObject("author", authorResponseDto);

        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);

        return new ModelAndView("redirect:/author");
    }
}
