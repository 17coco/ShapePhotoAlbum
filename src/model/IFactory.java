package model;

/**
 * The interface Factory.
 */
interface IFactory {

  /**
   * Creates a new IShape object.
   *
   * @param type       String represents type of teh shape
   * @param name       name of the shape
   * @param color      the color
   * @param x          x coordinate of the center/corner point
   * @param y          y coordinate of the center/corner point
   * @param dimensionX parameter 1
   * @param dimensionY parameter 2
   * @return the shape
   */
  IShape create(String type, String name,
                Color color,
                double x, double y,
                Double dimensionX, Double dimensionY);


}
