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

public class day_19_jan_27_17 extends PApplet {

public void setup() {
  
  background(255);
  textSize(16);
  fill(0);
  String[] lines = {
  "void setup() {",
  "  size(750, 750);",
  "  background(255);",
  "  textSize(16);",
  "  fill(0);",
  "  String[] lines = {",
  "  };",
  "  ",
  "  for(int i = 0; i <= 5; i++) {",
  "    text(lines[i], 10, 20*(i+1));",
  "  }",
  "  for(int i = 0; i < lines.length; i++) {",
  "    text(lines[7] + str(char(34)) + lines[i] + str(char(34)) + str(char(44)), 10, 20*(i+7));",
  "  }",
  "  for(int i = 6; i < lines.length; i++) {",
  "    text(lines[i], 10, 20*(i+19));",
  "  }",
  "}",
  };

  for(int i = 0; i <= 5; i++) {
    text(lines[i], 10, 20*(i+1));
  }
  for(int i = 0; i < lines.length; i++) {
    text(lines[7] + str(PApplet.parseChar(34)) + lines[i] + str(PApplet.parseChar(34)) + str(PApplet.parseChar(44)), 10, 20*(i+7));
  }
  for(int i = 6; i < lines.length; i++) {
    text(lines[i], 10, 20*(i+19));
  }
}
  public void settings() {  size(750, 750); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_19_jan_27_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
