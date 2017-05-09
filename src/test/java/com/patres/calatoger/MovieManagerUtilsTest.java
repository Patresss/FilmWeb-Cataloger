package com.patres.calatoger;

import com.patres.cataloger.Movie;
import com.patres.cataloger.MovieManagerUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieManagerUtilsTest {

    private Movie movie;

    @Before
    public void setupTestEnvironment() {
        movie = new Movie();
        movie.setTitle("Caramel");
        movie.setPolishTitle("Karmel");
        movie.setGenre("Dramat, Komedia, Romans");
        movie.setYear(2007);
        movie.setRate(7.3f);
        movie.setPlot("Historie kilku kobiet, których drogi krzyżują się w bejruckim salonie urody.");
    }

    @Test
    public void testDirName() {
        String dirName = MovieManagerUtils.getDirName(movie);
        String expectedName = "Caramel    (2007) [7]";
        assertEquals(expectedName, dirName);
    }

    @Test
    public void testMovieDescription() {
        String movieDescription = MovieManagerUtils.getMovieDescription(movie);
        String expectedMovieDescription =
                "Tytuł:              Caramel" + System.getProperty("line.separator") +
                "Polski tytuł:       Karmel" + System.getProperty("line.separator") +
                "Gatunek             Dramat, Komedia, Romans" + System.getProperty("line.separator") +
                "Rok:                2007" + System.getProperty("line.separator") +
                "Ocena:              7,3" + System.getProperty("line.separator") +
                "Opis:               Historie kilku kobiet, których drogi krzyżują się w bejruckim salonie urody." + System.getProperty("line.separator");

        assertEquals(expectedMovieDescription, movieDescription);
    }

}
