Ball b1, b2, b3;
float t, dt;

void setup() {
  size(500, 500);
  strokeWeight(4);
  t = 0;
  dt = 0.01;
  reset();
}

void reset() {
  background(255);
  b1 = new Ball(random(width), random(height), random(-5, 5), random(-5, 5), random(-0.5, 0.5), random(-0.5, 0.5));
  b2 = new Ball(random(width), random(height), random(-5, 5), random(-5, 5), random(-0.5, 0.5), random(-0.5, 0.5));
  b3 = new Ball(random(width), random(height), random(-5, 5), random(-5, 5), random(-0.5, 0.5), random(-0.5, 0.5));
}

void draw() {
  stroke(lerpColor(color(#75507B), color(#06989A), t));
  b1.update();
  b2.update();
  b3.update();
  line(b1.pos.x, b1.pos.y, b2.pos.x, b2.pos.y);
  line(b2.pos.x, b2.pos.y, b3.pos.x, b3.pos.y);
  t += dt;
  if(t <= 0.0 || t >= 1.0) {
    dt *= -1;
  }
}

void mouseReleased() {
  reset();
}

void keyReleased() {
  reset();
}

class Ball {
  Ball(float x, float y, float vx, float vy, float ax, float ay) {
    pos = new PVector(x, y);
    vel = new PVector(vx, vy);
    acc = new PVector(ax, ay);
  }

  void update() {
    vel.add(acc);
    pos.add(vel);
    if(pos.x <= 0) {
      pos.x = 0;
      vel.x *= -1;
    }
    if(pos.x >= width) {
      pos.x = width;
      vel.x *= -1;
    }
    if(pos.y <= 0) {
      pos.y = 0;
      vel.y *= -1;
    }
    if(pos.y >= height) {
      pos.y = height;
      vel.y *= -1;
    }
  }

  PVector pos, vel, acc;
}
