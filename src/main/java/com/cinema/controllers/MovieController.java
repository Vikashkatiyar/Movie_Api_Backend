package com.cinema.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cinema.dto.MovieDto;
import com.cinema.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/add-movie")
	public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file, @RequestPart String movieDto)
			throws IOException {
		MovieDto dto = convertToMovieDto(movieDto);

		return new ResponseEntity<>(movieService.addMovie(dto, file), HttpStatus.CREATED);

	}

	// Change String to JSON
	private MovieDto convertToMovieDto(String movieDtoObj) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		MovieDto movieDto = objectMapper.readValue(movieDtoObj, MovieDto.class);
		return movieDto;
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieDto> getMovieHandler(@PathVariable Integer movieId){
		return ResponseEntity.ok(movieService.getMovie(movieId));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieDto>> getAllMovieHandler(){
		return ResponseEntity.ok(movieService.getAllMovies());
	}

}
