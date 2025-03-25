package aut.isp.lab4.exercise2;

public class Exercise2 {
    public static void main(String[] args) {
        FishFeeder tank1 = new FishFeeder();
        FishFeeder tank2 = new FishFeeder("FISHNOSH", "New Generation 2024");
        FishFeeder tank3 = new FishFeeder("Ani Mate Inc", "Fish Mate F14", 9);

        System.out.println(tank1.toString());
        System.out.println(tank2.toString());

        for (int i = 0; i < 3; i++)
            tank3.feed();

        System.out.println(tank3.toString());
        tank3.fillUp();
        System.out.println(tank3.toString());
    }
}
