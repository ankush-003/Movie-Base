package com.ankush003.MovieBase;

import com.ankush003.MovieBase.config.RSAKeyRecord;
import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.MovieRepository;
import com.ankush003.MovieBase.repository.UserRepository;
import com.ankush003.MovieBase.service.MovieService;
import com.ankush003.MovieBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyRecord.class)
public class MovieBaseApplication {
	private final UserService userService;
	private final MovieService movieService;
//	private final ReviewRepository reviewRepository;

	@Autowired
	MovieBaseApplication(UserService userService, MovieService movieService) {
		this.userService = userService;
		this.movieService = movieService;
	}


	public static void main(String[] args) {
		SpringApplication.run(MovieBaseApplication.class, args);
	}

	// using command line runner to run the code
	@Bean
	public CommandLineRunner run() {
		return args -> {
			System.out.println("Hello from MovieBaseApplication");

			UserEntity newUser = UserEntity.builder()
					.username("ankush003")
					.password("password")
					.email("anshhv2003@gmail.com")
					.roles("ROLE_USER")
					.build();

			userService.registerUser(newUser);
//
//			movies.forEach(movieService::addMovie);
		};
	}

	private List<MovieEntity> movies = List.of(
//			MovieEntity.builder()
//					.title("The Dark Knight")
//					.releaseDate(LocalDate.of(2008, 7, 18))
//					.genre("Action, Crime, Drama")
//					.posterUrl("https://m.media-amazon.com/images/I/81CLFQwU-WL.jpg")
//					.director("Christopher Nolan")
//					.build(),
//			MovieEntity.builder()
//					.title("Inception")
//					.releaseDate(LocalDate.of(2010, 7, 16))
//					.genre("Action, Adventure, Sci-Fi")
//					.director("Christopher Nolan")
//					.posterUrl("https://m.media-amazon.com/images/I/71DwIcSgFcS.jpg")
//					.build(),
//			MovieEntity.builder()
//					.title("Interstellar")
//					.releaseDate(LocalDate.of(2014, 11, 7))
//					.posterUrl("https://rukminim2.flixcart.com/image/850/1000/jsxjekw0/poster/p/h/x/medium-interstellar-e-interstellar-movies-poster-for-room-office-original-imafed464espvnzr.jpeg")
//					.genre("Adventure, Drama, Sci-Fi")
//					.director("Christopher Nolan")
//					.build()
			// sample animal movie
			MovieEntity.builder()
					.title("Animal")
					.releaseDate(LocalDate.of(2023, 12, 21))
					.genre("Crime, Action, Drama")
					.posterUrl("https://m.media-amazon.com/images/I/71e1JzgZGTL._AC_SY679_.jpg")
					.director("Roger Allers, Rob Minkoff")
					.build()
			// sample json

	);
}
