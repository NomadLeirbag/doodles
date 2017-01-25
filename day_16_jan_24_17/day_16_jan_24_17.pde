float prevX = -1;
float prevY = -1;
PVector prevParallel = new PVector(-1, -1);
boolean cross = false;

void setup() {
  size(500, 500);
  background(255);
}

void draw() {
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
