package aut.isp.lab4.exercise3;

public class Exercise3 {
    public static void main(String[] args) {
        AquariumController controller1 = new AquariumController("Neptune Systems", "Trident & A3 Apex Pro");
        controller1.setFeedingTime(13.15F);
        controller1.feeder = new FishFeeder("Ani Mate Inc", "Fish Mate F14", 3);

        float time = 9.15F;
        for (int i = 0; i < 5; i++)
        {
            controller1.setCurrentTime(time);  // initial time
            System.out.println(controller1.toString());
            if (controller1.getCurrentTime() == controller1.getFeedingTime()) {
                if (controller1.feeder.toString().contains("0"))
                    controller1.feeder.fillUp();
                controller1.feeder.feed();
                System.out.println(controller1.feeder.toString());
            }
            time += 2;
        }
    }
}
