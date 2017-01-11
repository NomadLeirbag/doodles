File.open("index.html", "w") do |f|
  dirs = Dir.glob('*/').reverse
  count = dirs.size
  f.write <<-eos
<!DOCTYPE html>
<html>
  <head>
    <title>daily doodles doubtfully dueling depression</title>
    <script src="processing.min.js"></script>
  </head>
  <body>
    <h1>days: #{count}</h1>
  eos
  dirs.each do |dir|
    dir.chomp! "/"
    f.write <<-eos
    <h2>#{dir}</h2>
    <canvas data-processing-sources="#{dir}/#{dir}.pde"></canvas>
    eos
  end
  <<-eos
  </body>
</html>
  eos
end
