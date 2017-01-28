void setup() {
  size(750, 750);
  background(255);
  textSize(16);
  fill(0);
  String[] lines = {
  "void setup() {",
  "  size(750, 750);",
  "  background(255);",
  "  textSize(16);",
  "  fill(0);",
  "  String[] lines = {",
  "  };",
  "  ",
  "  for(int i = 0; i <= 5; i++) {",
  "    text(lines[i], 10, 20*(i+1));",
  "  }",
  "  for(int i = 0; i < lines.length; i++) {",
  "    text(lines[7] + str(char(34)) + lines[i] + str(char(34)) + str(char(44)), 10, 20*(i+7));",
  "  }",
  "  for(int i = 6; i < lines.length; i++) {",
  "    text(lines[i], 10, 20*(i+19));",
  "  }",
  "}",
  };

  for(int i = 0; i <= 5; i++) {
    text(lines[i], 10, 20*(i+1));
  }
  for(int i = 0; i < lines.length; i++) {
    text(lines[7] + str(char(34)) + lines[i] + str(char(34)) + str(char(44)), 10, 20*(i+7));
  }
  for(int i = 6; i < lines.length; i++) {
    text(lines[i], 10, 20*(i+19));
  }
}