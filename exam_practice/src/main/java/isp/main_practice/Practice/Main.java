package isp.main_practice.Practice;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
//        String[][] str = {
//                {"a", "b", "c"},
//                {"A", "B"}
//        };
////        for (int i = 0; i < 5; i++) arr[i] = i;
////        System.out.println(Arrays.toString(arr));
//        System.out.println(str[1][1] + "\n" + str[0][2]);

//        // default consumer
//        List<String> letters = Arrays.asList("A","B","C","D");
//        letters.forEach(System.out::println);
//
//        // custom consumer
//        List<Integer> list = Arrays.asList(2,3,4,1);
//        Consumer<Integer> action = x -> System.out.println(x += 5);
//        list.forEach(action);

//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        System.out.println("Element: " + list.get(1));
//        list.set(1, 1000);
//        System.out.println("Element: " + list.get(1));
//        System.out.println((list.get(1)).equals(1000));

//        Runnable action = new Runnable() {
//            static final int x = 0;
//            static int y = 0; // compilation error!
//
//            @Override
//            public void run() {
//                System.out.println("HI");
//            }
//        };
//
//        action.run();
        System.out.println("Input your email:");
        System.out.println(new Scanner(System.in).nextLine().trim().toLowerCase().hashCode());
    }
}
