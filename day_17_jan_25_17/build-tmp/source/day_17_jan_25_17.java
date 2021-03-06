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

public class day_17_jan_25_17 extends PApplet {

Planet[] ps = new Planet[3];

public void setup() {
  
  for(int i = 0; i < ps.length; i++) {
    ps[i] = new Planet(random(5, 50), new PVector(random(width), random(height)), new PVector(random(-1, 1), random(-1, 1)));
  }
}

public void draw() {
  for(Planet p1 : ps) {
    for(Planet p2 : ps) {
      if(p1 != p2) {
        p2.attract(p1);
      }
    }
  }
  float x = 0;
  float y = 0;
  float total = 0.0f;
  for(Planet p : ps) {
    x += p.pos.x*p.m;
    y += p.pos.y*p.m;
    total += p.m;
  }
  x /= total;
  y /= total;
  translate(-x+width/2, -y+height/2);
  for(Planet p : ps) {
    p.update();
    p.draw();
  }
}

class Planet {
  Planet(float m, PVector pos, PVector vel) {
    this.m = m;
    this.pos = pos;
    this.vel = vel;
    this.acc = new PVector(0, 0);
  }

  public void attract(Planet other) {
    PVector force = pos.get();
    force.sub(other.pos);
    force.normalize();
    float d = dist(pos.x, pos.y, other.pos.x, other.pos.y);
    float strength = 100*m*other.m/(d*d);
    strength = min(strength, 10);
    force.mult(strength);
    other.applyForce(force);
  }

  public void applyForce(PVector force) {
    PVector f = force.get();
    f.div(m);
    acc.add(f);
  }

  public void update() {
    vel.add(acc);
    pos.add(vel);
    acc = new PVector(0, 0);
  }

  public void draw() {
    ellipse(pos.x, pos.y, m, m);
  }

  float m;
  PVector pos, vel, acc;
}
  public void settings() {  size(750, 750); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_17_jan_25_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
