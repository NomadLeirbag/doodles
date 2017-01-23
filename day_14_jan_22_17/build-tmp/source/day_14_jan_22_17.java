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

public class day_14_jan_22_17 extends PApplet {

PGraphics text;
PGraphics[] frames = new PGraphics[5];
int frame = 0;

public void setup() {
  
  text = createGraphics(200, 100);
  text.beginDraw();
  text.background(255);
  text.textSize(64);
  text.textAlign(CENTER, CENTER);
  text.fill(0);
  text.text("Time", width/2, height/2);
  text.endDraw();
  blurframes(text, 0);
  frameRate(5);
}

public void blurframes(PGraphics orig, int f) {
  for(int r = 0; r < 5; r++) {
    PGraphics blur = createGraphics(200, 100);
    blur.beginDraw();
    blur.loadPixels();
    int[][] neighborhood = new int[(2*r+1)*(2*r+1)][2];
    int i = 0;
    for(int a = -r; a <= r; a++) {
      for(int b = -r; b <= r; b++) {
        neighborhood[i][0] = a;
        neighborhood[i][1] = b;
        i++;
      }
    }
    for(int x = 0; x < orig.width; x++) {
      for(int y = 0; y < orig.height; y++) {
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int[] offset : neighborhood) {
          int newX = x + offset[0];
          int newY = y + offset[1];
          if(newX >= 0 && newX < orig.width && newY >= 0 && newY < orig.height) {
            colors.add(orig.get(newX, newY));
          }
        }
        float[] rgb = {0.0f, 0.0f, 0.0f};
        for(int c : colors) {
          rgb[0] += red(c);
          rgb[1] += green(c);
          rgb[2] += blue(c);
        }
        rgb[0] /= colors.size();
        rgb[1] /= colors.size();
        rgb[2] /= colors.size();
        blur.pixels[200*y+x] = color(rgb[0], rgb[1], rgb[2]);
      }
    }
    blur.updatePixels();
    blur.endDraw();
    frames[f+r] = blur;
  }
}

public void draw() {
  image(frames[frame], 0, 0);
  frame += 1;
  if(frame == 5) {
    frame = 0;
  }
}
  public void settings() {  size(200, 100); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_14_jan_22_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
