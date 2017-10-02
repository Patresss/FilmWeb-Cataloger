package com.patres.cataloger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    private final static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        logger.debug("Start program");

        Optional<FilmType> type;
        do {
            type = chooseType();
        } while(!type.isPresent());

        System.out.print("Get path to directory: ");
        String pathCatalog = scanner.nextLine();
        File file = new File(pathCatalog);
        if (file.exists()) {
            CatalogManager catalogManager = new CatalogManager(pathCatalog, type.get());
            logger.debug("List of dir: {} ", catalogManager.getListOfDirectories());
            catalogManager.createDescriptionsFile();

            logger.debug("Starting rename dir");
            catalogManager.renameDirs();
            logger.debug("Renamed dir");
        } else {
            logger.warn("Folder: " + pathCatalog + ", nie istnieje");
        }

    }

    private static Optional<FilmType> chooseType() {
        FilmType type = null;
        System.out.print("Choose type film [F] or series [S]: ");
        String typeResponse = scanner.nextLine().toUpperCase();
        if (FilmType.FILM.getType().equals(typeResponse)) {
            type = FilmType.FILM;
        } else if (FilmType.SERIES.getType().equals(typeResponse)) {
            type = FilmType.SERIES;
        }

        return Optional.ofNullable(type);
    }

}
