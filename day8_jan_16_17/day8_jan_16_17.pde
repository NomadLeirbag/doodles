ArrayList<Point> points = new ArrayList<Point>();
float vx = random(-4, 4);
float vy = random(-4, 4);

void setup() {
  size(1000, 1000, P3D);
}

void draw() {
  background(255);

  points.add(new Point(mouseX, mouseY));

  for(int i = points.size()-1; i >= 0; i--) {
    points.get(i).update();
    if(points.get(i).dead()) {
      points.remove(i);
    }
  }

  for(int i = 0; i < points.size(); i++) {
    for(int j = i-1; j >= max(0, i-10); j--) {
      stroke(points.get(j).clr);
      line(points.get(i).x, points.get(i).y, -points.get(i).t, points.get(j).x, points.get(j).y, -points.get(i).t);
    }
  }
}

void mouseReleased() {
  vx = map(mouseX, 0, width, -4, 4);
  vy = map(mouseY, 0, height, -4, 4);
}

class Point {
  Point(float x, float y) {
    this.x = x;
    this.y = y;
    this.t = 0;
  }

  void update() {
    x += vx;
    y += vy;
    t += 2;
    clr = lerpColor(color(#CC0000), color(#FFFFFF), t/255.0);
  }

  boolean dead() {
    return t >= 255;
  }

  color clr;
  float x, y, t;
}
