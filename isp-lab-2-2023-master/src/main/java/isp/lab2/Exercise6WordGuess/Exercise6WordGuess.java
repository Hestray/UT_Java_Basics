package isp.lab2.Exercise6WordGuess;

import java.util.*;
import java.io.File;                    // import the file class
import java.io.FileNotFoundException;   // handle file errors

public class Exercise6WordGuess {

    /**
     * This method will return the number of occurrences of a character in a word
     *
     * @param c    letter that is guessed through user inputS
     * @param word to be guessed
     * @return the array of indexes of the char c found in word; will return pos = null if nothing is found
     */
    public static int[] getOccurrencePositions(char c, char[] word) {
        int n = 0, num = 0;
        // find the occurrences and save them in a number where each digit is an occurrence
        for (int i = 0; i < word.length; i++) {
            if (word[i] == Character.toLowerCase(c)) {
                num = num * 10 + i;
                n++;
            }
        }

        // transfer the positions into an array of int
        int[] pos = new int[n];
        if (num != 0) {
            for (int i = n - 1; i >= 0; i--) {
                pos[i] = num % 10;
                num = num / 10;
            }
        }

        return pos;
    }

    /**
     * This method will return the number of occurrences of a character in a word
     *
     * @return a list of English words
     */
    public static List<String> loadDictionary() {
        // using a file of 500 words in order to create a list of random, common words to generate
        String fileName = "src\\dictionary 500 words.txt";
        File dictionary = new File(fileName);
//        System.out.println(dictionary.toPath().toAbsolutePath());
        List<String> words = new ArrayList<>();
        Scanner reader = null;

        // handle errors regarding files
        try {
            reader = new Scanner(dictionary);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Dictionary could not be accessed.");
            System.exit(0);
        }

        while (reader.hasNextLine()) {
            String word = reader.nextLine(); // replace with a word from a dictionary
            words.add(word);
        }

        reader.close();

        return words;
    }

    /**
     * This method will return the number of occurrences of a character in a word
     *
     * @param dictionary list of strings
     * @return a random word from the list
     */
    public static String getRandomWord(List<String> dictionary) {
        Random random = new Random();
        int index = random.nextInt(500);

        String chosenWord = dictionary.get(index);

        return chosenWord;
    }

    /**
     * This method will replace the _ with the actual guessed letters
     */
    public static StringBuilder guessingWord(StringBuilder guessWord, int[] index, String c) {
        for (int i = 0; i < index.length; i++) {
            guessWord.setCharAt(index[i], Character.toUpperCase(c.charAt(0)));
        }
        return guessWord;
    }

    public static void main(String[] args) {
        String word = getRandomWord(loadDictionary());
        char[] wordChars = word.toCharArray();
        boolean solved = false;
        Scanner scanner = new Scanner(System.in);

        int tries = 0;
        int attempts = 0;
        int check = word.length();
        StringBuilder usedLetters = new StringBuilder(26);
        StringBuilder guessWord = new StringBuilder(word.length());

        char[] chars = new char[word.length()];
        Arrays.fill(chars, '_');
        String questionMark = new String(chars);

        guessWord.replace(0, guessWord.length(), questionMark);
        chars = null;
        questionMark = null;      // flag for garbage collector

        System.out.println();
        System.out.println("Your word has " + word.length() + " letters. Good luck! ~~~~~~~~~~");
        System.out.println();

        // start game
        while (attempts < word.length() && !solved && guessWord.indexOf("_") != -1) {
            tries++;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("WORD IS: " + guessWord);    // the word will be displayed as a string of '_'
            System.out.println("Enter a letter: ");
            String letter = scanner.nextLine();

            // check if the letter has been previously attempted
            while (usedLetters.indexOf(letter.toLowerCase()) != -1) {
                System.out.println("You already guessed this letter previously. Guess another letter: ");
                letter = scanner.nextLine();
            }
            usedLetters.append(letter);

            int[] occurrences = getOccurrencePositions(letter.charAt(0), wordChars);

            // get a quick check to make sure we didn't guess the word now
            check = check - occurrences.length;
            if (check != 0 && guessWord.indexOf("_") != -1) // if we cannot find any '_', the word was guessed
            {
                // print indexes, if the character can be found in the word
                if (occurrences.length == 0) {
                    System.out.println("Whoops! Letter not found~ \t Try again!");
                    attempts++;
                    System.out.println("Tries left: " + (word.length() - attempts));
                } else {
                    System.out.println("Letter found at positions: " + Arrays.toString((occurrences)));
                    guessWord = guessingWord(guessWord, occurrences, letter);
                    System.out.println("Current guess word: " + guessWord);
                }
            } else solved = true;
        }

        if (solved)
            System.out.println("Good job! You guessed the word in " + tries + " tries! The word was \"" + word.toUpperCase() + "\"");
        else System.out.println("Boohoo! You lost. The word was \"" + word.toUpperCase() + "\"");
    }
}