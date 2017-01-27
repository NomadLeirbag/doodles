void setup() {
  size(1000, 500);
  background(255);
  textSize(20);
  fill(0);

  String lines[] =loadStrings("http://dailydigitaldoodlesdoubtfullyduelingdepression" + "
                               .gabriel-damon.com/day_18_jan_26_17/day_18_jan_26_17.pde");
  text("There are " + lines.length + " lines:", 20, 40);
  for(int i = 0 ; i < lines.length; i++) {
    text(lines[i], 20, 40+20*(i+2));
  }
}

void draw() {
  // Untitled #18
  // Gabriel Damon
  // medium: screen, code
}
