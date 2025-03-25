package isp.lab3.exercise4;

import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MyPoint {
    int x, y, z;

    public MyPoint() {
        x = 0;
        y = 0;
        z = 0;
    }

    public MyPoint(int xGiven, int yGiven, int zGiven) {
        x = xGiven;
        y = yGiven;
        z = zGiven;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setXYZ(int xGiven, int yGiven, int zGiven) {
        x = xGiven;
        y = yGiven;
        z = zGiven;
    }

    public String tostring() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public int distance(int x,int y, int z) {
        int dist = 0;
        dist = (int) sqrt(pow(x - this.x, 2) + pow(y - this.y, 2) + pow(z - this.z, 2));

        return dist;
    }

    public double distance(MyPoint another) {
        double dist;
        dist = sqrt(pow(another.x - this.x, 2) + pow(another.y - this.y, 2) + pow(another.z - this.z, 2));

        return dist;
    }

    public static void main(String[] args) {
        MyPoint A = new MyPoint(2, 4, 6);
        MyPoint B = new MyPoint();

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Distance from A to B: " + df.format(A.distance(B)));
        System.out.println("Distance from B to A: " + df.format(B.distance(A)));
    }
}
