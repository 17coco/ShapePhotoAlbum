package controller;


import java.io.IOException;

import javax.swing.*;

import model.Snapshot;
import view.IView;

/**
 * The type Graphic album controller.
 */
public class GraphicAlbumController extends AbstractAlbumController implements IController {
  /**
   * The Maximum page.
   */
  int maximumPage, /**
   * The Current page.
   */
  currentPage;


  /**
   * Instantiates a new Graphic album controller.
   *
   * @param inputFile the input file
   * @param view      the view
   */
  public GraphicAlbumController(String inputFile, IView view) {
    super(inputFile, view);
    //initialize page number
    this.maximumPage = model.getSnapshots().size();
    this.currentPage = 0;
  }


  /**
   * Starts program.
   *
   * @throws IOException the io exception
   */
  @Override
  public void go() throws IOException {
    if (this.model.getSnapshots().isEmpty()) {
      // if no snapshots
      JOptionPane.showMessageDialog(new JFrame(),
              "There's no snapshot created.",
              "Warning",
              JOptionPane.WARNING_MESSAGE);
      System.exit(1);
    } else {
      this.view.updateShapes(model.getSnapshots().get(0));

      // set button functions
      this.view.setAction(e -> {
        if (e.getActionCommand()
                .equalsIgnoreCase("prev")) {
          if (currentPage <= 0) {
            // put on a dialog
            JOptionPane.showMessageDialog(new JFrame(),
                    "This is the first snapshot.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            System.out.println("no prev");
          } else {
            view.updateShapes(model.getSnapshots().get(--currentPage));
            view.displayWindow();
          }

        } else if (e.getActionCommand()
                .equalsIgnoreCase("next")) {
          if (currentPage >= maximumPage - 1) {
            // put on a dialog
            JOptionPane.showMessageDialog(new JFrame(),
                    "There is no more snapshot.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            System.out.println("no next");
          } else {
            view.updateShapes(model.getSnapshots().get(++currentPage));
            view.displayWindow();
          }

        } else if (e.getActionCommand()
                .equalsIgnoreCase("select")) {
          //get the array of snapshots
          Snapshot[] possibilities = model.getSnapshots().toArray(new Snapshot[0]);
          //show dialog with choices
          Snapshot choice = (Snapshot) JOptionPane.showInputDialog(
                  new JFrame(),
                  "Choose snapshot:\n",
                  "Menu",
                  JOptionPane.PLAIN_MESSAGE,
                  null,
                  possibilities,
                  possibilities[0]);
          // get which page is chosen, to know prev and next later
          currentPage = model.getSnapshots().indexOf(choice);

          view.updateShapes(choice);
          view.displayWindow();

        } else if (e.getActionCommand()
                .equalsIgnoreCase("quit")) {
          // quit program
          System.exit(0);
        }
      });
      this.view.displayWindow();
    }
  }
}
