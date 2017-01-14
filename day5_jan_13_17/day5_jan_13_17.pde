/* @pjs font="./ReenieBeanie.ttf"; */

ArrayList<Line> lines;
PFont font;

void setup() {
  size(500, 500);
  textFont(createFont("ReenieBeanie", 36), 36);
  textSize(36);
  textAlign(CENTER, CENTER);
  fill(0);
  stroke(0);
  reset();
}

void draw() {
  if(random(1) <= 0.1) {
    lines.add(new Line(random(1, 100), random(0, width), random(2, 10), random(30, 50)));
  }
  for(Line l : lines) {
    l.update();
    l.draw();
  }
}

void reset() {
  background(128);
  text("I should", width/2, height/2);
  lines = new ArrayList<Line>();
}

void mouseReleased() {
  reset();
}

void keyReleased() {
  reset();
}

class Line {
  Line(float t, float x, float wgt, float clr) {
    this.t = t;
    this.px = x;
    this.x = x;
    this.py = 0;
    this.y = 0;
    this.wgt = wgt;
    this.clr = clr;
  }

  void update() {
    if(y < height) {
      t  += 0.01;
      px =  x;
      x  += randomGaussian()/10.0;
      py =  y;
      y  += max(0, noise(t)-0.25);
      wgt += randomGaussian()/10.0;
      wgt = max(1, wgt);
      clr += randomGaussian()/10.0;
      clr = max(0, clr);
    }
  }

  void draw() {
    stroke(clr);
    strokeWeight(wgt);
    line(px, py, x, y);
  }

  float t, x, px, y, py, wgt, clr;
}
