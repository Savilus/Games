package Java_Programowanie.HangmanGame;

import java.io.IOException;

public class HangmanMain {

    public static void main(String[] args) throws IOException {

        int maxErrors = 5;
        HangmanGame game1 = new HangmanGame(maxErrors);

        game1.startTheGame();
        game1.playAgain();
    }
}
