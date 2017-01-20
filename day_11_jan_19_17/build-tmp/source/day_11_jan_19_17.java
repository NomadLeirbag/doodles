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

public class day_11_jan_19_17 extends PApplet {

ArrayList<Rect> rects = new ArrayList<Rect>();
final float diag = sqrt(500*500*2);

public void setup() {
  
  rects.add(new Rect(new PVector(random(width), random(height)), 50, 50));
  noStroke();
}

public void draw() {
  println(mouseX);
  background(255);
  // for(int r = int(diag+1); r > 0; r -= 10) {
  //   fill(lerpColor(#FCE94F, 0, map(r*r, 0, (int(diag+1))*(int(diag+1)), 0, 1)));
  //   ellipse(mouseX, mouseY, r*2, r*2);
  // }
  fill(0);
  PVector mouse = new PVector(mouseX, mouseY);
  for(Rect r : rects) {
    if(mouseX >= r.corners[0].x && mouseX <= r.corners[2].x && mouseY >= r.corners[0].y && mouseY <= r.corners[2].y) {
      fill(0);
      rect(0, 0, width, height);
      break;
    }
    PVector[] lns = new PVector[4];
    PVector l;
    for(int i = 0; i < 4; i++) {
      l = r.corners[i].get();
      l = l.sub(mouse);
      l.normalize();
      l.mult(width+height);
      lns[i] = l;
    }
    beginShape(QUADS);
    vertex(r.corners[0].x, r.corners[0].y);
    vertex(r.corners[1].x, r.corners[1].y);
    vertex(lns[1].x, lns[1].y);
    vertex(lns[0].x, lns[0].y);

    vertex(r.corners[0].x, r.corners[0].y);
    vertex(r.corners[2].x, r.corners[2].y);
    vertex(lns[2].x, lns[2].y);
    vertex(lns[0].x, lns[0].y);

    vertex(r.corners[0].x, r.corners[0].y);
    vertex(r.corners[3].x, r.corners[3].y);
    vertex(lns[3].x, lns[3].y);
    vertex(lns[0].x, lns[0].y);

    vertex(r.corners[1].x, r.corners[1].y);
    vertex(r.corners[2].x, r.corners[2].y);
    vertex(lns[2].x, lns[2].y);
    vertex(lns[1].x, lns[1].y);

    vertex(r.corners[1].x, r.corners[1].y);
    vertex(r.corners[3].x, r.corners[3].y);
    vertex(lns[3].x, lns[3].y);
    vertex(lns[1].x, lns[1].y);

    vertex(r.corners[2].x, r.corners[2].y);
    vertex(r.corners[3].x, r.corners[3].y);
    vertex(lns[3].x, lns[3].y);
    vertex(lns[2].x, lns[2].y);
    endShape();
  }
  fill(32);
  for(Rect r : rects) {
    r.draw();
  }
}

public void mouseReleased() {
  rects.add(new Rect(new PVector(mouseX, mouseY), 50, 50));
}

class Rect {
  /*
   * 0--1
   * |  |
   * 3--2
   */
  Rect(PVector v, float w, float h) {
    this.w = w;
    this.h = h;
    corners = new PVector[4];
    corners[0] = v;
    corners[1] = new PVector(v.x+w, v.y);
    corners[2] = new PVector(v.x+w, v.y+h);
    corners[3] = new PVector(v.x, v.y+h);
  }

  public void draw() {
    rect(corners[0].x, corners[0].y, w, h);
  }

  float w, h;
  PVector[] corners;
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_11_jan_19_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
