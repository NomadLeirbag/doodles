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

public class day9_jan_17_17 extends PApplet {

int count = 100;
boolean[][] world = new boolean[count][count];
boolean[][] nextw = new boolean[count][count];

public void setup() {
  
  reset();
  textAlign(CENTER, CENTER);
  noStroke();
}

public void draw() {
  fill(255, 8);
  rect(0, 0, width, height);
  fill(0);
  for(int x = 0; x < count; x++) {
    for(int y = 0; y < count; y++) {
      if(world[x][y]) {
        rect(x*5, y*5, 5, 5);
      }
    }
  }

  for(int x = 0; x < count; x++) {
    for(int y = 0; y < count; y++) {
      int n = live_neighbors(x, y);
      if(world[x][y]) {
        nextw[x][y] = (n == 2 || n == 3);
      }
      else {
        nextw[x][y] = (n == 3);
      }
    }
  }

  world = nextw;
  nextw = new boolean[count][count];
}

public void mouseReleased() {
  reset();
}

public void reset() {
  for(int x = 0; x < count; x++) {
    for(int y = 0; y < count; y++) {
      world[x][y] = (random(1) < noise(x, y));
    }
  }
}

public int live_neighbors(int x, int y) {
  int num = 0;
  if(x > 0) {
    num += PApplet.parseInt(world[x-1][y]);
  }
  if(x < count-1) {
    num += PApplet.parseInt(world[x+1][y]);
  }
  if(y > 0) {
    num += PApplet.parseInt(world[x][y-1]);
  }
  if(y < count-1) {
    num += PApplet.parseInt(world[x][y+1]);
  }
  if(x > 0 && y > 0) {
    num += PApplet.parseInt(world[x-1][y-1]);
  }
  if(x > 0 && y < count-1) {
    num += PApplet.parseInt(world[x-1][y+1]);
  }
  if(x < count-1 && y > 0) {
    num += PApplet.parseInt(world[x+1][y-1]);
  }
  if(x < count-1 && y < count-1) {
    num += PApplet.parseInt(world[x+1][y+1]);
  }
  return num;
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day9_jan_17_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
