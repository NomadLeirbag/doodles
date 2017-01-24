ArrayList<ArrayList<Curve>> curves = new ArrayList<ArrayList<Curve>>();

void setup() {
  size(1000, 500);
  for(int i = 0; i < 20; i++) {
    ArrayList<Curve> layer = new ArrayList<Curve>();
    float xoffset = 0;
    for(float x = 0; x < width*2; x += xoffset) {
      xoffset = random(width/2.0);
      layer.add(new Curve(lerpColor(#19A3A7, #011342, i/20.0), (i+1)/2.0, new PVector(x, height/3.0+15*i), new PVector(x+xoffset, height/3.0+15*i)));
    }
    curves.add(layer);
  }
}

void draw() {
  for(int y = 0; y <= 200; y++) {
    stroke(lerpColor(#F2EEAC, #FFFFFF, map(y, 0, 200, 0, 1)));
    line(0, y, width, y);
  }
  // background(255);
  for(int i = 0; i < curves.size(); i++) {
    ArrayList<Curve> layer = curves.get(i);
    for(Curve curve : layer) {
      curve.update();
      curve.draw();
    }
    if(layer.get(0).a2.x <= 0) {
      layer.remove(0);
      Curve prev = layer.get(layer.size()-1);
      PVector c1 = prev.c2.get();
      c1.sub(prev.a2);
      c1.mult(-1);
      c1.add(prev.a2);
      layer.add(new Curve(lerpColor(#19A3A7, #011342, i/20.0), (i+1)/2.0, new PVector(prev.a2.x, height/3.0+15*i), c1, new PVector(prev.a2.x+random(width/2.0), height/3.0+15*i)));
    }
  }
}

class Curve {
  Curve(color clr, float vel, PVector a1, PVector c1, PVector a2) {
    this.clr = clr;
    this.vel = vel;
    this.a1  = a1;
    this.a2  = a2;
    this.c1  = c1;
    this.c2  = new PVector(random(a2.x-100, a2.x+100), random(a2.y-100, a2.y+100));
  }
  Curve(color clr, float vel, PVector a1, PVector a2) {
    this.clr = clr;
    this.vel = vel;
    this.a1  = a1;
    this.a2  = a2;
    this.c1  = new PVector(random(a1.x-100, a1.x+100), random(a1.y-100, a1.y+100));
    this.c2  = new PVector(random(a2.x-100, a2.x+100), random(a2.y-100, a2.y+100));
  }

  void update() {
    a1.x -= vel;
    c1.x -= vel;
    c2.x -= vel;
    a2.x -= vel;
  }

  void draw() {
    fill(clr);
    noStroke();
    beginShape();
    vertex(a1.x, height*2);
    vertex(a1.x, a1.y);
    bezierVertex(c1.x, c1.y, c2.x, c2.y, a2.x, a2.y);
    vertex(a2.x, height*2);
    endShape();
  }

  color clr;
  float vel;
  PVector a1, c1, c2, a2;
}
