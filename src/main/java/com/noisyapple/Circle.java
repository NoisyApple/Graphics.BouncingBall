package com.noisyapple;

import java.awt.Graphics2D;
import java.awt.Color;

public class Circle {

  private final int UP = 0;
  private final int UP_RIGHT = 1;
  private final int RIGHT = 2;
  private final int DOWN_RIGHT = 3;
  private final int DOWN = 4;
  private final int DOWN_LEFT = 5;
  private final int LEFT = 6;
  private final int UP_LEFT = 7;

  private int x, y;
  private float speed;
  private int direction;
  private int size;
  private Color color;

  public Circle(int width, int height, int size) {
    this.size = size;
    this.x = (int) (Math.random() * width) - size;
    this.y = (int) (Math.random() * height) - size;
    this.speed = 2;
    this.direction = (int) Math.floor(Math.random() * 8);
    this.color = Color.getHSBColor((float) Math.random(), 0.6f, 1f);
  }

  public void update(int xBound, int yBound) {

    int xDir = 0;
    int yDir = 0;

    switch (this.direction) {
      case UP:
        xDir = 0;
        yDir = -1;
        break;
      case UP_RIGHT:
        xDir = 1;
        yDir = -1;
        break;
      case RIGHT:
        xDir = 1;
        yDir = 0;
        break;
      case DOWN_RIGHT:
        xDir = 1;
        yDir = 1;
        break;
      case DOWN:
        xDir = 0;
        yDir = 1;
        break;
      case DOWN_LEFT:
        xDir = -1;
        yDir = 1;
        break;
      case LEFT:
        xDir = -1;
        yDir = 0;
        break;
      case UP_LEFT:
        xDir = -1;
        yDir = -1;
        break;
    }

    // Wall collitions handler.
    if (this.x + this.speed * xDir >= (xBound - this.size)
        || this.y + this.speed * xDir + this.speed * yDir >= (yBound - this.size) || this.x + this.speed * xDir < 0
        || this.y + this.speed * xDir + this.speed * yDir < 0) {
      this.direction = newDirection(this.direction);
    } else {
      this.x += this.speed * xDir;
      this.y += this.speed * yDir;
    }
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.color);
    g2d.fillOval(this.x, this.y, this.size, this.size);
  }

  public int newDirection(int actualDirection) {
    int newDirection;
    int[] posibleDirections = new int[0];

    switch (actualDirection) {
      case UP:
        posibleDirections = new int[] { RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT };
        break;
      case UP_RIGHT:
        posibleDirections = new int[] { LEFT, DOWN_LEFT, DOWN };
        break;
      case RIGHT:
        posibleDirections = new int[] { UP, DOWN, DOWN_LEFT, LEFT, UP_LEFT };
        break;
      case DOWN_RIGHT:
        posibleDirections = new int[] { LEFT, UP_LEFT, UP };
        break;
      case DOWN:
        posibleDirections = new int[] { LEFT, UP_LEFT, UP, UP_RIGHT, RIGHT };
        break;
      case DOWN_LEFT:
        posibleDirections = new int[] { UP, UP_RIGHT, RIGHT };
        break;
      case LEFT:
        posibleDirections = new int[] { UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN };
        break;
      case UP_LEFT:
        posibleDirections = new int[] { RIGHT, DOWN_RIGHT, DOWN };
        break;
    }

    // String[] names = new String[] { "UP", "UP_RIGHT", "RIGHT", "DOWN_RIGHT",
    // "DOWN", "DOWN_LEFT", "LEFT", "UP_LEFT" };

    newDirection = posibleDirections[((int) Math.floor(Math.random() * posibleDirections.length))];
    // if (actualDirection == DOWN)
    // System.out.println(names[actualDirection] + " => " + names[newDirection]);
    return newDirection;
  }

}
