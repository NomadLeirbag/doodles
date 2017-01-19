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

public class day7_jan_15_17 extends PApplet {

float phi = 1.61803398875f;
PVector[] vs = new PVector[12];
Triangle[] ts = new Triangle[20];

float ax = 0;
float ay = PI/6;
float az = PI/2;

public void setup() {
  
  stroke(255);
  fill(0xffFFFFFF);

  vs[0 ] = new PVector(0, -1, -phi);
  vs[1 ] = new PVector(0, -1, +phi);
  vs[2 ] = new PVector(0, +1, -phi);
  vs[3 ] = new PVector(0, +1, +phi);
  vs[4 ] = new PVector(-1, -phi, 0);
  vs[5 ] = new PVector(-1, +phi, 0);
  vs[6 ] = new PVector(+1, -phi, 0);
  vs[7 ] = new PVector(+1, +phi, 0);
  vs[8 ] = new PVector(-phi, 0, -1);
  vs[9 ] = new PVector(+phi, 0, -1);
  vs[10] = new PVector(-phi, 0, +1);
  vs[11] = new PVector(+phi, 0, +1);

  ts[0 ] = new Triangle(vs[0], vs[2], vs[8]);
  ts[1 ] = new Triangle(vs[0], vs[8], vs[4]);
  ts[2 ] = new Triangle(vs[0], vs[4], vs[6]);
  ts[3 ] = new Triangle(vs[0], vs[6], vs[9]);
  ts[4 ] = new Triangle(vs[0], vs[9], vs[2]);
  ts[5 ] = new Triangle(vs[1], vs[4], vs[6]);
  ts[6 ] = new Triangle(vs[1], vs[6], vs[11]);
  ts[7 ] = new Triangle(vs[1], vs[11], vs[3]);
  ts[8 ] = new Triangle(vs[1], vs[3], vs[10]);
  ts[9 ] = new Triangle(vs[1], vs[10], vs[4]);
  ts[10] = new Triangle(vs[6], vs[9], vs[11]);
  ts[11] = new Triangle(vs[4], vs[8], vs[10]);
  ts[12] = new Triangle(vs[5], vs[2], vs[7]);
  ts[13] = new Triangle(vs[5], vs[7], vs[3]);
  ts[14] = new Triangle(vs[5], vs[3], vs[10]);
  ts[15] = new Triangle(vs[5], vs[10], vs[8]);
  ts[16] = new Triangle(vs[5], vs[8], vs[2]);
  ts[17] = new Triangle(vs[7], vs[2], vs[9]);
  ts[18] = new Triangle(vs[7], vs[9], vs[11]);
  ts[19] = new Triangle(vs[7], vs[11], vs[3]);
}

public void draw() {
  background(0);
  // spotLight(255, 0, 0, width/2, height/2*2.5, 400, 0, 0, -1, PI, 2);
  // lights();
  directionalLight(51, 102, 126, -1, 0, 0);

  // spotLight(51, 102, 126, 80, 20, 40, -1, 0, 0, PI/2, 2);

  translate(width/2.0f, height/2.0f, 0);
  rotateX(ax);
  rotateY(ay);
  rotateZ(az);

  ax += 0.01f;
  ay += 0.01f;
  az += 0.01f;

  for(Triangle t : ts) {
    t.draw();
  }
}

class Triangle {
  Triangle(PVector v1, PVector v2, PVector v3) {
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.s  = 100;
  }

  public void draw() {
    beginShape();
    vertex(v1.x*s, v1.y*s, v1.z*s);
    vertex(v2.x*s, v2.y*s, v2.z*s);
    vertex(v3.x*s, v3.y*s, v3.z*s);
    vertex(v1.x*s, v1.y*s, v1.z*s);
    endShape();
  }

  float s;
  PVector v1, v2, v3;
}

  public void settings() {  size(500, 500, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day7_jan_15_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
