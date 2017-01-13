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
    <p>#{dir}</p>
      eos
      doodlepage.write "    <p>" +
        (i < count-1 ? "<a href='../#{dirs[i+1]}/index.html'>previous</a> | " : "") +
        "<a href='../index.html'>list</a>" +
        (i > 0 ? " | <a href='../#{dirs[i-1]}/index.html'>next</a>" : "") +
        "</p>\n"
      doodlepage.write <<-eos
    <canvas data-processing-sources="#{dir}.pde"></canvas>
  </body>
</html>
      eos
    end
    homepage.write <<-eos
    <p><a href='#{dir}/index.html'>#{dir}</a></p>
    eos
  end
  homepage.write <<-eos
  </body>
</html>
  eos
end
