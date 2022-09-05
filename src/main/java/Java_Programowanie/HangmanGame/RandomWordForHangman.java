package Java_Programowanie.HangmanGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;


public class RandomWordForHangman {

    Random random = new Random();
    private final String filepath;

    public RandomWordForHangman(String filepath) {
        this.filepath = filepath;
    }

    public String rollRandomWord() throws IOException {
        int randomWordLine = random.nextInt(3000);
        String randomWord = Files.readAllLines(Paths.get(filepath)).get(randomWordLine);
        return randomWord;
    }

}
