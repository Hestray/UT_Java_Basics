package isp.lab2.Exercise3SumOfIntegersRecursive;

public class Exercise3SumOfIntegersRecursive {


    /**
     * This method will return the sum of the first n integers
     * @param n
     * @return
     */
    public static int sumOfIntegers(int n) {
        int prod = 1;
        if (n > 1)
            prod = n + sumOfIntegers(n - 1);
        return prod;
    }

    public static void main(String[] args) {
        System.out.println(sumOfIntegers(10));
    }
}
