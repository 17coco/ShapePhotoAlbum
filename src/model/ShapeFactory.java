package model;

/**
 * The type Shape factory.
 */
class ShapeFactory implements IFactory {


  /**
   * Creates a new IShape object.
   *
   * @param type       String represents type of the shape
   * @param name       name of the shape
   * @param color      the color
   * @param x          x coordinate of teh center/corner point
   * @param y          y coordinate of teh center/corner point
   * @param dimensionX parameter 1
   * @param dimensionY parameter 2
   * @return the shape
   */
  @Override
  public IShape create(String type, String name,
                       Color color,
                       double x, double y,
                       Double dimensionX, Double dimensionY) {
    if (type.equalsIgnoreCase("Rectangle")) {
      return new Rectangle(name, new Point(x, y),
              new Color(color.getRed(), color.getGreen(), color.getBlue()),
              dimensionX, dimensionY);
    } else if (type.equalsIgnoreCase("Oval")) {
      return new Oval(name, new Point(x, y),
              new Color(color.getRed(), color.getGreen(), color.getBlue()),
              dimensionX, dimensionY);
    }
    // add here
    throw new IllegalArgumentException();
  }
}
