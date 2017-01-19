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

public class day5_jan_13_17 extends PApplet {

ArrayList<Line> lines;

public void setup() {
  
  textSize(36);
  textAlign(CENTER, CENTER);
  fill(0);
  stroke(0);
  reset();
}

public void draw() {
  if(random(1) <= 0.1f) {
    lines.add(new Line(random(1, 100), random(0, width), random(2, 10), random(30, 50)));
  }
  for(Line l : lines) {
    l.update();
    l.draw();
  }
}

public void reset() {
  background(128);
  text("I should", width/2, height/2);
  lines = new ArrayList<Line>();
}

public void mouseReleased() {
  reset();
}

public void keyReleased() {
  reset();
}

class Line {
  Line(float t, float x, float wgt, float clr) {
    this.t = t;
    this.px = x;
    this.x = x;
    this.py = 0;
    this.y = 0;
    this.wgt = wgt;
    this.clr = clr;
  }

  public void update() {
    if(y < height) {
      t  += 0.01f;
      px =  x;
      x  += randomGaussian()/10.0f;
      py =  y;
      y  += max(0, noise(t)-0.25f);
      wgt += randomGaussian()/10.0f;
      wgt = max(1, wgt);
      clr += randomGaussian()/10.0f;
      clr = max(0, clr);
    }
  }

  public void draw() {
    stroke(clr);
    strokeWeight(wgt);
    line(px, py, x, y);
  }

  float t, x, px, y, py, wgt, clr;
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day5_jan_13_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
