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

public class day3_jan_11_17 extends PApplet {

final float G = 10;
final float pm = 5;
final int scale = 20;

ArrayList<ArrayList<Point>> points = new ArrayList<ArrayList<Point>>();
ArrayList<Triangle> triangles = new ArrayList<Triangle>();

public void setup() {
  
  for(int y = -scale; y <= height+scale; y += 2*scale) {
    ArrayList<Point> row = new ArrayList<Point>();
    for(int x = -scale; x <= width+scale; x += scale) {
      row.add(new Point(x, y, pm));

    }
    points.add(row);
    row = new ArrayList<Point>();
    for(int x = -scale/2; x <= width+scale/2; x += scale) {
      row.add(new Point(x, y+scale, pm));
    }
    points.add(row);
  }
  for(int ri = 1; ri < points.size()-1; ri += 2) {
    for(int pi = 0; pi < points.get(ri).size()-1; pi++) {
      Point p1 = points.get(ri).get(pi);
      Point p2 = points.get(ri).get(pi+1);
      Point p3 = points.get(ri-1).get(pi+1);
      Point p4 = points.get(ri+1).get(pi+1);
      triangles.add(new Triangle(p1, p2, p3));
      triangles.add(new Triangle(p1, p2, p4));
    }
  }
  for(int ri = 2; ri < points.size()-1; ri += 2) {
    for(int pi = 0; pi < points.get(ri).size()-1; pi++) {
      Point p1 = points.get(ri).get(pi);
      Point p2 = points.get(ri).get(pi+1);
      Point p3 = points.get(ri-1).get(pi);
      Point p4 = points.get(ri+1).get(pi);
      triangles.add(new Triangle(p1, p2, p3));
      triangles.add(new Triangle(p1, p2, p4));
    }
  }
}

public void draw() {
  background(255);
  Point mouse = new Point(mouseX, mouseY, 2*pm);
  for(ArrayList<Point> row : points) {
    for(Point p : row) {
      mouse.attract(p);
      Point orig_pos = new Point(p.orig_pos.x, p.orig_pos.y, pm);
      orig_pos.attract(p);
      p.update();
    }
  }
  for(Triangle t : triangles) {
    t.display();
  }
}

class Point {
  Point(float x, float y, float m) {
    pos = new PVector(x, y);
    orig_pos = pos.get();
    vel = new PVector(0, 0);
    acc = new PVector(0, 0);
    this.m = m;
  }

  public void applyForce(PVector force) {
    PVector f = PVector.div(force, m);
    acc.add(f);
  }

  public void attract(Point other) {
    PVector force = PVector.sub(pos, other.pos);
    float dist = force.mag();
    dist = max(30, dist);
    force.normalize();
    float strength = (G*m*other.m)/(dist*dist);
    force.mult(strength);
    other.applyForce(force);
  }

  public void update() {
    vel.add(acc);
    pos.add(vel);
    acc.mult(0);
    float dist = pos.dist(orig_pos);
    PVector diff;
    if(dist > 10) {
      diff = pos.get();
      diff.sub(orig_pos);
      diff.normalize();
      diff.mult(10);
      pos = orig_pos.get();
      pos.add(diff);
    }
  }

  public void display() {
    ellipse(pos.x, pos.y, m, m);
  }

  PVector pos, orig_pos, vel, acc;
  float m;
}

class Triangle {
  Triangle(Point p1, Point p2, Point p3) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }

  public void display() {
    float a = abs(p1.pos.dist(p2.pos));
    float b = abs(p2.pos.dist(p3.pos));
    float c = abs(p3.pos.dist(p1.pos));
    float s = (a+b+c)/2.0f;
    float area = sqrt(s*(s-a)*(s-b)*(s-c));
    fill(lerpColor(color(0xff20908C), color(0xffFBE622), map(area, 0, 609, 0, 1)));
    triangle(p1.pos.x, p1.pos.y, p2.pos.x, p2.pos.y, p3.pos.x, p3.pos.y);
  }

  Point p1, p2, p3;
}

float mi;
float ma;

public float myconstrain(float a, float b, float c) {
  float rtrn = constrain(a, b, c);
  if(abs(rtrn - a) > 0.001f) {
    mi = min(mi, a);
    ma = max(ma, a);
    println(mi, ma);
  }
  return rtrn;
}
  public void settings() {  size(1000, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day3_jan_11_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
