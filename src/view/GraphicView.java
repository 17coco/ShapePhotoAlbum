package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import model.IShape;
import model.Rectangle;
import model.Oval;
import model.Snapshot;

/**
 * The type Graphic view.
 */
public class GraphicView extends JFrame implements IView {

  private JButton prev, next;
  private JButton select;
  private JButton quit;
  private List<IShape> shapes;


  /**
   * Instantiates a new Graphic view.
   *
   * @param x the width
   * @param y the height
   */
  public GraphicView(int x, int y) {
    super("CS5004 Shapes Photo Album Viewer");

    if (x > 0 && y > 0) { //handle it when no dimension is set
      setSize(x, y);
    } else {
      setSize(1000, 1000);
    }

    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    prev = new JButton("<< prev <<");
    select = new JButton("^^ select ^^");
    next = new JButton(">> next >>");
    quit = new JButton("xx Quit xx");

    prev.setActionCommand("prev");
    select.setActionCommand("select");
    next.setActionCommand("next");
    quit.setActionCommand("quit");

    buttonPanel.add(prev);
    buttonPanel.add(select);
    buttonPanel.add(next);
    buttonPanel.add(quit);
    this.add(buttonPanel, BorderLayout.SOUTH);
    //DrawPanel draw = new DrawPanel();
    //this.add(draw, BorderLayout.CENTER);

  }

  /**
   * Makes the actual view.
   * In GraphicView's case, the method starts an interactive UI.
   */
  @Override
  public void displayWindow() {
    this.setVisible(true);
  }

  /**
   * Sets Action of teh buttons.
   *
   * @param listener action listener
   */
  @Override
  public void setAction(ActionListener listener) {
    prev.addActionListener(listener);
    select.addActionListener(listener);
    next.addActionListener(listener);
    quit.addActionListener(listener);
  }

  /**
   * The Draw panel.
   */
  class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (IShape each : shapes) {
        if (each instanceof Rectangle) {
          drawRectangle(g, each);
        } else if (each instanceof Oval) {
          drawOval(g, each);
        }
      }
    }

    /**
     * Draws an rectangle.
     *
     * @param g     graphics
     * @param shape an IShape
     */
    private void drawRectangle(Graphics g, IShape shape) {
      // I used double for all dimensions, now I need to round them back to int
      // Draw rectangle
      g.setColor(new Color((int) Math.round(shape.getColor().getRed()),
              (int) Math.round(shape.getColor().getGreen()),
              (int) Math.round(shape.getColor().getBlue()))); // Set color

      g.fillRect((int) Math.round(shape.getPosition().getX()),
              (int) Math.round(shape.getPosition().getY()),
              (int) Math.round(shape.getDimensionX()),
              (int) Math.round(shape.getDimensionY())); // Draw rectangle
    }

    /**
     * Draws an oval.
     *
     * @param g     graphics
     * @param shape an IShape
     */
    private void drawOval(Graphics g, IShape shape) {
      // Draw oval
      g.setColor(new Color((int) Math.round(shape.getColor().getRed()),
              (int) Math.round(shape.getColor().getGreen()),
              (int) Math.round(shape.getColor().getBlue()))); // Set color

      g.fillOval((int) Math.round(shape.getPosition().getX()),
              (int) Math.round(shape.getPosition().getY()),
              (int) Math.round(shape.getDimensionX()),
              (int) Math.round(shape.getDimensionY())); // Draw rectangle
    }
  }


  /**
   * Update shapes that are being displayed.
   *
   * @param snapshot a snapshot
   */
  @Override
  public void updateShapes(Snapshot snapshot) {

    this.shapes = snapshot.getShapes();
    //draw the shapes
    DrawPanel draw = new DrawPanel();
    //draw id and description
    JPanel infoPanel = new JPanel();
    JLabel id = new JLabel(snapshot.getID());
    infoPanel.setLayout(new BorderLayout());
    infoPanel.add(id, BorderLayout.NORTH);
    if (!snapshot.getDescription().isEmpty()) {
      JLabel description = new JLabel(snapshot.getDescription());
      infoPanel.add(description, BorderLayout.SOUTH);
    }
    infoPanel.setBackground(Color.ORANGE);
    this.add(draw, BorderLayout.CENTER);
    this.add(infoPanel, BorderLayout.NORTH);

  }

}