int count = 100;
boolean[][] world = new boolean[count][count];
boolean[][] nextw = new boolean[count][count];

void setup() {
  size(500, 500);
  reset();
  textAlign(CENTER, CENTER);
  noStroke();
}

void draw() {
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

void mouseReleased() {
  reset();
}

void reset() {
  for(int x = 0; x < count; x++) {
    for(int y = 0; y < count; y++) {
      world[x][y] = (random(1) < noise(x, y));
    }
  }
}

int live_neighbors(int x, int y) {
  int num = 0;
  if(x > 0) {
    num += int(world[x-1][y]);
  }
  if(x < count-1) {
    num += int(world[x+1][y]);
  }
  if(y > 0) {
    num += int(world[x][y-1]);
  }
  if(y < count-1) {
    num += int(world[x][y+1]);
  }
  if(x > 0 && y > 0) {
    num += int(world[x-1][y-1]);
  }
  if(x > 0 && y < count-1) {
    num += int(world[x-1][y+1]);
  }
  if(x < count-1 && y > 0) {
    num += int(world[x+1][y-1]);
  }
  if(x < count-1 && y < count-1) {
    num += int(world[x+1][y+1]);
  }
  return num;
}
