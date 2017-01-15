require "date"

def pretty_dir(dir)
  parts = dir.split("_")
  day = parts[0].split(/(\d)/)[1]
  month = Date::MONTHNAMES.select { |m| !m.nil? && m.downcase.include?(parts[1]) }[0]
  "day #{day}: #{month} #{parts[2]}, 20#{parts[3]}"
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
  </head>
  <body>
    <p>days: #{count}</p>
  eos
  dirs.each_with_index do |dir, i|
    File.open("#{dir}/index.html", "w") do |doodlepage|
      doodlepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF-8'>
    <title>daily doodles doubtfully dueling depression</title>
    <script src='../processing.min.js'></script>
  </head>
  <body>
    <p>#{pretty_dir(dir)}</p>
      eos
      doodlepage.write "    <p>" +
        (i < count-1 ? "<a href='../#{dirs[i+1]}/index.html'>previous</a>" : "<s>previous</s>") +
        " | <a href='../index.html'>list</a> | " +
        (i > 0 ? "<a href='../#{dirs[i-1]}/index.html'>next</a>" : "<s>next</s>") +
        "</p>\n"
      doodlepage.write <<-eos
    <canvas data-processing-sources="#{dir}.pde"></canvas>
  </body>
</html>
      eos
    end
    homepage.write <<-eos
    <p><a href='#{dir}/index.html'>#{pretty_dir(dir)}</a></p>
    eos
  end
  homepage.write <<-eos
  </body>
</html>
  eos
end
