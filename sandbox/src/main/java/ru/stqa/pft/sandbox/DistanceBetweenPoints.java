package ru.stqa.pft.sandbox;

/**
 * Created by popdv on 19.07.2016.
 */
public class DistanceBetweenPoints {

  public static void main(String[] args) {

    Point p1 = new Point(2, 2);
    Point p2 = new Point(2, 4);
    System.out.println("Расстояние между точками p1(" + p1.x + ", " + p1.y + ") и p2(" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
  }

}
