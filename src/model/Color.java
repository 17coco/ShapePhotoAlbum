package model;

/**
 * A simple color representation in RGB.
 * So that we don't have to import.
 */
public class Color {
  // Define color constants with corresponding RGB values
  private double red;
  private double green;
  private double blue;

  /**
   * Constructor to initialize color constants with name and RGB values
   */
  public Color(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  /**
   * Gets red.
   *
   * @return the red
   */
  public Double getRed() {
    return red;
  }

  /**
   * Gets green.
   *
   * @return the green
   */
  public Double getGreen() {
    return green;
  }

  /**
   * Gets blue.
   *
   * @return the blue
   */
  public Double getBlue() {
    return blue;
  }


  @Override
  public String toString() {
    return "(" + red +
            "," + green +
            "," + blue +
            ')';
  }
}