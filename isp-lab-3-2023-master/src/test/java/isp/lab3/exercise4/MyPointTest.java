package isp.lab3.exercise4;

import org.junit.Assert;
import org.junit.Test;

public class MyPointTest {
    @Test
    public void MyPointTest() {
        MyPoint P = new MyPoint();
        MyPoint T = new MyPoint(0, 3, 4);
        MyPoint X = new MyPoint(1, 2, 1);

        Assert.assertEquals(5.0D, P.distance(T), 0D);
        Assert.assertEquals(3.3166247903554D, T.distance(X), 0D);

        Assert.assertEquals("(0, 3, 4)", T.tostring());
        Assert.assertEquals("(0, 0, 0)", P.tostring());
        T.setXYZ(6, 6, 6);
        Assert.assertEquals("(6, 6, 6)", T.tostring());
        Assert.assertEquals(10.392304845413264D, T.distance(P), 0D);
    }
}
