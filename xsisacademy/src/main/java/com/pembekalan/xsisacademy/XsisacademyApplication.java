package com.pembekalan.xsisacademy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.pembekalan.xsisacademy.entity.Author;
import com.pembekalan.xsisacademy.entity.Book;
import com.pembekalan.xsisacademy.entity.Category;
import com.pembekalan.xsisacademy.entity.EndpointPermission;
import com.pembekalan.xsisacademy.entity.Permission;
import com.pembekalan.xsisacademy.entity.Publisher;
import com.pembekalan.xsisacademy.entity.Role;
import com.pembekalan.xsisacademy.entity.User;
import com.pembekalan.xsisacademy.repository.AuthorRepository;
import com.pembekalan.xsisacademy.repository.BookRepository;
import com.pembekalan.xsisacademy.repository.CategoryRepository;
import com.pembekalan.xsisacademy.repository.EndpointPermissionRepository;
import com.pembekalan.xsisacademy.repository.PermissionRepository;
import com.pembekalan.xsisacademy.repository.PublisherRepository;
import com.pembekalan.xsisacademy.repository.UserRepository;
import com.pembekalan.xsisacademy.repository.RoleRepository;

@EnableCaching
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

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private EndpointPermissionRepository endpointPermissionRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

			for (int i = 0; i < 25; i++) {

				Author authorSeed = authorRepository.findById(random.nextInt(10) + 1).orElse(null);
				Publisher publisherSeed = publisherRepository.findById(random.nextInt(10) + 1).orElse(null);
				Category categorySeed = categoryRepository.findById(random.nextInt(3) + 1).orElse(null);

				long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
				long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
				long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
				LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

				Book bookSeed = new Book(
						authorSeed,
						categorySeed,
						publisherSeed,
						faker.book().title(),
						faker.book().title(),
						(random.nextInt(100) + 1),
						randomDate);

				bookRepository.save(bookSeed);
			}

			// 1. Buat Permissions
			Permission userRead = new Permission();
			userRead.setName("USER_READ");
			permissionRepository.save(userRead);

			Permission userWrite = new Permission();
			userWrite.setName("USER_WRITE");
			permissionRepository.save(userWrite);

			Permission userUpdate = new Permission();
			userUpdate.setName("USER_UPDATE");
			permissionRepository.save(userUpdate);

			Permission userDelete = new Permission();
			userDelete.setName("USER_DELETE");
			permissionRepository.save(userDelete);

			// 2. Buat Roles
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			adminRole.setPermissions(Set.of(userRead, userWrite, userUpdate, userDelete));
			adminRole = roleRepository.save(adminRole);

			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			userRole.setPermissions(Set.of(userRead));
			userRole = roleRepository.save(userRole);

			// 3. Konfigurasi Endpoint-Permissions
			EndpointPermission ep1 = new EndpointPermission();
			ep1.setHttpMethod("GET");
			ep1.setUrlPattern("/api/book/**");
			ep1.setRequiredPermission("USER_READ");
			endpointPermissionRepo.save(ep1);

			EndpointPermission ep2 = new EndpointPermission();
			ep2.setHttpMethod("POST");
			ep2.setUrlPattern("/api/book");
			ep2.setRequiredPermission("USER_WRITE");
			endpointPermissionRepo.save(ep2);

			EndpointPermission ep3 = new EndpointPermission();
			ep3.setHttpMethod("PUT");
			ep3.setUrlPattern("/api/book");
			ep3.setRequiredPermission("USER_UPDATE");
			endpointPermissionRepo.save(ep3);

			EndpointPermission ep4 = new EndpointPermission();
			ep4.setHttpMethod("DELETE");
			ep4.setUrlPattern("/api/book/**");
			ep4.setRequiredPermission("USER_DELETE");
			endpointPermissionRepo.save(ep4);

			// Buat super user manual untuk testing
			User superUser = new User();
			superUser.setUsername("fadiltesting25");
			superUser.setEmail("fadiltesting25@gmail.com");
			superUser.setPassword(passwordEncoder.encode("12345678"));

			Set<Role> roles = new HashSet<>();
			roles.add(userRole);
			superUser.setRoles(roles);
			userRepository.save(superUser);
		};
	};
}
