float t = 0;

void setup() {
  size(100, 100);
  frameRate(20);
}

void draw() {
  if(randomGaussian() > t) {
    background(#FFFF00);
  }
  else {
    background(#000000);
  }
  t += 0.001;
}
