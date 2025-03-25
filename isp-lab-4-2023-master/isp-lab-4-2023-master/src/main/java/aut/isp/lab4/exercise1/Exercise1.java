package aut.isp.lab4.exercise1;

public class Exercise1 {

    public static void main(String[] args) {
        //create an object
        AquariumController controller1 = new AquariumController("Neptune Systems", "Trident & A3 Apex Pro");
        AquariumController controller2 = new AquariumController("Coralvue", "HYDROS Control XS", 13.42F);

        controller1.setCurrentTime(8.31F);

        System.out.println(controller1.toString());
        System.out.println(controller2.toString());
    }
}
