package isp.lab3.exercise1;

import isp.lab3.exercise1.Tree;
import org.junit.Assert;
import org.junit.Test;

public class TreeTest {
    @Test
    public void testTree() {
        Tree t = new Tree();
        Assert.assertEquals(15, t.height);
        Assert.assertEquals("maple", t.type);

        t.height = 23;
        t.grow(4);
        Assert.assertEquals(27, t.height);

        Assert.assertEquals("27", t.toString());
    }
}
