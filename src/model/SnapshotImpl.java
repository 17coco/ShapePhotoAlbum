package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * The type Snapshot.
 */
class SnapshotImpl implements Snapshot {
  private final String ID;
  private final String timestamp;
  private final String description;
  private final List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param description the description (allowed to be null or empty string)
   * @param shapes      the shapes
   */
  public SnapshotImpl(String description, List<IShape> shapes) {
    //get time stamp and ID
    LocalDateTime time = LocalDateTime.now();
    this.ID = time.toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    // Format the timestamp using the formatter
    this.timestamp = time.format(formatter);
    if (description == null) { //if no description
      description = "";
    }
    this.description = description;

    //converting the hashmap to a list to store
    List<IShape> list = new ArrayList<>();
    for (IShape each : shapes) {
      list.add(each.copy());
    }

    this.shapes = list;
  }

  public SnapshotImpl(Snapshot snapshot) {
    this.ID = snapshot.getID();
    this.timestamp = snapshot.getTimestamp();
    this.description = snapshot.getDescription();

    //converting the hashmap to a list to store
    List<IShape> list = new ArrayList<>();
    for (IShape each : snapshot.getShapes()) {
      list.add(each.copy());
    }

    this.shapes = list;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getID() {
    return ID;
  }

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  public List<IShape> getShapes() {
    //safe copy
    List<IShape> copy = new ArrayList<>();
    for (IShape each : shapes) {
      copy.add(each.copy());
    }
    return Collections.unmodifiableList(copy); //immutable list
  }

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  @Override
  public Snapshot copy() {
    return new SnapshotImpl(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SnapshotImpl snapshot = (SnapshotImpl) o;
    return Objects.equals(getID(), snapshot.getID()) && Objects.equals(getTimestamp(), snapshot.getTimestamp()) && Objects.equals(getDescription(), snapshot.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getID(), getTimestamp(), getDescription());
  }

  @Override
  public String toString() {
    //print the list
/*    Iterator<IShape> shapeIterator = shapes.iterator();
    StringBuilder shapeString = new StringBuilder();
    while (shapeIterator.hasNext()) {
      shapeString.append(shapeIterator.next().toString()).append("\n");
    }*/
    return ID;
  }
}