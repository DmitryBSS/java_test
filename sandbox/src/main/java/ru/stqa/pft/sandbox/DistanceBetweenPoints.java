package ru.stqa.pft.sandbox;

/**
 * Created by popdv on 19.07.2016.
 */
public class DistanceBetweenPoints {

  public static void main(String[] args) {

    Point p2 = new Point(2, 4);
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }
}
