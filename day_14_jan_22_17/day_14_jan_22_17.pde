PGraphics text;
PGraphics[] frames = new PGraphics[5];
int frame = 0;

void setup() {
  size(200, 100);
  text = createGraphics(200, 100);
  text.beginDraw();
  text.background(255);
  text.textSize(64);
  text.textAlign(CENTER, CENTER);
  text.fill(0);
  text.text("Time", width/2, height/2);
  text.endDraw();
  blurframes(text, 0);
  frameRate(5);
}

void blurframes(PGraphics orig, int f) {
  for(int r = 0; r < 5; r++) {
    PGraphics blur = createGraphics(200, 100);
    blur.beginDraw();
    blur.loadPixels();
    int[][] neighborhood = new int[(2*r+1)*(2*r+1)][2];
    int i = 0;
    for(int a = -r; a <= r; a++) {
      for(int b = -r; b <= r; b++) {
        neighborhood[i][0] = a;
        neighborhood[i][1] = b;
        i++;
      }
    }
    for(int x = 0; x < orig.width; x++) {
      for(int y = 0; y < orig.height; y++) {
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int[] offset : neighborhood) {
          int newX = x + offset[0];
          int newY = y + offset[1];
          if(newX >= 0 && newX < orig.width && newY >= 0 && newY < orig.height) {
            colors.add(orig.get(newX, newY));
          }
        }
        float[] rgb = {0.0, 0.0, 0.0};
        for(color c : colors) {
          rgb[0] += red(c);
          rgb[1] += green(c);
          rgb[2] += blue(c);
        }
        rgb[0] /= colors.size();
        rgb[1] /= colors.size();
        rgb[2] /= colors.size();
        blur.pixels[200*y+x] = color(rgb[0], rgb[1], rgb[2]);
      }
    }
    blur.updatePixels();
    blur.endDraw();
    frames[f+r] = blur;
  }
}

void draw() {
  image(frames[frame], 0, 0);
  frame += 1;
  if(frame == 5) {
    frame = 0;
  }
}
