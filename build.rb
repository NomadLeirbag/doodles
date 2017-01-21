require "date"

def dir_parts(dir)
  parts = dir.split("_")
  month = Date::MONTHNAMES.select { |m| !m.nil? && m.downcase.include?(parts[2]) }[0]
  { num: parts[1], month: month, day: parts[3], year: "20#{parts[4]}" }
end

def pretty_text_dir(dir)
  parts = dir_parts(dir)
  "day #{parts[:num]}: #{parts[:month]} #{parts[:day]}, #{parts[:year]}"
end

def pretty_html_dir(dir)
  parts = dir_parts(dir)
  "<a href='#{dir}/index.html' class='list_item'><div class='num'>#{parts[:num]}</div><div class='date'>#{parts[:month]} #{parts[:day]}, #{parts[:year]}</div></a>"
end

dirs = Dir.glob("*/").sort_by { |dir| dir_parts(dir)[:num].to_i }.reverse.map { |dir| dir.chomp '/' }
count = dirs.size

File.open("index.html", "w") do |homepage|
  homepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>daily digital doodles doubtfully dueling depression</title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <header>
      <div class='icon'>d6</div><div class='title'>daily digital doodles doubtfully dueling depression</div>
    </header>
    <main>
  eos
  dirs.each_with_index do |dir, i|
    File.open("#{dir}/index.html", "w") do |doodlepage|
      doodlepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>daily digital doodles doubtfully dueling depression</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <script src='../processing.min.js'></script>
    <script src='../script.js'></script>
  </head>
  <body>
    <header>
      <div class='icon'>d6</div><div class='title'>#{pretty_text_dir(dir)}</div>
    </header>
    <main>
      eos
      doodlepage.write "      <nav>" +
        (i < count-1 ? "<a href='../#{dirs[i+1]}/index.html' class='prev nav_item'>previous</a>" : "<s class='prev nav_item disabled'>previous</s>") +
        "<a href='../index.html' class='list nav_item'>list</a><a href='#' class='random nav_item'>random</a>" +
        (i > 0 ? "<a href='../#{dirs[i-1]}/index.html' class='next nav_item'>next</a>" : "<s class='next nav_item disabled'>next</s>") +
        "</nav>\n"
      doodlepage.write <<-eos
      <canvas data-processing-sources="#{dir}.pde"></canvas>
    </main>
  </body>
</html>
      eos
    end
    homepage.write <<-eos
      #{pretty_html_dir(dir)}
    eos
  end
  homepage.write <<-eos
    </main>
  </body>
</html>
  eos
end
File.open("script.js", "w") do |scriptpage|
  scriptpage.write <<-eos
window.onload = function() {
  document.getElementsByClassName('random')[0].onclick = function() {
    var pages = new Array(#{dirs.map { |dir| "'../#{dir}/index.html'" }.join(", ")});
    window.location = pages[Math.floor(Math.random() * pages.length)];
  };
};
  eos
end
