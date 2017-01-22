package ru.stqa.pft.sandbox;

/**
 * Created by Administrator on 20.01.2017.
 */
public class Point {
  public double x;
  public double y;


  public Point(double x, double y) {

    this.x = x;
    this.y = y;
  }

  public double distance(Point p2) {  // в этом случае не указывая параметр метод работать не будет?
    return Math.sqrt ( (this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y) );
  }

}
