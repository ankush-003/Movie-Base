package com.ankush003.MovieBase;

import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.MovieRepository;
import com.ankush003.MovieBase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MovieBaseApplication {
	private final UserRepository userRepository;
	private final MovieRepository movieRepository;
//	private final ReviewRepository reviewRepository;

	@Autowired
	MovieBaseApplication(UserRepository userRepository, MovieRepository movieRepository) {
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
//		this.reviewRepository = reviewRepository;
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
					.name("Ankush")
					.email("anshhv2003@gmail.com")
					.password("password")
					.build();

			userRepository.save(newUser);

			movieRepository.saveAll(movies);
		};
	}

	private List<MovieEntity> movies = List.of(
			MovieEntity.builder()
					.title("The Dark Knight")
					.releaseDate(LocalDate.of(2008, 7, 18))
					.genre("Action, Crime, Drama")
					.director("Christopher Nolan")
					.averageRating(9.0)
					.build(),
			MovieEntity.builder()
					.title("Inception")
					.releaseDate(LocalDate.of(2010, 7, 16))
					.genre("Action, Adventure, Sci-Fi")
					.director("Christopher Nolan")
					.averageRating(8.8)
					.build(),
			MovieEntity.builder()
					.title("Interstellar")
					.releaseDate(LocalDate.of(2014, 11, 7))
					.genre("Adventure, Drama, Sci-Fi")
					.director("Christopher Nolan")
					.averageRating(8.6)
					.build()
	);
}
