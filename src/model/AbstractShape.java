package model;

import java.util.Objects;

/**
 * The type Abstract shape.
 */
abstract class AbstractShape implements IShape {
  private String name;
  private Point point;
  private Color color;
  private Double dimensionX; // dimension 1 to be assigned
  private Double dimensionY; // dimension 2 to be assigned

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name       the name
   * @param point      the point
   * @param color      the color
   * @param dimensionX the dimension x
   * @param dimensionY the dimension y
   */
  public AbstractShape(String name, Point point, Color color,
                       Double dimensionX, Double dimensionY) {
    if (dimensionX < 0 || dimensionY < 0 || name == null
            || point == null || color == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.point = new Point(point);
    this.color = color;
    this.dimensionX = dimensionX;
    this.dimensionY = dimensionY;
  }

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name       the name
   * @param color      the color
   * @param x          the x
   * @param y          the y
   * @param dimensionX the dimension x
   * @param dimensionY the dimension y
   */
  public AbstractShape(String name,
                       Color color,
                       double x, double y,
                       Double dimensionX, Double dimensionY) {
    if (dimensionX < 0 || dimensionY < 0) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.point = new Point(x, y);
    this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
    this.dimensionX = dimensionX;
    this.dimensionY = dimensionY;
  }


  /**
   * Move this shape to a given coordinate.
   *
   * @param x x
   * @param y y
   */
  @Override
  public void move(double x, double y) {
    this.point.move(x, y);
  }

  /**
   * get position of the shape
   *
   * @return a point marking the position
   */
  @Override
  public Point getPosition() {
    return new Point(point);
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Changes color.
   *
   * @param color new color
   */
  @Override
  public void changeColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException();
    }
    this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
  }

  /**
   * Gets dimension x.
   *
   * @return the dimension x
   */
  @Override
  public Double getDimensionX() {
    return dimensionX;
  }

  /**
   * Gets dimension y.
   *
   * @return the dimension y
   */
  @Override
  public Double getDimensionY() {
    return dimensionY;
  }

  /**
   * Change dimension x.
   *
   * @param x the new x
   */
  @Override
  public void changeDimensionX(Double x) {
    if (x < 0) {
      throw new IllegalArgumentException();
    } else {
      dimensionX = x;
    }
  }

  /**
   * Change dimension y.
   *
   * @param y the new y
   */
  @Override
  public void changeDimensionY(Double y) {
    if (y < 0) {
      throw new IllegalArgumentException();
    } else {
      dimensionY = y;
    }
  }

  /**
   * Resizes the shape to a given factor
   *
   * @param times factor
   */
  @Override
  public void resize(double times) {
    if (times < 0) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      dimensionX *= times;
      dimensionY *= times;
    }
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractShape that = (AbstractShape) o;
    return Objects.equals(getName(), that.getName()) && Objects.equals(point, that.point) && getColor() == that.getColor() && Objects.equals(getDimensionX(), that.getDimensionX()) && Objects.equals(getDimensionY(), that.getDimensionY());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), point, getColor(), getDimensionX(), getDimensionY());
  }
}
