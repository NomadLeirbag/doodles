ArrayList<ArrayList<Curve>> curves = new ArrayList<ArrayList<Curve>>();
final float yoffset = 400;

void setup() {
  size(1000, 500);
  for(int i = 0; i < 10; i++) {
    ArrayList<Curve> layer = new ArrayList<Curve>();
    layer.add(new Curve(lerpColor(#19A3A7, #011342, i/10.0), -200*i, new PVector(-width, yoffset), new PVector(random(-width-100, -width+100), random(yoffset-100, yoffset+100)),
                        new PVector(random(-width*2.0/3.0-100, -width*2.0/3.0+100), random(yoffset-100, yoffset+100)), new PVector(-width*2.0/3.0, yoffset)));
    for(int j = 0; j < 20; j++) {
      Curve prev = layer.get(layer.size()-1);
      PVector c2 = prev.c2.get();
      c2.sub(prev.a2);
      c2.mult(-1);
      c2.add(prev.a2);
      float x = prev.a2.x + width/3.0;
      layer.add(new Curve(prev.clr, prev.z, prev.a2.get(), c2, new PVector(random(x-100, x+100), random(yoffset-100, yoffset+100)), new PVector(x, yoffset)));
    }
    curves.add(layer);
  }
}

void draw() {
  // for(int y = -height*4; y <= height*2; y++) {
  //   stroke(lerpColor(#F2EEAC, #FFFFFF, map(y, -height*4, height*2, 0, 1)));
  //   line(-width*5, y, -3000, width*5, y, -3000);
  // }
  for(ArrayList<Curve> layer : curves) {
    for(Curve curve : layer) {
      curve.update();
      curve.draw();
    }
    if(layer.get(0).a2.x <= -2*width) {
      layer.remove(0);
      Curve prev = layer.get(layer.size()-1);
      PVector c2 = prev.c2.get();
      c2.sub(prev.a2);
      c2.mult(-1);
      c2.add(prev.a2);
      float x = prev.a2.x + width/3.0;
      layer.add(new Curve(prev.clr, prev.z, prev.a2.get(), c2, new PVector(random(x-100, x+100), random(yoffset-100, yoffset+100)), new PVector(x, yoffset)));
    }
  }
}

class Curve {
  Curve(color clr, float z, PVector a1, PVector c1, PVector c2, PVector a2) {
    this.clr = clr;
    this.z   = z;
    this.a1  = a1;
    this.c1  = c1;
    this.c2  = c2;
    this.a2  = a2;
  }

  void update() {
    a1.x -= 4;
    c1.x -= 4;
    c2.x -= 4;
    a2.x -= 4;
  }

  void draw() {
    // pushMatrix();
    // translate(0, 0, z);
    fill(clr);
    noStroke();
    beginShape();
    vertex(a1.x, height*2);
    vertex(a1.x, a1.y);
    bezierVertex(c1.x, c1.y, c2.x, c2.y, a2.x, a2.y);
    vertex(a2.x, height*2);
    endShape();
    // popMatrix();
  }

  color clr;
  float z;
  PVector a1, c1, c2, a2;
}
