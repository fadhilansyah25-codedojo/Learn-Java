package com.pembekalan.xsisacademy.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pembekalan.xsisacademy.dto.request.BookRequestDto;
import com.pembekalan.xsisacademy.dto.response.BookResponseDto;
import com.pembekalan.xsisacademy.entity.Author;
import com.pembekalan.xsisacademy.entity.Book;
import com.pembekalan.xsisacademy.entity.Category;
import com.pembekalan.xsisacademy.entity.Publisher;
import com.pembekalan.xsisacademy.repository.AuthorRepository;
import com.pembekalan.xsisacademy.repository.BookRepository;
import com.pembekalan.xsisacademy.repository.CategoryRepository;
import com.pembekalan.xsisacademy.repository.PublisherRepository;
import com.pembekalan.xsisacademy.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PublisherRepository publisherRepository;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();

        return books.stream()
                .map(book -> modelMapper().map(book, BookResponseDto.class)).collect(Collectors.toList());
    }

    public Page<BookResponseDto> getBooksPage(PageRequest pageRequest) {
        Page<Book> books = bookRepository.findAll(pageRequest);

        return books.map(book -> modelMapper().map(book, BookResponseDto.class));
    }

    @Override
    public BookResponseDto getBookById(Integer id) {
        return modelMapper().map(bookRepository.findById(id).orElse(null), BookResponseDto.class);
    }

    @Override
    public BookResponseDto saveBook(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.getAuthor_id()).orElse(null);
        Category category = categoryRepository.findById(bookRequestDto.getCategory_id()).orElse(null);
        Publisher publisher = publisherRepository.findById(bookRequestDto.getPublisher_id()).orElse(null);

        Book book = modelMapper().map(bookRequestDto, Book.class);

        book.setAuthor(author);
        book.setCategory(category);
        book.setPublisher(publisher);

        bookRepository.save(book);

        return modelMapper().map(book, BookResponseDto.class);
    }

    @Override
    public BookResponseDto editBook(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.getAuthor_id()).orElse(null);
        Category category = categoryRepository.findById(bookRequestDto.getCategory_id()).orElse(null);
        Publisher publisher = publisherRepository.findById(bookRequestDto.getPublisher_id()).orElse(null);

        Book book = modelMapper().map(bookRequestDto, Book.class);

        book.setId(bookRequestDto.getId());
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublisher(publisher);

        bookRepository.save(book);

        return modelMapper().map(book, BookResponseDto.class);
    }

    @Override
    public void deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);

        book.setIsDeleted(true);

        bookRepository.save(book);
    }

}
