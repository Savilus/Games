package Java_Programowanie.HangmanGame;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class HangmanGame {
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";
    public static final String RED = "\033[0;31m";
    public static final String YELLOW_UNDERLINED = "\033[4;33m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";

    RandomWordForHangman newRandomWordWhenGameStart = new RandomWordForHangman("hangmanGame.txt");
    private int maxErrors;
    private String secretPasswordToDiscover;
    private int numbersOfLives;

    private Scanner playlerLetter = new Scanner(System.in);
    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] secretPasswordTab;
    private char[] howManyLettersAreDiscovered;

    private Set<Character> usedCharacters = new HashSet<>();

    public HangmanGame(int maxErrors) throws IOException {
        String secret = rollRandomWordWhenGameStart();
        this.maxErrors = maxErrors;
        this.secretPasswordTab = secret.toCharArray();
        this.howManyLettersAreDiscovered = new char[secretPasswordTab.length];
        complementEmptyArray();
        secretPasswordToDiscover = secret;
        numbersOfLives = maxErrors;

    }

    private void complementEmptyArray() {
        for (int i = 0; i < howManyLettersAreDiscovered.length; i++) {
            if (!(secretPasswordTab[i] == ' ')) {
                howManyLettersAreDiscovered[i] = '-';
            } else {
                howManyLettersAreDiscovered[i] = ' ';
            }
        }
    }

    private void showActualState() {
        for (int i = 0; i < howManyLettersAreDiscovered.length; i++) {
            System.out.print(howManyLettersAreDiscovered[i]);
        }
    }

    private boolean isCharFromAnAlphabet(char guess) {
        boolean result = false;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == guess) {
                result = true;
                break;
            }
        }
        if (!result) {
            System.out.println("Enter a letter from an alphabet");
            return false;
        }
        return false;
    }

    private boolean guessChar(char guess) {
       boolean result = false;
       int isGuessCharInPassword = 0;
        for (int i = 0; i < secretPasswordTab.length; i++) {
            if (secretPasswordTab[i] == guess) {
                howManyLettersAreDiscovered[i] = guess;
                isGuessCharInPassword++;
                result = true;
            } else if (usedCharacters.contains(guess)) {
                System.out.println("You already tried letter [" + guess + "]! Try something else.");
                System.out.println("You don't lose live :)");
                result = true;
                return result;
            }
        }
        if(isGuessCharInPassword == 0){
            System.out.println(RED + "Whoops! You missed. You lose one life." + RESET);
            if((this.maxErrors - 1) == 1){
                System.out.println("Carefully, is your last live.");
            } else {
                System.out.println( "You have [" + GREEN + (this.maxErrors - 1) + RESET + "] lives remaining");
            }
        }

        usedCharacters.add(guess);
        return result;
    }

    public int countErrors() {
        return this.maxErrors--;
    }

    private boolean isOver() {
        if (lostAllLives()) {
            System.out.println("Unfortunately you made to many mistakes. You lose!");
            System.out.println("Your hidden word was: " + YELLOW_UNDERLINED + secretPasswordToDiscover + RESET);
            System.out.println("You want try again? " + GREEN + "Y" + RESET + "/" + RED + "N" + RESET );
            return lostAllLives();
        } else if (guessedSecret()) {
            System.out.println( GREEN_BOLD_BRIGHT + "Congratulations!" + RESET + "You discovered you hidden word! :)");
            System.out.println("Your hidden word was: " + YELLOW_UNDERLINED + secretPasswordToDiscover + RESET);
            System.out.println("You want try again? " + GREEN + "Y" + RESET + "/" + RED + "N" + RESET);
        }
        return guessedSecret();
    }

    private boolean guessedSecret() {
        if (Arrays.equals(secretPasswordTab, howManyLettersAreDiscovered)) {
            return true;
        }
        return false;
    }


    public void playAgain() throws IOException {
        char isHeWantAgain;
        do {
            isHeWantAgain = playlerLetter.next().charAt(0);
            switch (isHeWantAgain) {
                case 'y', 'Y':
                    String rollNewWord = rollRandomWordWhenGameStart();
                    this.secretPasswordTab = rollNewWord.toCharArray();
                    this.howManyLettersAreDiscovered = new char[secretPasswordTab.length];
                    secretPasswordToDiscover = rollNewWord;
                    this.maxErrors = numbersOfLives;
                    this.secretPasswordToDiscover = rollNewWord;
                    complementEmptyArray();
                    usedCharacters.clear();
                    startTheGame();
                    break;
                case 'n', 'N':
                    System.out.println("Thanks for playing! See you soon!");
                    break;
                default:
                    System.out.println("Wrong letter! Use Y/y or N/n!");
            }
        } while (!(isHeWantAgain == 'n' || isHeWantAgain == 'N'));
    }

    public void startTheGame() {

        do {
            showActualState();
            System.out.println();
            System.out.println("Guess the letter");
            char guess = playlerLetter.next().charAt(0);
            isCharFromAnAlphabet(guess);
            boolean exist = guessChar(guess);
            if (!exist) {
                countErrors();
            }
        } while (!isOver());
    }

    private boolean lostAllLives() {
        if (maxErrors == 0) {
            return true;
        } else return false;
    }

    private String rollRandomWordWhenGameStart() throws IOException {
        return newRandomWordWhenGameStart.rollRandomWord();
    }
}
