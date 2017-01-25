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

public class day_16_jan_24_17 extends PApplet {

float prevX = -1;
float prevY = -1;
PVector prevParallel = new PVector(-1, -1);
boolean cross = false;

public void setup() {
  
  background(255);
}

public void draw() {
  fill(255, 4);
  rect(0, 0, width, height);
  if(prevX != -1) {
    PVector parallel = new PVector(mouseX, mouseY);
    parallel.sub(new PVector(prevX, prevY));
    parallel.rotate(HALF_PI);
    // line(mouseX-parallel.x/2, mouseY-parallel.y/2, mouseX+parallel.x/2, mouseY+parallel.y/2);
    // line(prevX-prevParallel.x/2, prevY-prevParallel.y/2, prevX+prevParallel.x/2, prevY+prevParallel.y/2);
    if(cross) {
      line(prevX, prevY, mouseX+parallel.x/2, mouseY+parallel.y/2);
      line(prevX-prevParallel.x/2, prevY-prevParallel.y/2, mouseX, mouseY);
      line(prevX+prevParallel.x/2, prevY+prevParallel.y/2, mouseX-parallel.x/2, mouseY-parallel.y/2);
    }
    else {
      line(prevX, prevY, mouseX, mouseY);
      line(prevX-prevParallel.x/2, prevY-prevParallel.y/2, mouseX-parallel.x/2, mouseY-parallel.y/2);
      line(prevX+prevParallel.x/2, prevY+prevParallel.y/2, mouseX+parallel.x/2, mouseY+parallel.y/2);
    }
    cross = !cross;
    prevParallel = parallel.get();
  }
  prevX = mouseX;
  prevY = mouseY;
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_16_jan_24_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
