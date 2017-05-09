package com.patres.cataloger;


public class MovieManagerUtils {

    private MovieManagerUtils() {
    }

    public static String getDirName(Movie movie) {
        StringBuilder stringBuilder = new StringBuilder();
        String title = movie.getTitle() == null ? "" : movie.getTitle();
        String year = movie.getYear() == null ? "" : movie.getYear().toString();
        String rate = movie.getRate() == null ? "" : movie.getRate();
        stringBuilder.append(String.format("%s    ", title));
        stringBuilder.append(String.format("(%s) ", year));
        stringBuilder.append(String.format("[%s]", rate));
        return stringBuilder.toString();
    }

    public static String getMovieDescription(Movie movie) {
        StringBuilder stringBuilder = new StringBuilder();
        setPartOfDescription(stringBuilder, String.format("%-20s", "Tytuł:"), movie.getTitle());
        setPartOfDescription(stringBuilder, String.format("%-20s", "Polski tytuł:"), movie.getPolishTitle());
        setPartOfDescription(stringBuilder, String.format("%-20s", "Gatunek"), movie.getGenre());
        setPartOfDescription(stringBuilder, String.format("%-20s", "Rok:"), movie.getYear());
        setPartOfDescription(stringBuilder, String.format("%-20s", "Ocena:"), movie.getRate());
        setPartOfDescription(stringBuilder, String.format("%-20s", "Opis:"), movie.getPlot());

        return stringBuilder.toString();
    }

    private static StringBuilder setPartOfDescription(StringBuilder stringBuilder, String key, Object value) {
        if (value != null) {
            stringBuilder.append(key);
            stringBuilder.append(value);
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder;
    }

}
