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

public class day4_jan_12_17 extends PApplet {

int a = 5;
int b = 4;
float d = 0;

public void setup() {
  
  stroke(0xff00FF00);
}

public void draw() {
  background(0xff1B635E);
  a = PApplet.parseInt(map(mouseX, 0, width, 1, 25));
  b = PApplet.parseInt(map(mouseY, 0, height, 1, 25));
  for(float t = 0; t < 2*PI; t += 0.001f) {
    float x = 100*sin(a*t+d);
    float y = 100*sin(b*t);
    ellipse(x+width/2.0f, y+height/2.0f, 1, 1);
  }
  d += 0.1f;
}
  public void settings() {  size(250, 250); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day4_jan_12_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
