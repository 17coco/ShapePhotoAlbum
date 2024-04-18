package model;

/**
 * The interface IShape.
 */
public interface IShape {

  /**
   * Move this shape to a given coordinate.
   *
   * @param x x
   * @param y y
   */
  void move(double x, double y);

  /**
   * get position of the shape
   *
   * @return a point marking the position
   */
  Point getPosition();//Not sure on this method yet, also implemented getX and getY

  /**
   * Gets color.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Changes color.
   *
   * @param color new color
   */
  void changeColor(Color color);


  /**
   * Gets dimension x.
   *
   * @return the dimension x
   */
  Double getDimensionX();

  /**
   * Gets dimension y.
   *
   * @return the dimension y
   */
  Double getDimensionY();

  /**
   * Change dimension x.
   *
   * @param x the new x
   */
  void changeDimensionX(Double x);

  /**
   * Change dimension y.
   *
   * @param Y the new y
   */
  void changeDimensionY(Double Y);

  /**
   * Resizes the shape to a given factor
   *
   * @param times factor
   */
  void resize(double times);


  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName();

  /**
   * Copy the shape.
   *
   * @return a safe copy
   */
  IShape copy();
}