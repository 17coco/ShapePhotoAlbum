package controller;

import java.io.IOException;

import view.GraphicView;
import view.IView;
import view.WebView;

/**
 * The command reader which reads from text files.
 */
public class CommandFileReader implements ICommandReader {

  /**
   * Starts reading.
   * In this particular case,
   * this reader will read from files the commands to create shapes
   * and take snapshots,
   * and then start different controllers accordingly.
   *
   * @param args the commandline arguments
   */
  @Override
  public void start(String[] args) {
    // input will be :
    // -in "name-of-command-file" -view "type-of-view"
    // [-out "where-output-should-go"] [xmax] [ymax]
    //read command line arguments
    if (args.length < 4) {
      System.err.println("Usage: java Main -in <name-of-command-file> " +
              "-view <type-of-view> [-out <where-output-should-go>] [xmax] [ymax]");
      System.exit(1);
    }

    String inputFile = null;
    String viewType = null;
    String outputFile = null;
    int xmax = 0;
    int ymax = 0;

    // Parse command line arguments
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in" -> inputFile = args[++i];
        case "-view", "-v" -> viewType = args[++i];
        case "-out" -> outputFile = args[++i];
        default -> {
          try {
            xmax = Integer.parseInt(args[i]);
            ymax = Integer.parseInt(args[++i]);
          } catch (NumberFormatException e) {
            System.err.println("Invalid command-line arguments.");
            System.exit(1);
          }
        }
      }
    }

    if (inputFile == null || viewType == null) {
      System.err.println("Missing required command-line arguments.");
      System.exit(1);
    }


    IView view;
    if (viewType.equalsIgnoreCase("graphical")) {
      view = new GraphicView(xmax, ymax);
      //initiate model, read file and carry out commands
      IController controller = new GraphicAlbumController(inputFile, view);
      try { //start controller
        controller.go();
      } catch (IOException e) {
        System.err.println("IOException.");
      }
    } else if (viewType.equalsIgnoreCase("web")) {
      if (outputFile == null) {
        System.err.println("WebView needs a output file name.");
        System.exit(1);
      }
      view = new WebView(outputFile, xmax, ymax);
      //initiate model, read file and carry out commands
      IController controller = new WebAlbumController(inputFile, view);
      try { //start controller
        controller.go();
      } catch (IOException e) {
        System.err.println(e.getMessage()); // print inner error messages
        System.err.println("Got IOException.");
      }
    } else {
      throw new IllegalArgumentException("No valid view type is chosen.");
    }
  }
}
