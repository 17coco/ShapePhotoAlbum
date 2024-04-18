package model;

import java.util.Objects;

/**
 * The type Point.
 */
public class Point {
  private double x;
  private double y;

  /**
   * Instantiates a new Point.
   *
   * @param x the x
   * @param y the y
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Instantiates a new Point.
   *
   * @param other the other
   */
  public Point(Point other) {
    this.x = other.x;
    this.y = other.y;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  public double getY() {
    return y;
  }

  /**
   * Gets x.
   *
   * @return the x
   */
  public double getX() {
    return x;
  }

  /**
   * Move.
   *
   * @param x the x
   * @param y the y
   */
  void move(double x, double y) {
    this.x = x;
    this.y = y;
  }


  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return Double.compare(getX(), point.getX()) == 0 && Double.compare(getY(), point.getY()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}
