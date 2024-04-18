package model;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape implements IShape {
  /**
   * Instantiates a new Oval.
   *
   * @param name       the name
   * @param point      the point
   * @param color      the color
   * @param dimensionX the dimension x
   * @param dimensionY the dimension y
   */
  public Oval(String name, Point point, Color color, Double dimensionX, Double dimensionY) {
    super(name, point, color, dimensionX, dimensionY);
  }

  /**
   * Instantiates a new Oval.
   *
   * @param other the other
   */
  public Oval(Oval other) {
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
    return new Oval(this);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: Oval" + "\n"
            + "Center: " + this.getPosition()
            + ", X radius: " + this.getDimensionX()
            + ", Y radius: " + this.getDimensionY()
            + ", Color: " + this.getColor() + "\n";
  }
}
