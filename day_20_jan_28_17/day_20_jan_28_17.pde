float sz = 200;
float mult = -2;
float t = 0;

void setup() {
  size(500, 500);
  noStroke();
}

void draw() {
  background(255);

  sz += mult*abs(noise(t));
  t += 0.01;
  if(sz < 0 || random(1) < 0.005) {
    sz = 200;
    mult = -random(3);
  }

  translate(width/2, height/2);
  scale(sz/250.0);

  fill(#D9D9D9);
  quad(-50, 500-300, -50, 300-300, 50, 100-300, 50, 500-300);
  fill(#BFBFBF);
  quad(-50, 350-300, -50, 300-300, 50, 100-300, 50, 150-300);
}
