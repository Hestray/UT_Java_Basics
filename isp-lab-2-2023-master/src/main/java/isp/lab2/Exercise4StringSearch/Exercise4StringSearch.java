package isp.lab2.Exercise4StringSearch;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise4StringSearch {
    /**
     * This method should return an array of strings that contain the substring.
     * @param input the array to look through
     * @param substring what to search for
     * @return the array with the strings that contain the substring
     */
    public static String[] searchSubstrings(String input, String substring) {
        String[] splitInput = input.split(",");
        int j = 0, size = 0;

        for (int i = 0; i < splitInput.length; i++)
        {
            if (splitInput[i].contains(substring))
                size++;
        }

        String[] found = new String[size];
        for (int i = 0; i < splitInput.length && j < size; i++)
        {
            if (splitInput[i].contains(substring))
            {
                found[j] = splitInput[i];
                j++;
            }
        }
        return found;
    }

    public static String readFromConsole(String suffix) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + suffix);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String input = readFromConsole("words");
        String substring = readFromConsole("substring");
        String[] result = searchSubstrings(input, substring);
        for (String string : result) {
            System.out.println(string);
        }
    }
}
