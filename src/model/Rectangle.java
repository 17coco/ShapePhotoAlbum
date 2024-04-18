package model;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape implements IShape {
  /**
   * Instantiates a new Rectangle.
   *
   * @param name       the name
   * @param point      the point
   * @param color      the color
   * @param dimensionX the dimension x
   * @param dimensionY the dimension y
   */
  public Rectangle(String name, Point point, Color color, Double dimensionX, Double dimensionY) {
    super(name, point, color, dimensionX, dimensionY);
  }

  /**
   * Instantiates a new Rectangle.
   *
   * @param other the other
   */
  public Rectangle(Rectangle other) {
    super(other.getName(),
            new Point(other.getPosition().getX(), other.getPosition().getY()),
            other.getColor(), other.getDimensionX(), other.getDimensionY());
  }

  /**
   * Copy the shape.
   *
   * @return a safe copy
   */
  @Override
  public IShape copy() {
    return new Rectangle(this);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: Rectangle" + "\n"
            + "Min corner: " + this.getPosition()
            + ", Width: " + this.getDimensionX()
            + ", Height: " + this.getDimensionY()
            + ", Color: " + this.getColor() + "\n";
  }


}
