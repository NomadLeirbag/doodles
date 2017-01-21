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

public class day8_jan_16_17 extends PApplet {

ArrayList<Point> points = new ArrayList<Point>();
float vx = random(-4, 4);
float vy = random(-4, 4);

public void setup() {
  
}

public void draw() {
  background(255);

  points.add(new Point(mouseX, mouseY));

  for(int i = points.size()-1; i >= 0; i--) {
    points.get(i).update();
    if(points.get(i).dead()) {
      points.remove(i);
    }
  }

  for(int i = 0; i < points.size(); i++) {
    for(int j = i-1; j >= max(0, i-10); j--) {
      stroke(points.get(j).clr);
      line(points.get(i).x, points.get(i).y, -points.get(i).t, points.get(j).x, points.get(j).y, -points.get(i).t);
    }
  }
}

public void mouseReleased() {
  vx = map(mouseX, 0, width, -4, 4);
  vy = map(mouseY, 0, height, -4, 4);
}

class Point {
  Point(float x, float y) {
    this.x = x;
    this.y = y;
    this.t = 0;
  }

  public void update() {
    x += vx;
    y += vy;
    t += 2;
    clr = lerpColor(color(0xffCC0000), color(0xffFFFFFF), t/255.0f);
  }

  public boolean dead() {
    return t == 255;
  }

  int clr;
  float x, y, t;
}
  public void settings() {  size(1000, 1000, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day8_jan_16_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
