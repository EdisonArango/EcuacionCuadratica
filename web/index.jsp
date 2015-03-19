<%-- 
    Document   : index
    Created on : 19-mar-2015, 3:06:22
    Author     : edisonarango
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script> 
        <title>Ecuaci&oacute;n Cuadr&aacute;tica</title>
    </head>
    <body>
    <center>
        <form method="get" action="EcuacionSegundoGrado">           
            <input class="solo-numero" type="text" name="a" placeholder="a">
            <label>x^2+</label>
            <input class="solo-numero" type="text" name="b" placeholder="b">
            <label>x+</label>
            <input class="solo-numero" type="text" name="c" placeholder="c">
            <label> = 0</label>
            <br>
            <input type="hidden" name="opcion" id="opcion">
            <input type="submit" onclick = "this.form.target='frameCalcular';document.getElementById('opcion').value='calcular';" value="Calcular Ra&iacute;ces">
            <input type="submit" onclick = "this.form.target='frameGraficar';document.getElementById('opcion').value='graficar';" value="Graficar ecuaci&oacute;n">
        </form>
        <iframe style="width: 920px;height: 55px" id="frameCalcular"></iframe>
        <iframe style="width: 920px;height: 600px" id="frameGraficar"></iframe>
    </center>
        
    <script>
        $(document).ready(function (){
          $('.solo-numero').keyup(function (){
            this.value = (this.value + '').replace(/[^0-9.]/g, '');
          });
        });
    </script>
    
    </body>
</html>
