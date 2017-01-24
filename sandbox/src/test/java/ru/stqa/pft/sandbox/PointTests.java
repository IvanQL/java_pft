package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 24.01.2017.
 */
public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point ( 4, 4 );
    Point p2 = new Point ( 8, 10 );
    Assert.assertEquals ( p1.distance ( p2 ), 7.211102550927978 );

    Point p3 = new Point ( -4, -11 );
    Point p4 = new Point ( 8, 10 );
    Assert.assertEquals ( p3.distance ( p4 ), 24.186773244895647 );

    Point p5 = new Point ( 0, 0 );
    Point p6 = new Point ( 0, 0 );
    Assert.assertEquals ( p5.distance ( p6 ), 0.0 );

  }

}

