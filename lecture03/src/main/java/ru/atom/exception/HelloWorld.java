package ru.atom.exception;

import sun.rmi.runtime.Log;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

//import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class HelloWorld {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionHandler.class);
    static String filename = "dictionary.txt";
    static BufferedReader bufferedReader;
    static Collection dictioner;

    public static void main(String[] args) throws IOException {
        dictioner = new ArrayList();
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
            dictioner.add(mot);
            log.info(" "+mot);
        }
//выбрать случайное слово
        String xWord="abcde";
        xWord=xWord.toLowerCase();
        int len=xWord.length();

        String inputWord="";
        if(inputWord.length()==0) log.info("Ничего не введено");

        if (inputWord.length()!=xWord.length()) log.info("длина введенного слова не равна длине. повторите ввод. букв: "+ xWord.length());

        inputWord=inputWord.toLowerCase();
        int bulls=0, cows=0;
        char chX, chInputWord;
        ArrayList singleChar;//
        char[] ssss= inputWord.toCharArray();






        for (int b=0; b<inputWord.length(); b++){
            chX=xWord.charAt(b);
            for (int c=0; c<inputWord.length(); c++){
                chInputWord=inputWord.charAt(c);
                if (chX==chInputWord & b==c) bulls++;

            }


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