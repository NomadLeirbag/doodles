File.open("index.html", "w") do |homepage|
  dirs = Dir.glob('*/')
  count = dirs.size
  homepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>daily doodles doubtfully dueling depression</title>
  </head>
  <body>
    <h1>days: #{count}</h1>
  eos
  dirs.each do |dir|
    dir.chomp! "/"
    File.open("#{dir}/index.html", "w") do |doodlepage|
      doodlepage.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>daily doodles doubtfully dueling depression</title>
    <script src="../processing.min.js"></script>
  </head>
  <body>
    <canvas data-processing-sources="#{dir}.pde"></canvas>
  </body>
</html>
      eos
    end
    homepage.write <<-eos
    <h2><a href="#{dir}/index.html">#{dir}</a></h2>
    eos
  end
  homepage.write <<-eos
  </body>
</html>
  eos
end
