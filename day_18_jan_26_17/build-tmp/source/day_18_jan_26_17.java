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

public class day_18_jan_26_17 extends PApplet {

public void setup() {
  
  background(255);
  textSize(20);
  fill(0);

  String lines[] =loadStrings(
    "http://dailydigitaldoodlesdoubtfullyduelingdepression.gabriel-damon.com/day_18_jan_26_17/day_18_jan_26_17.pde");
  text("There are " + lines.length + " lines:", 20, 40);
  for(int i = 0 ; i < lines.length; i++) {
    text(lines[i], 20, 40+20*(i+2));
  }
}

public void draw() {
  // Untitled #18
  // Gabriel Damon
  // medium: screen, code
}
  public void settings() {  size(1000, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_18_jan_26_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
