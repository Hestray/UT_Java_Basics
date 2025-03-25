package aut.isp.lab4.exercise6;

public class Exercise6 {
    public static void main(String[] args) {
        AquariumController controller = new AquariumController(14, 17.25f, 18.45f, "Aquacontrols", "DX2457");
        controller.modifyPH(5.8f);
        System.out.println(controller.getpHsensor().toString());
    }
}
