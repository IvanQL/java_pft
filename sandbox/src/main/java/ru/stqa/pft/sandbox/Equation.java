package ru.stqa.pft.sandbox;

/**
 * Created by Administrator on 31.01.2017.
 */
public class Equation {

  private double b;
  private double a;
  private double c;

  private int n;


  public Equation(double a, double b, double c) {


    this.b = b;
    this.a = a;
    this.c = c;


    double d = b * b - 4 * a * a;

    if (d > 0) {

      n = 2;
    } else {

      if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }
    }
  }


  public int rootNumber() {
    return n;
  }
}
