package isp.lab5.exercise1;

public class Exercise1 {
    // todo: add a menu for a customer to choose from
    public static void main(String[] args) {
        Product p1 = new Product("24",          "Teddy bear",   13d,        ProductCategory.TOYS);
        Product p2 = new Product("2",           "Milk carton",  2d,         ProductCategory.HOME_AND_GARDEN);
        Product p3 = new Product("5000000000",  "Nail polish",  3d,         ProductCategory.BEAUTY);
        Product p4 = new Product("4382",        "Warframe",     500000000d, ProductCategory.TOYS);
        System.out.println("Product 1 is " + p1.toString());

        Customer c1 = new Customer("Emma",  "0744", "Cluj",             "Observator");
        Customer c2 = new Customer("Dew",   "5231", "Constantinopol",   "1st Avenue");
        Customer c3 = new Customer("Sloth", "1234", "Thessaloniki",     "Anthinus");
        Customer c4 = new Customer("Luna",  "7768", "Nari",             "Orayama street 54th");

        Order o1 = new Order("1", p2, c1);
        Order o2 = new Order("2", p3, c2);
        Order o3 = new Order("3", p3, c3);
        Order o4 = new Order("4", p2, c4);

        System.out.println(c1.toString() + "\t\t\t\tORDER: " + o1.toString());
        System.out.println(c2.toString() + "\t\tORDER: " + o2.toString());
        System.out.println(c3.toString() + "\t\t\tORDER: " + o3.toString());
        System.out.println(c4.toString() + "\t\tORDER: " + o4.toString());

    }
}
