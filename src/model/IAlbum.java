package model;

import java.util.List;

/**
 * The interface Album.
 */
public interface IAlbum {

  /**
   * Creates a new IShape object and add to the album.
   *
   * @param type       String represents type of the shape
   * @param name       name of the shape
   * @param color      the color
   * @param x          x coordinate of teh center/corner point
   * @param y          y coordinate of teh center/corner point
   * @param dimensionX parameter 1
   * @param dimensionY parameter 2
   */
  void addShape(String type, String name, double x, double y,
                double dimensionX, double dimensionY,
                double red, double green, double blue);

  /**
   * Gets position.
   *
   * @param name the name
   * @return the position
   */
  Point getPosition(String name);

  /**
   * Gets x.
   *
   * @param name the name
   * @return the x
   */
  Double getX(String name);

  /**
   * Gets y.
   *
   * @param name the name
   * @return the y
   */
  Double getY(String name);

  /**
   * Gets dimension x.
   *
   * @param name the name
   * @return the dimension x
   */
  Double getDimensionX(String name);

  /**
   * Gets dimension y.
   *
   * @param name the name
   * @return the dimension y
   */
  Double getDimensionY(String name);

  /**
   * Gets color.
   *
   * @param name the name
   * @return the color
   */
  Color getColor(String name);

  /**
   * Moves shape to (x,y)
   *
   * @param name name
   * @param x    x
   * @param y    y
   */
  void move(String name, double x, double y);

  /**
   * Remove shape.
   *
   * @param name the name
   */
  void removeShape(String name);

  /**
   * Resize dimension x.
   *
   * @param name the name
   * @param x    the x
   */
  void resizeX(String name, double x);

  /**
   * Resize dimension y.
   *
   * @param name the name
   * @param y    the y
   */
  void resizeY(String name, double y);

  /**
   * Change color.
   *
   * @param name  the name
   * @param color the color
   */
  void ChangeColor(String name, Color color);

  /**
   * Display canvas.
   *
   * @return the shape list
   */
  List<IShape> displayCanvas();

  /**
   * Take snapshot.
   *
   * @param description the description
   */
  void takeSnapshot(String description);

  /**
   * Gets snapshots.
   *
   * @return the snapshots
   */
  List<Snapshot> getSnapshots();

  /**
   * Clears current shapes on canvas.
   */
  void clearCanvas();

  /**
   * Clears all snapshots.
   */
  void clearSnapshots();

  /**
   * Resets album.
   */
  void reset();
}