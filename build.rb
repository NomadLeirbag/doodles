require "date"

def dir_parts(dir)
  parts = dir.split("_")
  num = parts[0].split(/(\d)/)[1]
  month = Date::MONTHNAMES.select { |m| !m.nil? && m.downcase.include?(parts[1]) }[0]
  { num: num, month: month, day: parts[2], year: "20#{parts[3]}" }
end

def pretty_text_dir(dir)
  parts = dir_parts(dir)
  "day #{parts[:num]}: #{parts[:month]} #{parts[:day]}, #{parts[:year]}"
end

def pretty_html_dir(dir)
  parts = dir_parts(dir)
  "<a href='#{dir}/index.html' class='list_item'><div class='num'>#{parts[:num]}</div><div class='date'>#{parts[:month]} #{parts[:day]}, #{parts[:year]}</div></a>"
end

File.open("index.html", "w") do |homepage|
  dirs = Dir.glob("*/").sort.reverse.map { |dir| dir.chomp '/' }
  count = dirs.size
  homepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>daily doodles doubtfully dueling depression</title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <p>#{count} daily doodles doubtfully dueling depression</p>
  eos
  dirs.each_with_index do |dir, i|
    File.open("#{dir}/index.html", "w") do |doodlepage|
      doodlepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>daily doodles doubtfully dueling depression</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <script src='../processing.min.js'></script>
  </head>
  <body>
    <p>#{pretty_text_dir(dir)}</p>
      eos
      doodlepage.write "    <div class='nav'>" +
        (i < count-1 ? "<a href='../#{dirs[i+1]}/index.html' class='prev'>previous</a>" : "<s class='prev disabled'>previous</s>") +
        "<a href='../index.html' class='list'>list</a>" +
        (i > 0 ? "<a href='../#{dirs[i-1]}/index.html' class='next'>next</a>" : "<s class='next disabled'>next</s>") +
        "</div>\n"
      doodlepage.write <<-eos
    <canvas data-processing-sources="#{dir}.pde"></canvas>
  </body>
</html>
      eos
    end
    homepage.write <<-eos
    #{pretty_html_dir(dir)}
    eos
  end
  homepage.write <<-eos
  </body>
</html>
  eos
end
