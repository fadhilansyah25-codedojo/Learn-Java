package com.pembekalan.xsisacademy;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.pembekalan.xsisacademy.entity.Author;
import com.pembekalan.xsisacademy.entity.Book;
import com.pembekalan.xsisacademy.entity.Category;
import com.pembekalan.xsisacademy.entity.Publisher;
import com.pembekalan.xsisacademy.entity.User;
import com.pembekalan.xsisacademy.repository.AuthorRepository;
import com.pembekalan.xsisacademy.repository.BookRepository;
import com.pembekalan.xsisacademy.repository.CategoryRepository;
import com.pembekalan.xsisacademy.repository.PublisherRepository;
import com.pembekalan.xsisacademy.repository.UserRepository;

@SpringBootApplication
public class XsisacademyApplication {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PublisherRepository publisherRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(XsisacademyApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Category thriller = new Category("Thriller");
			Category novel = new Category("Novel");
			Category horror = new Category("Horror");

			categoryRepository.save(thriller);
			categoryRepository.save(horror);
			categoryRepository.save(novel);

			Faker faker = new Faker(Locale.forLanguageTag("id-ID"));

			for (int i = 0; i < 10; i++) {
				User userSeed = new User(
						faker.name().fullName(),
						faker.phoneNumber().phoneNumber(),
						faker.address().streetAddress() + " " + faker.address().city());

				userRepository.save(userSeed);
			}

			for (int i = 0; i < 10; i++) {
				Publisher publisherSeed = new Publisher(
						faker.book().publisher(),
						faker.address().streetAddress() + " " + faker.address().city());
				publisherRepository.save(publisherSeed);
			}

			for (int i = 0; i < 10; i++) {
				Author authorSeed = new Author(
						faker.book().author());
				authorRepository.save(authorSeed);
			}

			Random random = new Random();

			for (int i = 0; i < 10; i++) {

				Author authorSeed = authorRepository.findById(random.nextInt(10) + 1).orElse(null);
				Publisher publisherSeed = publisherRepository.findById(random.nextInt(10) + 1).orElse(null);
				Category categorySeed = categoryRepository.findById(random.nextInt(3) + 1).orElse(null);

				Book bookSeed = new Book(
						authorSeed,
						categorySeed,
						publisherSeed,
						faker.book().title(),
						faker.book().title(),
						(random.nextInt(100) + 1),
						faker.date().between(new Date(2012 - 1900, 0, 1), new Date(2024 - 1900, 12, 1)));

				bookRepository.save(bookSeed);
			}

		};
	};
}
