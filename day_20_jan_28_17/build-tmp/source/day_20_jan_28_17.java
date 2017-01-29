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

public class day_20_jan_28_17 extends PApplet {

float sz = 200;
float mult = -2;
float t = 0;

public void setup() {
  
  noStroke();
}

public void draw() {
  background(255);

  sz += mult*abs(noise(t));
  t += 0.01f;
  if(sz < 0 || random(1) < 0.005f) {
    sz = 200;
    mult = -random(3);
  }


  translate(width/2, height/2);
  scale(sz/250.0f);

  fill(0xffD9D9D9);
  quad(-50, 500-300, -50, 300-300, 50, 100-300, 50, 500-300);
  fill(0xffBFBFBF);
  quad(-50, 350-300, -50, 300-300, 50, 100-300, 50, 150-300);
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_20_jan_28_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
