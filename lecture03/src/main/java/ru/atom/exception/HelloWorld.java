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
        log.info("File: " + String.valueOf(f));
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

        while ((mot = bufferedReader.readLine()) != null) {
            dictioner.add(mot);
            //log.info(" " + mot);
        }

        //Игра началась
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Игра началась");



//выбрать случайное слово

        String xWord = "abcde";
        xWord = xWord.toLowerCase();
        int len = xWord.length();
//проверки на валидность с приведением к единому виду
        String inputWord = "Bwbbb";
        if (inputWord.length() == 0) log.info("Ничего не введено");

        if (inputWord.length() != xWord.length())
            log.info("длина введенного слова не равна длине. повторите ввод. букв: " + xWord.length());
        inputWord = inputWord.toLowerCase();

        //singleChar список с набором символов введенного слова без повтора
        ArrayList <Character> singleChar = new ArrayList();//
        singleChar.add(inputWord.charAt(0));
        for (int i = 1; i < inputWord.length(); i++) {
            if (!verifiDouble(inputWord.charAt(i), singleChar)) singleChar.add(inputWord.charAt(i));
        }

        //*******************************************
        //логика
        int bulls = 0, cows = 0;
        for (int b = 0; b < inputWord.length(); b++) {

            if (inputWord.charAt(b)==xWord.charAt(b)) {
                bulls++;
                continue;
            }
            for(char ch:singleChar){
                if (ch==xWord.charAt(b) & !(inputWord.charAt(b)==xWord.charAt(b))) cows++;
            }
        }
        log.info("bulls: " + bulls+ " cows: "+ cows);
    }





    //***********************************************
    //методы
    //***********************************************
    //если в коллекции есть символ, возвращается true
    private static boolean verifiDouble(char c, ArrayList<Character> singleChar){
        for (char ch:singleChar) {
            if (ch==c)return true;
        }
        return false;
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