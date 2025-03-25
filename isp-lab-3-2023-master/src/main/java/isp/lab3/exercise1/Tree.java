package isp.lab3.exercise1;

public class Tree {
    public int height;
    String type;
    public Tree() {
        height = 15;
        type = "maple";
    }

    public int grow(int meters) {
        if (meters >= 1)
            height += meters;
        return height;
    }

    public String toString() {
        return this.height + "";
    }

    public static void main(String[] args) {
        Tree t1 = new Tree();

        System.out.println("The " + t1.type + " grew from " + t1.toString() + " up to " + t1.grow(5) + "!");
    }
}
