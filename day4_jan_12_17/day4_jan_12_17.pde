int a = 5;
int b = 4;
float d = 0;

void setup() {
  size(250, 250);
  stroke(#00FF00);
}

void draw() {
  background(#1B635E);
  a = int(map(mouseX, 0, width, 1, 25));
  b = int(map(mouseY, 0, height, 1, 25));
  for(float t = 0; t < 2*PI; t += 0.001) {
    float x = 100*sin(a*t+d);
    float y = 100*sin(b*t);
    ellipse(x+width/2.0, y+height/2.0, 1, 1);
  }
  d += 0.1;
}
