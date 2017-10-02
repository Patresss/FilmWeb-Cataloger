package com.patres.cataloger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogManager {

    private final static Logger logger = LoggerFactory.getLogger(CatalogManager.class);
    private final static String descriptionFileName = "About movie.txt";
    private File[] directories;
    private FilmType type;
    private Map<File, Movie> fileMovieMap = new HashMap<>();

    public CatalogManager(String path, FilmType type) {
        this.type = type;
        File dir = new File(path);
        directories = dir.listFiles(File::isDirectory);
        connectDirWithMovie();
    }

    public List<String> getListOfDirectories() {
        return Arrays.stream(directories).map(File::getName).collect(Collectors.toList());
    }

    private void connectDirWithMovie() {
        for (File file : directories) {
            fileMovieMap.put(file, Searcher.searchMovie(file.getName(), type));
        }
    }

    public void createDescriptionsFile() {
        for (Map.Entry<File, Movie> entry : fileMovieMap.entrySet()) {
            File file = entry.getKey();
            Movie movie = entry.getValue();
            String path = Paths.get(file.getPath(), descriptionFileName).toString();
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"))) {
                writer.write(MovieManagerUtils.getMovieDescription(movie));
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void renameDirs() {
        for (Map.Entry<File, Movie> entry : fileMovieMap.entrySet()) {
            File file = entry.getKey();
            Movie movie = entry.getValue();
            File newNamedDir = new File(file.getParent(), MovieManagerUtils.getDirName(movie).replaceAll("[\\\\/:*?\"<>|]", ""));
            logger.debug("Renaming dir from: " + file.getName() + " to: " + newNamedDir.getName());
            boolean success = file.renameTo(newNamedDir);
            if (!success) {
                logger.error("Can't change name from: " + file.getName() + " to: " + newNamedDir.getName());
            }

        }
    }



}
