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

public class day_10_jan_18_17 extends PApplet {

final int dim = 50;
final int scale = 10;
Vertex[][] vs = new Vertex[dim][dim];
ArrayList<Line> ls = new ArrayList<Line>();
int curr = 0;

public void setup() {
  
  strokeWeight(scale/2.0f);
  strokeCap(SQUARE);

  for(int x = 0; x < vs.length; x++) {
    for(int y = 0; y < vs[x].length; y++) {
      vs[x][y] = new Vertex();
      if(x < vs.length-1) {
        vs[x][y].edges.add(new Edge(x+1, y, random(1)));
      }
      if(x > 0) {
        vs[x][y].edges.add(new Edge(x-1, y, random(1)));
      }
      if(y < vs[x].length-1) {
        vs[x][y].edges.add(new Edge(x, y+1, random(1)));
      }
      if(y > 0) {
        vs[x][y].edges.add(new Edge(x, y-1, random(1)));
      }
    }
  }

  reset();
}

public void dfs(int x, int y) {
  vs[x][y].visited = true;
  float[] wgts = new float[vs[x][y].edges.size()];
  ArrayList<Edge> edges = (ArrayList<Edge>)vs[x][y].edges.clone();
  for(int i = 0; i < wgts.length; i++) {
    wgts[i] = edges.get(i).wgt;
  }
  wgts = sort(wgts);
  for(int i = 0; i < wgts.length; i++) {
    int match = -1;
    for(int j = 0; j < edges.size(); j++) {
      if(edges.get(j).wgt == wgts[i]) {
        match = j;
        break;
      }
    }
    Edge e = edges.get(match);
    edges.remove(match);
    if(!vs[e.x][e.y].visited) {
      // line(x*scale+scale/2.0, y*scale+scale/2.0, e.x*scale+scale/2.0, e.y*scale+scale/2.0);
      ls.add(new Line(x*scale+scale/2.0f, y*scale+scale/2.0f, e.x*scale+scale/2.0f, e.y*scale+scale/2.0f));
      dfs(e.x, e.y);
    }
  }
}

public void reset() {
  ls = new ArrayList<Line>();
  curr = 0;
  for(int x = 0; x < vs.length; x++) {
    for(int y = 0; y < vs[x].length; y++) {
      vs[x][y].visited = false;
    }
  }
  dfs(PApplet.parseInt(random(vs.length)), PApplet.parseInt(random(vs[0].length)));
}

public void draw() {
  background(255);
  for(int i = 0; i <= curr; i++) {
    line(ls.get(i).x1, ls.get(i).y1, ls.get(i).x2, ls.get(i).y2);
  }
  if(curr < ls.size()-1) {
    curr = min(curr+4, ls.size()-1);
  }
  else {
    reset();
  }
}

class Vertex {
  Vertex() {
    edges = new ArrayList<Edge>();
    visited = false;
  }

  ArrayList<Edge> edges;
  boolean visited;
}

class Edge {
  Edge(int x, int y, float wgt) {
    this.x = x;
    this.y = y;
    this.wgt = wgt;
  }

  int x, y;
  float wgt;
}

class Line {
  Line(float x1, float y1, float x2, float y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  float x1, y1, x2, y2;
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "day_10_jan_18_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
