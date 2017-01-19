void setup() {
  size(1000, 1000);
}

void draw() {
  background(255);
  rectMode(CENTER);
  drawSquare(width/2, height/2, map(mouseY, 0, 1000, 200, 800), map(mouseX, 0, 1000, 0, PI/2));
}

void drawSquare(float x, float y, float size, float angle) {
  pushMatrix();
  translate(x, y);
  // stroke(0);
  noStroke();
  // noFill();
  fill(0, 256, 0, 32);
  rect(0, 0, size, size);
  if(size > 8) {
    rotate(angle);
    drawSquare(size/2, 0, size/2, angle);
    drawSquare(0, -size/2, size/2, angle);
    drawSquare(-size/2, 0, size/2, angle);
    drawSquare(0, +size/2, size/2, angle);
  }
  popMatrix();
}
