import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class day6_jan_14_17 extends PApplet {

Ball b1, b2, b3;
float t, dt;

public void setup() {
  
  fill(255, 0.5f);
  strokeWeight(4);
  t = 0;
  dt = 0.01f;
  reset();
}

public void reset() {
  background(255);
  b1 = new Ball(random(width), random(height), random(-5, 5), random(-5, 5), random(-0.5f, 0.5f), random(-0.5f, 0.5f));
  b2 = new Ball(random(width), random(height), random(-5, 5), random(-5, 5), random(-0.5f, 0.5f), random(-0.5f, 0.5f));
  b3 = new Ball(random(width), random(height), random(-5, 5), random(-5, 5), random(-0.5f, 0.5f), random(-0.5f, 0.5f));
}

public void draw() {
  noStroke();
  rect(0, 0, width, height);
  stroke(lerpColor(color(0xff75507B), color(0xff06989A), t));
  b1.update();
  b2.update();
  b3.update();
  line(b1.pos.x, b1.pos.y, b2.pos.x, b2.pos.y);
  line(b2.pos.x, b2.pos.y, b3.pos.x, b3.pos.y);
  t += dt;
  if(t <= 0.0f || t >= 1.0f) {
    dt *= -1;
  }
}

public void mouseReleased() {
  reset();
}

public void keyReleased() {
  reset();
}

class Ball {
  Ball(float x, float y, float vx, float vy, float ax, float ay) {
    pos = new PVector(x, y);
    vel = new PVector(vx, vy);
    acc = new PVector(ax, ay);
  }

  public void update() {
    vel.add(acc);
    pos.add(vel);
    if(pos.x <= 0) {
      pos.x = 0;
      vel.x *= -1;
    }
    if(pos.x >= width) {
      pos.x = width;
      vel.x *= -1;
    }
    if(pos.y <= 0) {
      pos.y = 0;
      vel.y *= -1;
    }
    if(pos.y >= height) {
      pos.y = height;
      vel.y *= -1;
    }
  }

  PVector pos, vel, acc;
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day6_jan_14_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
