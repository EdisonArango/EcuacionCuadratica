<%-- 
    Document   : grafica
    Created on : 20-mar-2015, 12:38:57
    Author     : edisonarango
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript"
          src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>

    <script type="text/javascript">
      google.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['X', 'f(x)'],
          ${valores}
        ]);

        var options = {
          title: '${a}x^2 ${signob}${b}x ${signoc}${c}',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
      <form method="get" action="EcuacionSegundoGrado"> 
        <input type="hidden" name="a" value="${a}">
            <input type="hidden" name="b" value="${b}">
            <input type="hidden" name="c" value="${c}">
            <input type="hidden" name="opcion" value="graficar">
             <label>Min:</label><input type="text" name="min" >
             <label>Max:</label><input type="text" name="max" >
            <input type="submit" value="Cambiar rango">
        </form>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>
