package isp.lab2.Exercise2PrimeProduct;

import java.util.Scanner;

public class Exercise2PrimeProduct {

    /**
     * This method will check if a number is prime or not.
     * @param n number to be checked
     * @return number if it is prime, 0 otherwise
     */
    public static int checkPrime(int n)
    {
        if (n == 1 || n == 0) return 0;
        else {
            for (int i = 2; i <= n / 2; i++)
                if (n % i == 0) return 0;

            return n;
        }
    }

    /**
     * This method should return the product of the first n prime numbers
     * @param n number of prime numbers
     * @param m product of the first n prime numbers
     * @return the product if there is at least one prime number, 1 otherwise
     */
    public static long getPrimeProduct(int n, int m) {
        long prod = 1;
        for (int i = n + 1; i < m; i++)
            if(checkPrime(i) != 0) { prod = i * prod; }
        return prod;
    }


    /**
     * This method should read from the console a number
     * @return the number read from the console
     */
    public static int readfromConsoleInt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();
        return number;
    }

    public static void main(String[] args) {
        System.out.println("The product of the first 10 prime numbers is: " + getPrimeProduct(1, 10));
        System.out.println("The product of prime numbers between m and n: " + getPrimeProduct(readfromConsoleInt(), readfromConsoleInt()));
    }

}
