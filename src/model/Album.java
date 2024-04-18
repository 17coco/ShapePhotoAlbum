package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * The type Album.
 */
public class Album implements IAlbum {

  private HashMap<String, IShape> shapes;
  private List<Snapshot> snapshots;
  private List<IShape> shapesOnly;

  /**
   * Instantiates a new Album.
   */
  public Album() {
    this.shapes = new HashMap<>();
    this.snapshots = new ArrayList<>();
    this.shapesOnly = new ArrayList<>();
  }

  /**
   * Creates a new IShape object and add to the album.
   *
   * @param type       String represents type of the shape
   * @param name       name of the shape
   * @param x          x coordinate of teh center/corner point
   * @param y          y coordinate of teh center/corner point
   * @param dimensionX parameter 1
   * @param dimensionY parameter 2
   *
   */
  @Override
  public void addShape(String type, String name, double x, double y,
                       double dimensionX, double dimensionY,
                       double red, double green, double blue) {
    if (this.exists(name)) {
      throw new IllegalArgumentException("Name already exists.");
    }
    IFactory factory = new ShapeFactory();
    IShape newShape = factory.create(
            type, name, new Color(red, green, blue), x, y, dimensionX, dimensionY);
    //both the hash map and list will have pointer to the new shape
    //the list will store the order how the shapes are added
    shapes.put(name.toUpperCase(), newShape);
    shapesOnly.add(newShape);
  }

  /**
   * Gets position.
   *
   * @param name the name
   * @return the position
   */
  @Override
  public Point getPosition(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      return new Point(shapes.get(name.toUpperCase()).getPosition());
    }
  }

  /**
   * Gets x.
   *
   * @param name the name
   * @return the x
   */
  @Override
  public Double getX(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      return this.shapes.get(name.toUpperCase())
              .getPosition().getX();
    }
  }

  /**
   * Gets y.
   *
   * @param name the name
   * @return the y
   */
  @Override
  public Double getY(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      return this.shapes.get(name.toUpperCase())
              .getPosition().getY();
    }
  }

  /**
   * Gets dimension x.
   *
   * @param name the name
   * @return the dimension x
   */
  @Override
  public Double getDimensionX(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      return this.shapes.get(name.toUpperCase())
              .getDimensionX();
    }
  }

  /**
   * Gets dimension y.
   *
   * @param name the name
   * @return the dimension y
   */
  @Override
  public Double getDimensionY(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      return this.shapes.get(name.toUpperCase())
              .getDimensionY();
    }
  }

  /**
   * Gets color.
   *
   * @param name the name
   * @return the color
   */
  @Override
  public Color getColor(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      return shapes.get(name.toUpperCase()).getColor();
    }
  }

  /**
   * Moves shape to (x,y)
   *
   * @param name name
   * @param x    x
   * @param y    y
   */
  @Override
  public void move(String name, double x, double y) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      this.shapes.get(name.toUpperCase()).move(x, y);
    }
  }

  /**
   * Remove shape.
   *
   * @param name the name
   */
  @Override
  public void removeShape(String name) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      this.shapesOnly.remove(shapes.get(name.toUpperCase()));
      this.shapes.remove(name.toUpperCase());
    }
  }

  /**
   * Resize dimension x.
   *
   * @param name the name
   * @param x    the x
   */
  @Override
  public void resizeX(String name, double x) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    } else {
      this.shapes.get(name.toUpperCase()).changeDimensionX(x);
    }
  }

  /**
   * Resize dimension y.
   *
   * @param name the name
   * @param y    the y
   */
  @Override
  public void resizeY(String name, double y) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    }
    this.shapes.get(name.toUpperCase()).changeDimensionY(y);
  }

  /**
   * Change color.
   *
   * @param name  the name
   * @param color the color
   */
  @Override
  public void ChangeColor(String name, Color color) {
    if (!this.exists(name)) {
      throw new IllegalArgumentException("No such shape.");
    }
    this.shapes.get(name.toUpperCase()).changeColor(color);
  }

  /**
   * Display canvas.
   *
   * @return the shape list
   */
  @Override
  public List<IShape> displayCanvas() {
    List<IShape> list = new ArrayList<>();
    for (String each : shapes.keySet()) {
      list.add(shapes.get(each).copy());
    }
    return Collections.unmodifiableList(list);
  }


  /**
   * Take a snapshot.
   *
   * @param description the description
   */
  @Override
  public void takeSnapshot(String description) {
    Snapshot newSnapshot = new SnapshotImpl(description, new ArrayList<>(shapesOnly));
    this.snapshots.add(newSnapshot);
  }

  /**
   * Gets snapshots.
   *
   * @return the snapshots
   */
  @Override
  public List<Snapshot> getSnapshots() {
    //safe copy
    List<Snapshot> copy = new ArrayList<>();
    for (Snapshot each : snapshots) {
      copy.add(each.copy());
    }
    return Collections.unmodifiableList(copy);
  }

  /**
   * Clears current shapes on canvas.
   */
  @Override
  public void clearCanvas() {
    this.shapes.clear();
  }

  /**
   * Clears all snapshots.
   */
  @Override
  public void clearSnapshots() {
    this.snapshots = new ArrayList<>();
  }

  /**
   * Resets album.
   */
  public void reset() {
    this.shapes.clear();
    this.snapshots = new ArrayList<>();
  }

  /**
   * Helper method to check if a name already exists.
   *
   * @param name name
   * @return boolean
   */
  private boolean exists(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    return shapes.containsKey(name.toUpperCase());
  }

  @Override
  public String toString() {
    if (snapshots.isEmpty()) {
      return "No Snapshots now.";
    }
    StringBuilder s = new StringBuilder();
    for (Snapshot each : snapshots) {
      s.append(each.toString()).append("\n");
    }
    return s.toString();
  }
}
