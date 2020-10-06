package com.noisyapple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

// MovingObject class.
@SuppressWarnings("serial")
public class MovingObject extends JFrame {

  // Control constants.
  private final int UPDATE_RATE = 60;

  // Panel bounds.
  private int width, height;

  // Objects Array.
  Circle[] circles;

  // Class constructor.
  public MovingObject(int width, int height, int objectAmount) {
    this.width = width;
    this.height = height;

    circles = new Circle[objectAmount];

    for (int i = 0; i < circles.length; i++) {
      circles[i] = new Circle(width, height, (int) (Math.random() * 20 + 5));
    }

    // Window configuration.
    this.setTitle("Moving Object");
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    // Panel is added to the window.
    this.add(new CustomCanvas());

    // Window position and size.
    this.pack();
    this.setLocationRelativeTo(null);

    // Loop.
    startLoop();
  }

  // Loop for rerendering and updating.
  public void startLoop() {
    Thread drawLoop = new Thread() {
      public void run() {
        while (true) {
          repaint();

          try {
            Thread.sleep(1000 / UPDATE_RATE); // Desired frame rate.

          } catch (InterruptedException ex) {
          }
        }
      }
    };

    // Thread execution.
    drawLoop.start();
  }

  // CustomCanvas inner class.
  class CustomCanvas extends JPanel {

    // Overwritten method paintComponent.
    public void paintComponent(Graphics g) {

      // Graphics context g is then casted to Graphics2D.
      Graphics2D g2d = (Graphics2D) g;

      // Context configuration, antialiasing enabled for smoother graphics.
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Background gets drawn.
      g2d.setColor(Color.decode("#000000"));
      g2d.fillRect(0, 0, width, height);

      for (Circle c : circles) {
        c.draw(g2d);
        c.update(width, height);
      }

    }

    // Overwriten method getPreferredSize for window bounds to fit panel size.
    public Dimension getPreferredSize() {
      return (new Dimension(width, height));
    }

  }

}