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

public class day_13_jan_21_17 extends PApplet {

float t = 0;

public void setup() {
  
  frameRate(20);
}

public void draw() {
  if(randomGaussian() > t) {
    background(0xffFFFF00);
  }
  else {
    background(0xff000000);
  }
  t += 0.001f;
}
  public void settings() {  size(100, 100); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_13_jan_21_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
