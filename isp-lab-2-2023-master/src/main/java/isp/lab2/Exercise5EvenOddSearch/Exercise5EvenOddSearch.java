package isp.lab2.Exercise5EvenOddSearch;

public class Exercise5EvenOddSearch {

    public static int[] findEvenOdd(String input) {
        String[] splitInput = input.split(",");
        boolean eOK = false, oOK = false;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int minIndex = -1, maxIndex = -1;
        int[] result = new int[4];

        for (int i = 0; i < splitInput.length; i++) {
            if (Integer.parseInt(splitInput[i]) % 2 == 0 && Integer.parseInt(splitInput[i]) > max)
            {
                eOK = true;
                max = Integer.parseInt(splitInput[i]);
                maxIndex = i;
            }
            else
            {
                if (Integer.parseInt(splitInput[i]) < min)
                {
                    oOK = true;
                    min = Integer.parseInt(splitInput[i]);
                    minIndex = i;
                }
            }
        }
        result[0] = max; result[1] = maxIndex;
        result[2] = min; result[3] = minIndex;
        return result;
    }

    public static void main(String[] args) {
        String input = "1,2,3,4,5,6,7,8,9,10";
        int[] result = findEvenOdd(input);
        for (int i = 0; i < result.length; i++) {
            switch(i) {
                case 0: { System.out.print("Max even: "); break; }
                case 1: { System.out.print("Index of max: "); break; }
                case 2: { System.out.print("Min odd: "); break; }
                case 3: { System.out.print("Index of min: "); break; }
            }
            System.out.println(result[i]);
        }
    }
}
