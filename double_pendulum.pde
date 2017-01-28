float f1 = random(1, 10);
float p1 = 0;
float a1 = 100;
float d1 = 0.001;

float f2 = f1;
float p2 = 0;
float a2 = 100;
float d2 = 0.001;

float f4 = random(1, 10);
float p4 = 0;
float a4 = 100;
float d4 = 0.001;

float f3 = f4;
float p3 = 0;
float a3 = 100;
float d3 = 0.001;

float t = 0;

float x  = -1;
float y  = -1;
float px = -1;
float py = -1;

void setup() {
  size(500, 500);
  background(200);
}

void draw() {
  // exp(n)=e^n
  p1 = mouseX;
  p2 = mouseY;
  x = a1*sin(t*f1+p1)*exp(-d1*t) + a2*sin(t*f2+p2)*exp(-d2*t) + width/2.0;
  y = a3*sin(t*f3+p3)*exp(-d3*t) + a4*sin(t*f4+p4)*exp(-d4*t) + height/2.0;
  if(px >= 0 && py >= 0) {
    line(px, py, x, y);
  }
  px = x;
  py = y;
  t += 0.05;
}

void keyReleased() {
  background(200);
  px = -1;
  py = -1;
}