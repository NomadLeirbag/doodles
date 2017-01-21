PGraphics pg_static, pg_text;
float t = 0;

void setup() {
  size(500, 500);
  pg_static = createGraphics(500, 150);
  pg_text = createGraphics(500, 150);
}

void draw() {
  pg_text.beginDraw();
  pg_text.background(255);
  pg_text.textSize(128);
  pg_text.textAlign(CENTER, BOTTOM);
  pg_text.fill(0);
  pg_text.text("I'm fine.", pg_text.width/2, pg_text.height);
  pg_text.endDraw();

  pg_static.beginDraw();
  float xoff = 0.0;
  for(int x = 0; x < pg_static.width; x++) {
    float yoff = 0.0;
    for(int y = 0; y < pg_static.height; y++) {
      // stroke(map(noise(xoff, yoff, t), 0, 1, 0, 255), map(noise(xoff+1000, yoff+1000, t), 0, 1, 0, 255), map(noise(xoff+2000, yoff+2000, t), 0, 1, 0, 255));
      pg_static.stroke(map(noise(xoff, yoff, t), 0, 1, 0, 255));
      pg_static.point(x, y);
      yoff += 0.05;
    }
    xoff += 0.05;
  }
  t += 0.1;
  pg_text.loadPixels();
  pg_static.loadPixels();
  for(int i = 0; i < pg_text.width*pg_text.height; i++) {
    if(pg_text.pixels[i] == color(255)) {
      pg_static.pixels[i] = color(128);
    }
  }
  pg_static.updatePixels();
  pg_static.endDraw();

  background(128);
  image(pg_static, 0, 500-150);
}
