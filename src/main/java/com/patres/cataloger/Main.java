package com.patres.cataloger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.debug("Start program");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj ścieżkę: ");
        String pathCatalog = scanner.nextLine();
        File file = new File(pathCatalog);
        if (file.exists()) {
            CatalogManager catalogManager = new CatalogManager(pathCatalog);
            logger.debug("List of dir: {} ", catalogManager.getListOfDirectories());
            catalogManager.createDescriptionsFile();

            logger.debug("Starting rename dir");
            catalogManager.renameDirs();
            logger.debug("Renamed dir");
        } else {
            logger.warn("Folder: " + pathCatalog + ", nie istnieje");
        }

    }

}
