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

public class jan_10_17 extends PApplet {

ArrayList<Branch> trees;
float dt = 0.01f;
float da = 2.5f;

public void setup() {
  
  trees = new ArrayList<Branch>();
  trees.add(new Branch(100, 0));
  trees.add(new Branch(100, 0));
  trees.add(new Branch(100, 0));
}

public void draw() {
  background(255);
  dt = map(mouseX, 0, width, 0.005f, 0.05f);
  da = map(mouseY, 0, height, 2, PI);
  translate(250, height);
  for(Branch t : trees) {
    t.display();
    translate(250, 0);
  }
}

class Branch {
  Branch(float tlen, float tt) {
    len = tlen;
    t = tt;
    branches = new ArrayList<Branch>();

    tlen *= 0.80f;
    if(tlen > 16) {
      int n = round(randomGaussian()+2);
      println(n);
      for(int i = 0; i < n; i++) {
        branches.add(new Branch(tlen, random(0, 1000)));
      }
    }
  }

  public void display() {
    stroke(0);
    line(0, 0, 0, -len);
    pushMatrix();
    translate(0, -len);
    for(int i = 0; i < branches.size(); i++) {
      pushMatrix();
      rotate(map(noise(t+i), 0, 1, -PI/da, PI/da));
      branches.get(i).display();
      popMatrix();
    }
    if(branches.size() == 0) {
      noStroke();
      fill(0, 105, 148, 8);
      ellipse(0, 0, 50, 50);
    }
    popMatrix();
    t += dt;
  }

  float len, t;
  ArrayList<Branch> branches;
}
  public void settings() {  size(1000, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "jan_10_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
