package isp.lab3.exercise2;

public class Rectangle {
    private int length;
    private int width;
    private String color;

    public Rectangle() {
        length = 2;
        width = 1;
        color = "red";
    }

    public Rectangle(int l, int w) {
        length = l;
        width = w;
    }

    public Rectangle(int l, int w, String c) {
        length = l;
        width = w;
        color = c;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getColor() {
        return color;
    }

    public int getParameter() {
        return ((this.width + this.length) * 2);
    }

    public int getArea() {
        return (this.width * this.length);
    }
    public static void main(String[] args) {
        Rectangle shape0 = new Rectangle();
        Rectangle shape1 = new Rectangle(4, 5);
        Rectangle shape2 = new Rectangle(6, 2, "teal");

        System.out.println("For rectangle no. 1: Length " + shape0.getLength() + "\t Width " + shape0.getWidth() + "\t Color " + shape0.getColor() + "\t Parameter " + shape0.getParameter() + "\t Area " + shape0.getArea());
        System.out.println("For rectangle no. 2: Length " + shape1.getLength() + "\t Width " + shape1.getWidth() + "\t Color " + shape1.getColor() + "\t Parameter " + shape1.getParameter() + "\t Area " + shape1.getArea());
        System.out.println("For rectangle no. 3: Length " + shape2.getLength() + "\t Width " + shape2.getWidth() + "\t Color " + shape2.getColor() + "\t Parameter " + shape2.getParameter() + "\t Area " + shape2.getArea());
    }
}
