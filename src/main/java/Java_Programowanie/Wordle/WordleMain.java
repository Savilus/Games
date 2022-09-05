package Java_Programowanie.Wordle;

import java.io.IOException;

public class WordleMain {

    public static void main(String[] args) throws IOException {

        String secret = "aabbb";
        WordleGame wordle = new WordleGame(secret);
        // Runtime.getRuntime().exec("5LetterWordsForWordle.txt");


        wordle.startTheGame();
        wordle.playAgain();



    }
}

