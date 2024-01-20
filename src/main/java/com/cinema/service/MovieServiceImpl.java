package com.cinema.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinema.dto.MovieDto;
import com.cinema.entities.Movie;
import com.cinema.exception.FileExistsException;
import com.cinema.exception.MovieNotFoundException;
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
		// 0. check for file already exists
		if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))) {
			throw new FileExistsException("File already exists! Please enter another file name!");
		}

		// 1. upload the file
		String uploadedFileName = fileService.uploadFile(path, file);

		// 2. set the value of field 'Poster' as filename
		movieDto.setPoster(uploadedFileName);

		// 3. map dto to Movie object
		Movie movie = new Movie(
				    null, 
					movieDto.getTitle(), 
					movieDto.getDirector(),
					movieDto.getStudio(), 
					movieDto.getMovieCast(), 
					movieDto.getReleaseYear(), 
					movieDto.getPoster()
				);

		// 4. save the movie object -> saved Movie object
		Movie SavedMovie = movieRepository.save(movie);

		// 5. generate the posterUrl
		String posterUrl = baseUrl.substring(1, baseUrl.length() - 1) + "/file/" + uploadedFileName;

		// 6. map Movie object to DTO object and return it
		MovieDto response = new MovieDto(
				SavedMovie.getMovieId(), 
				SavedMovie.getTitle(), 
				SavedMovie.getDirector(),
				SavedMovie.getStudio(),
				SavedMovie.getMovieCast(),
				SavedMovie.getReleaseYear(),
				SavedMovie.getPoster(),
				posterUrl);

		return response;
	}

	@Override
	public MovieDto getMovie(Integer movieId) {
		// 1. check the data in DB and if exists, fetch the data of given Id
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie Not Found with id= "+movieId));

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
			MovieDto response = new MovieDto(movie.getMovieId(), movie.getTitle(), movie.getDirector(),
					movie.getStudio(), movie.getMovieCast(), movie.getReleaseYear(), movie.getPoster(), posterUrl);
			movieDtos.add(response);
		}
		return movieDtos;
	}

	@Override
	public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException {
		//1. check if movie object exist with movieid
		Movie mv = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie Not Found with id= "+movieId));
		
		//2.if file is null, do nothing
		// if file is not null, then delete existing file associated with the record,
        //	and upload the new file
		String fileName=mv.getPoster();
		if(file!=null) {
			Files.deleteIfExists(Paths.get(path+File.separator+fileName));
			fileName=fileService.uploadFile(path, file);
		}
		
		
		//3. set movieDto's poster value, according to step2
		movieDto.setPoster(fileName);
		
		//4.map it to movie object
		Movie movie = new Movie(
			    mv.getMovieId(), 
				movieDto.getTitle(), 
				movieDto.getDirector(),
				movieDto.getStudio(), 
				movieDto.getMovieCast(), 
				movieDto.getReleaseYear(), 
				movieDto.getPoster()
			);
		
		//5. save the movie object -> return saved movie object
		Movie updatedMovie = movieRepository.save(movie);
		
		//6. generate posterUrl for it
		String posterUrl=baseUrl.substring(1, baseUrl.length() - 1)+"/file/"+fileName;
		
		// 7. map to movieDto and return it
		MovieDto response = new MovieDto(
					movie.getMovieId(),
					movie.getTitle(),
					movie.getDirector(), 
					movie.getStudio(),
					movie.getMovieCast(),
					movie.getReleaseYear(), 
					movie.getPoster(), 
					posterUrl);

		
		return response;
	}

	@Override
	public String deleteMovie(Integer movieId) throws IOException {
		// 1. check if movie object exists in DB
		Movie mv = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie Not Found with id= "+movieId));
		Integer id=mv.getMovieId();
		
		//2. delete the file associated with this object
		Files.deleteIfExists(Paths.get(path+File.separator+mv.getPoster()));
		
		//3.delete the movie object
		movieRepository.delete(mv);
		
		return "Movie deleted with id: "+id;
	}

}
