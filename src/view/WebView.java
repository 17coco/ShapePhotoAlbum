package view;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

/**
 * The web view.
 * The html view of shape album.
 */
public class WebView implements IView {

  private String outputFileTitle;
  private StringBuilder outputFileContent;
  private int x, y; //canvas size


  /**
   * Creates a webview instance.
   *
   * @param outputFileTitle title of html file
   * @param x               width
   * @param y               height
   */
  public WebView(String outputFileTitle, int x, int y) {
    this.outputFileTitle = outputFileTitle;
    this.outputFileContent = new StringBuilder().append("<!DOCTYPE html>\n<html>\n");
    if (x <= 0) {
      this.x = 1000;
      this.y = 1000;
    } else {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * Makes the actual view.
   * In WebView's case, the method generates a html file with its given name and path.
   */
  @Override
  public void displayWindow() {
    //write file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileTitle))) {
      writer.write(outputFileContent.toString());
      System.out.println("HTML file generated successfully!");
    } catch (IOException e) {
      System.out.println("HTML file generation failed");
    }
  }

  /**
   * Not needed for static web view.
   *
   * @param listener Action listener
   */
  @Override
  public void setAction(ActionListener listener) {

  }

  /**
   * Takes one snapshot and generate a html view of it,
   * and add it to the outputFileContent.
   *
   * @param snapshot a snapshot of shapes
   */
  @Override
  public void updateShapes(Snapshot snapshot) {

    if (!snapshot.getShapes().isEmpty()) {
      //step up frame
      //including background color and frame line
      int strokeWidthForFrame = 10;
      String canvasFrameColor = "red";
      //frame
      outputFileContent.append(String.format("""
              <svg width="%d" height="%d" style=background-color:aqua>;
              <rect width="%d" height="%d" style="fill: none;stroke-width:%d;stroke:%s"/>;
              """, x, y, x, y, strokeWidthForFrame, canvasFrameColor));
      //ID
      outputFileContent.append(
              String.format("<text x=\"10\" y=\"40\" font-size=\"25px\""
                      + "text-anchor=\"left\" fill=\"black\" "
                      + "font-weight=\"bold\""
                      + ">%s</text>\n", snapshot.getID()));
      //description if available
      if (!snapshot.getDescription().isEmpty()) {
        outputFileContent.append(
                String.format("<text x=\"10\" y=\"70\" font-size=\"18px\""
                        + "text-anchor=\"left\" fill=\"black\" "
                        + "> Description: %s</text>\n", snapshot.getDescription()));
      }

      //put shapes into html
      for (IShape each : snapshot.getShapes()) {
        String aShape = null;
        if (each instanceof Rectangle) { // create rectangle
          aShape = String.format("""
                          <rect width="%f" height="%f" x="%f" y="%f" style="fill:rgb(%f,%f,%f)"/>;
                          """,
                  each.getDimensionX(), each.getDimensionY(),
                  // slight shift for the format
                  each.getPosition().getX() + 5, each.getPosition().getY() + 100,
                  each.getColor().getRed(), each.getColor().getGreen(), each.getColor().getBlue());
        } else if (each instanceof Oval) { // create oval
          aShape = String.format("""
                          <ellipse rx="%f" ry="%f" cx="%f" cy="%f" style="fill:rgb(%f,%f,%f)"/>;
                          """,
                  each.getDimensionX(), each.getDimensionY(),
                  // slight shift for the format
                  each.getPosition().getX() + 5, each.getPosition().getY() + 100,
                  each.getColor().getRed(), each.getColor().getGreen(), each.getColor().getBlue());
        } else {
          throw new IllegalArgumentException("Type of shape doesn't exist.");
        }
        outputFileContent.append(aShape);
      }
      outputFileContent.append("</svg>\n")
              //the next line is just some blank spacer between two snapshots
              .append("<svg width=\"1000\" height=\"1\"></svg>\n");
      outputFileContent.append("</html>");//end the file
    }
  }

  /**
   * Returns the text in html format.
   * This is a back door method for testing but also could benefit
   * when you just want the text in the html form for later adjustment
   * but not directly to a file
   *
   * @return output string
   */
  @Override
  public String toString() {
    return outputFileContent.toString();
  }
}
