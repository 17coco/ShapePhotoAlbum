package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * The Snapshot interface.
 */
public interface Snapshot {


  /**
   * Gets id.
   *
   * @return the id
   */
  String getID();

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  String getTimestamp();

  /**
   * Gets description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  List<IShape> getShapes();

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  Snapshot copy();
}