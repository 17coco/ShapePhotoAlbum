package view;

import model.IShape;
import model.Snapshot;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * The interface View.
 */
public interface IView {

  /**
   * Display window.
   */
  void displayWindow();

  /**
   * Sets up listener
   *
   * @param listener listener
   */
  void setAction(ActionListener listener);

  /**
   * Update shapes.
   *
   * @param snapshot the snapshot
   */
  void updateShapes(Snapshot snapshot);
}

