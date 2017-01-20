ArrayList<Rect> rects = new ArrayList<Rect>();
final float diag = sqrt(500*500*2);

void setup() {
  size(500, 500);
  rects.add(new Rect(new PVector(random(width), random(height)), 50, 50));
  noStroke();
}

void draw() {
  background(0);
  for(int r = int(diag+1); r > 0; r -= 10) {
    fill(lerpColor(#FCE94F, 0, map(r*r, 0, (int(diag+1))*(int(diag+1)), 0, 1)));
    ellipse(mouseX, mouseY, r*2, r*2);
  }
  fill(0);
  PVector mouse = new PVector(mouseX, mouseY);
  for(Rect r : rects) {
    if(mouseX >= r.corners[0].x && mouseX <= r.corners[2].x && mouseY >= r.corners[0].y && mouseY <= r.corners[2].y) {
      fill(0);
      rect(0, 0, width, height);
      break;
    }
    PVector[] lns = new PVector[4];
    PVector l = new PVector();
    for(int i = 0; i < 4; i++) {
      l = r.corners[i].get();
      l.sub(mouse);
      l.normalize();
      l.mult(width+height);
      lns[i] = l;
    }
    beginShape(QUADS);
    vertex(r.corners[0].x, r.corners[0].y);
    vertex(r.corners[1].x, r.corners[1].y);
    vertex(lns[1].x, lns[1].y);
    vertex(lns[0].x, lns[0].y);

    vertex(r.corners[0].x, r.corners[0].y);
    vertex(r.corners[2].x, r.corners[2].y);
    vertex(lns[2].x, lns[2].y);
    vertex(lns[0].x, lns[0].y);

    vertex(r.corners[0].x, r.corners[0].y);
    vertex(r.corners[3].x, r.corners[3].y);
    vertex(lns[3].x, lns[3].y);
    vertex(lns[0].x, lns[0].y);

    vertex(r.corners[1].x, r.corners[1].y);
    vertex(r.corners[2].x, r.corners[2].y);
    vertex(lns[2].x, lns[2].y);
    vertex(lns[1].x, lns[1].y);

    vertex(r.corners[1].x, r.corners[1].y);
    vertex(r.corners[3].x, r.corners[3].y);
    vertex(lns[3].x, lns[3].y);
    vertex(lns[1].x, lns[1].y);

    vertex(r.corners[2].x, r.corners[2].y);
    vertex(r.corners[3].x, r.corners[3].y);
    vertex(lns[3].x, lns[3].y);
    vertex(lns[2].x, lns[2].y);
    endShape();
  }
  fill(32);
  for(Rect r : rects) {
    r.draw();
  }
}

void mouseReleased() {
  rects.add(new Rect(new PVector(mouseX, mouseY), 50, 50));
}

class Rect {
  /*
   * 0--1
   * |  |
   * 3--2
   */
  Rect(PVector v, float w, float h) {
    this.w = w;
    this.h = h;
    corners = new PVector[4];
    corners[0] = v;
    corners[1] = new PVector(v.x+w, v.y);
    corners[2] = new PVector(v.x+w, v.y+h);
    corners[3] = new PVector(v.x, v.y+h);
  }

  void draw() {
    rect(corners[0].x, corners[0].y, w, h);
  }

  float w, h;
  PVector[] corners;
}
