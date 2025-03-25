package isp.lab5.exercise2;


public class Exercise2 {
    // went ahead and experimented with enum and random
    public static void main(String[] args) {
        Laptop laptop = new Laptop("ASUS TUF GAMING", 36, chargingLaptopType.AC);
        SmartPhone phone = new SmartPhone("Samsung S22", 57, chargingPhoneType.FAST);

        System.out.println(laptop.toString());
        laptop.charge(laptop.timeLeft(laptop.getBatteryLevel(), laptop.getCharging()));
        System.out.println(phone.toString());
        phone.charge(phone.timeLeft(phone.getBatteryLevel(), phone.getCharging()));
    }
}
