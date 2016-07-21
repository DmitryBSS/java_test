package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by popdv on 21.07.2016.
 */
public class DistanceTests {

    @Test
    public void testDistanceBetweenPoints1() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 4);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }

    @Test
    public void testDistanceBetweenPoints2() {
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(-1, -3);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }

    @Test
    public void testDistanceBetweenPoints3() {
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(-1, -1);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDistanceBetweenPoints4() {
        Point p1 = new Point(-2, -1);
        Point p2 = new Point(-1, -1);
        Assert.assertEquals(p1.distance(p2), 1.0);
    }
}
