package Java_Programowanie.Wordle;

import java.util.*;

public class WordleGame {

    private static final String ANSI_GREEN = "\u001B[32m";

    private static final String ANSI_YELLOW = "\u001B[33m";

    private static final String ANSI_RESET = "\u001B[0m";
    private List<String> listOfAttempts = new ArrayList<>();
    private String secretWord;
    private Scanner scanner = new Scanner(System.in);

    private Map<String,Integer> letterOccurrences = new HashMap<>();
    private Map<String,Integer> emptyOccurrences = new HashMap<>();

    private  Set<Map.Entry<String, Integer>> entries = letterOccurrences.entrySet();
    private int numberOfAttempts = 0;
    private int counter = 0;

    public WordleGame(String secret) {
        this.secretWord = secret;
    }


    private boolean isOver() {
        if (failedAllAttempts()) {
            System.out.println();
            System.out.println("Unfortunately you made to many mistakes. You lose!");
            System.out.println("Your hidden wordle was: " + secretWord);
            System.out.println("You want try again? Y / N");
            return failedAllAttempts();
        } else if (checkSecretPassword()) {
            System.out.println("Congratulations! You discovered your wordle password! :)");
            System.out.println("Your wordle was: " + ANSI_GREEN + secretWord + ANSI_RESET);
            System.out.println("You want try again? Y / N");
            return true;
        }
        return false;
    }

    private boolean failedAllAttempts() {
        if (numberOfAttempts == 5) {
            return true;
        } else {
            return false;
        }
    }

    private void countOccurrencesOfLetters(){
        String secretWord = this.secretWord;
        String[] secretWordInTab = secretWord.split("");
        for(String s : secretWordInTab){
            if(letterOccurrences.containsKey(s)){
                letterOccurrences.put(s, letterOccurrences.get(s)+1);
            }
            else{
                letterOccurrences.put(s, 1);
                emptyOccurrences.put(s,1);
            }
        }
       this.entries = letterOccurrences.entrySet();
    }

    private void calculateColor() {
        String guessFromPlayer = listOfAttempts.get(listOfAttempts.size() - 1);
        String secretWord = this.secretWord;
        String[] guessWordInTab = guessFromPlayer.split("");
        String[] secretWordInTab = secretWord.split("");
        String[] coloredWordlePassword = new String[secretWordInTab.length];
        for (int i = 0; i < secretWordInTab.length; i++) {
            for (int j =0;j < secretWordInTab.length; j++) {
                if (guessWordInTab[i].equals(secretWordInTab[j]) && i == j) {
                    coloredWordlePassword[i] = "G";
                    break;
                } else if (guessWordInTab[i].equals(secretWordInTab[j]) && i != j) {
                    coloredWordlePassword[i] = "Y";
                    break;
                } else {
                    coloredWordlePassword[i] = "N";
                }
            }
        }
        printColoredWord(coloredWordlePassword, guessWordInTab);
    }

    private void printColoredWord(String[] coloredWord, String[] guessFromPlayer) {
        System.out.print(toString());
        counter++;
        for (int i = 0; i < coloredWord.length; i++) {
            if (coloredWord[i] == "G") {
                System.out.print(ANSI_GREEN + guessFromPlayer[i] + ANSI_RESET);
                continue;
            } else if (coloredWord[i] == "Y") {
                System.out.print(ANSI_YELLOW + guessFromPlayer[i] + ANSI_RESET);
                continue;
            } else {
                System.out.print(guessFromPlayer[i]);
                continue;
            }
        }

    }

    private void addOneAttempt() {
        this.numberOfAttempts++;
    }

    private void takeWordFromPlayer() {
        System.out.println();
        System.out.println("Guess your 5 letter word.");
        String guess = scanner.nextLine();
        if(guess.length() != 5){
            System.out.println("Try again");
            System.out.println("Please write" + ANSI_GREEN + " 5 letter word"+ ANSI_RESET +"! You do not lose your attempt :)");
            takeWordFromPlayer();
        } else if (guess.length() == 5) {
            listOfAttempts.add(guess);
        }
    }

    private boolean checkSecretPassword() {
        String lastPlayerAnswer = listOfAttempts.get(listOfAttempts.size() - 1);
        if (this.secretWord.equals(lastPlayerAnswer)) {
            return true;
        } else {

            return false;
        }
    }

    public void startTheGame(){

        countOccurrencesOfLetters();
        do {
            takeWordFromPlayer();
            boolean checkWord = checkSecretPassword();
            if (!checkWord) {
                addOneAttempt();
                calculateColor();
            }
        } while (!isOver());
    }

    public void playAgain() {
        String isHeWantAgain;
        do {
            isHeWantAgain = scanner.nextLine();
            switch (isHeWantAgain) {
                case "y", "Y":
                    this.numberOfAttempts = 0;
                    this.listOfAttempts.clear();
                    this. letterOccurrences.clear();
                    this.emptyOccurrences.clear();
                    this. entries.clear();
                    this. counter = 0;
                    startTheGame();
                break;
                case "n", "N":
                    System.out.println("Thanks for playing! See you soon!");
                    break;
                default:
                    System.out.println("Wrong letter! Use Y/y or N/n!");
            }
        } while (!(isHeWantAgain == "n" || isHeWantAgain == "N"));
    }


    @Override
    public String toString() {
        return "Attempt [" + (counter + 1) + "] - ";
    }


}
