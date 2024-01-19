package com.cinema.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinema.dto.MovieDto;
import com.cinema.entities.Movie;
import com.cinema.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;
	private final FileService fileService;

	public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
		this.movieRepository = movieRepository;
		this.fileService = fileService;
	}

	@Value("${project.poster}")
	private String path;

	@Value("${base.url}")
	private String baseUrl;

	@Override
	public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
		// 1. upload the file
		String uploadedFileName = fileService.uploadFile(path, file);

		// 2. set the value of field 'Poster' as filename
		movieDto.setPoster(uploadedFileName);

		// 3. map dto to Movie object
		Movie movie = new Movie(movieDto.getMovieId(), movieDto.getTitle(), movieDto.getDirector(),
				movieDto.getStudio(), movieDto.getMovieCast(), movieDto.getReleaseYear(), movieDto.getPoster());

		// 4. save the movie object -> saved Movie object
		Movie SavedMovie = movieRepository.save(movie);

		// 5. generate the posterUrl
		String posterUrl = baseUrl.substring(1, baseUrl.length() - 1) + "/file/" + uploadedFileName;

		// 6. map Movie object to DTO object and return it
		MovieDto response = new MovieDto(SavedMovie.getMovieId(), SavedMovie.getTitle(), SavedMovie.getDirector(),
				SavedMovie.getStudio(), SavedMovie.getMovieCast(), SavedMovie.getReleaseYear(), SavedMovie.getPoster(),
				posterUrl);

		return response;
	}

	@Override
	public MovieDto getMovie(Integer movieId) {
		// 1. check the data in DB and if exists, fetch the data of given Id
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new RuntimeException("Movie Not Found in DB!"));

		// 2. generate posterUrl
		String posterUrl = baseUrl.substring(1, baseUrl.length() - 1) + "/file" + movie.getPoster();

		// 3.map to MovieDto object and return it
		MovieDto response = new MovieDto(movie.getMovieId(), movie.getTitle(), movie.getDirector(), movie.getStudio(),
				movie.getMovieCast(), movie.getReleaseYear(), movie.getPoster(), posterUrl);

		return response;
	}

	@Override
	public List<MovieDto> getAllMovies() {
		// 1. fetch all data from DB
		List<Movie> movies = movieRepository.findAll();

		List<MovieDto> movieDtos = new ArrayList<>();
		// 2. iterate through the list, generate posterUrl for each movie obj,
		// and map to MovieDto obj
		for (Movie movie : movies) {
			String posterUrl = baseUrl.substring(1, baseUrl.length() - 1) + "/file/" + movie.getPoster();
			MovieDto response = new MovieDto(movie.getMovieId(),
						movie.getTitle(), 
						movie.getDirector(),
						movie.getStudio(),
						movie.getMovieCast(),
						movie.getReleaseYear(), 
						movie.getPoster(), 
						posterUrl
					);
			movieDtos.add(response);
		}
		return movieDtos;
	}

}
