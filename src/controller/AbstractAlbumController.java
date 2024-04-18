package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Album;
import model.Color;
import model.IAlbum;
import view.IView;

/**
 * The AbstractAlbumController.
 */
abstract class AbstractAlbumController implements IController {
  /**
   * The Model.
   */
  protected IAlbum model;
  /**
   * The View.
   */
  protected IView view;

  /**
   * Instantiates a new AbstractAlbumController.
   *
   * @param inputFile the input file
   * @param view      the view
   */
  public AbstractAlbumController(String inputFile, IView view) {
    this.model = new Album();
    this.view = view;

    // Read from the input file
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.startsWith("#") && !line.isEmpty()) {
          String[] commands = line.stripLeading().split("\\s+");
          switch (commands[0].toLowerCase()) {
            case "shape" -> {
              // should do some error handling here
              String name = commands[1];
              String type = commands[2];
              double x = Double.parseDouble(commands[3]);
              double y = Double.parseDouble(commands[4]);
              double width = Double.parseDouble(commands[5]);
              double height = Double.parseDouble(commands[6]);
              double red = Double.parseDouble(commands[7]);
              double green = Double.parseDouble(commands[8]);
              double blue = Double.parseDouble(commands[9]);
              model.addShape(type, name, x, y, width, height, red, green, blue);
            }
            case "move" -> {
              String name = commands[1];
              double x = Double.parseDouble(commands[2]);
              double y = Double.parseDouble(commands[3]);
              model.move(name, x, y);
            }
            case "color" -> {
              String name = commands[1];
              double red = Double.parseDouble(commands[2]);
              double green = Double.parseDouble(commands[3]);
              double blue = Double.parseDouble(commands[4]);
              model.ChangeColor(name, new Color(red, green, blue));
            }
            case "resize" -> {
              String name = commands[1];
              double width = Double.parseDouble(commands[2]);
              double height = Double.parseDouble(commands[3]);
              model.resizeX(name, width);
              model.resizeY(name, height);
            }
            case "remove" -> {
              String name = commands[1];
              model.removeShape(name);
            }
            case "snapshot" -> {
              if (commands.length == 1) {
                model.takeSnapshot("");
              } else {
                // need to recompile the strings to a sentence
                List<String> description = new ArrayList<>(Arrays.stream(commands).toList());
                description.removeFirst();
                StringBuilder string = new StringBuilder();
                for (String each : description) {
                  string.append(each).append(" ");
                }
                model.takeSnapshot(string.toString().stripTrailing());
              }
            }
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading input file: " + e.getMessage());
      System.exit(1);
    }
  }
}