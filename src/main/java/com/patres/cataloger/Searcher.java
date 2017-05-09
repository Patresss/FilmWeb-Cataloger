package com.patres.cataloger;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Searcher {

    private final static Logger logger = LoggerFactory.getLogger(Searcher.class);

    private Searcher() {
    }

    public static Movie searchMovie(String title) {
        Movie movie = new Movie();

        FilmwebApi filmwebApi = new FilmwebApi();
        String preparedTitle = title.split("\\(")[0];
        List<FilmSearchResult> filmInfoList = filmwebApi.findFilm(preparedTitle);

        if (filmInfoList.isEmpty()) {
            logger.warn("File {} not found", title);
            return null;
        }

        FilmSearchResult result = filmInfoList.get(0);
        movie.setTitle(result.getPolishTitle());
        movie.setPolishTitle(result.getTitle());
        movie.setYear(result.getYear());
        try {
            Film film = filmwebApi.getFilmData(result.getId());
            movie.setRate(film.getRate());
            movie.setCountries(film.getCountries());
            movie.setPlot(film.getPlot());
            movie.setGenre(film.getGenre());
        } catch (FilmwebException e) {
            logger.error("FilmwebException: {}", e);
        }

        return movie;
    }
}
