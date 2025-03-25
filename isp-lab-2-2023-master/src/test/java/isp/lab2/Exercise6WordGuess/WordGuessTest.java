package isp.lab2.Exercise6WordGuess;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class WordGuessTest {

    @Test
    public void getOccurrencePositionsTest() {
        char c = 'a';
        char[] word = {'a', 'b', 'c', 'a', 'd', 'a'};
        int[] expected = {0, 3, 5};
        int[] actual = Exercise6WordGuess.getOccurrencePositions(c, word);
        assertArrayEquals(expected, actual);
    }

}
