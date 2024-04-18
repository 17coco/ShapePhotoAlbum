package controller;

import java.io.IOException;

import model.Snapshot;
import view.IView;

/**
 * The type Web album controller.
 */
public class WebAlbumController extends AbstractAlbumController implements IController {
  /**
   * Instantiates a new Web album controller.
   *
   * @param inputFile the input file
   * @param view      the view
   */
  public WebAlbumController(String inputFile, IView view) {
    super(inputFile, view);
  }

  /**
   * Starts program.
   *
   * @throws IOException the io exception
   */
  @Override
  public void go() throws IOException {
    if (model.getSnapshots().isEmpty()) { // if no snapshots
      throw new IOException("There's no snapshots.");
    } else {
      for (Snapshot each : model.getSnapshots()) {
        this.view.updateShapes(each);
      }
      this.view.displayWindow();
    }
  }
}
