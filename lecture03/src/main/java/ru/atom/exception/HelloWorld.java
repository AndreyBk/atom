package ru.atom.exception;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class HelloWorld {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionHandler.class);
    static String filename = "dictionary.txt";
    static BufferedReader bufferedReader;

    public static void main(String[] args) throws IOException {
        URL f = HelloWorld.class.getClassLoader().getResource(filename);
        log.info("File: "+ String.valueOf(f));
        File file = null;

        try {
            file = new File(f.toURI());
        } catch (URISyntaxException e) {
            log.error("file path no valid = {}", f);
            e.printStackTrace();
        }

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in readOneLineFromFile, filename = {}", f);
            e.printStackTrace();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {
                }
            }
        }
        String mot;


        while ((mot=bufferedReader.readLine())!=null){
            log.info(" "+mot);
        }


    }






    public static String readOneLineFromFile(String filename) {
        File file = new File(filename);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            log.warn("FileNotFoundException in readOneLineFromFile, filename = {}", filename);
            return null;
        } catch (IOException e) {
            log.warn("IOException in readOneLineFromFile, filename = {}", filename);
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

}