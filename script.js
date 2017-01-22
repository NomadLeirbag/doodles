window.onload = function() {
  document.getElementsByClassName('random')[0].onclick = function() {
    var pages = new Array('../day_13_jan_21_17/index.html', '../day_12_jan_20_17/index.html', '../day_11_jan_19_17/index.html', '../day_10_jan_18_17/index.html', '../day_09_jan_17_17/index.html', '../day_08_jan_16_17/index.html', '../day_07_jan_15_17/index.html', '../day_06_jan_14_17/index.html', '../day_05_jan_13_17/index.html', '../day_04_jan_12_17/index.html', '../day_03_jan_11_17/index.html', '../day_02_jan_10_17/index.html', '../day_01_jan_09_17/index.html');
    window.location = pages[Math.floor(Math.random() * pages.length)];
  };
};
