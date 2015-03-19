/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
/**
 *
 * @author edisonarango
 */
@WebServlet(urlPatterns = {"/EcuacionSegundoGrado"})
public class EcuacionSegundoGrado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EcuacionSegundoGrado</title>");            
            out.println("</head>");
            out.println("<body>");
            
            double a,b,c;
            try{
            a = Double.valueOf(request.getParameter("a"));
            b = Double.valueOf(request.getParameter("b"));
            c = Double.valueOf(request.getParameter("c"));
            }
            catch(Exception e){
                out.print("<center>Los valores dados no son válidos</center>");
                return;
            }

            double baseRaiz = Math.pow(b, 2)-4*a*c;

            out.print("<center>");

            if (a==0) {
                double x= -c/b;
                out.print("X = "+x);
            }

            else if (baseRaiz<0) {
                double xReal = -b/(2*a);
                double x1i = Math.sqrt(-baseRaiz)/(2*a);
                String signo="";
                if (x1i>=0) {
                    signo = "+";
                }
                out.print("X1 ="+xReal+signo+x1i+"i");
                out.print("<br>");
                signo = "";
                double x2i = -(Math.sqrt(-baseRaiz)/(2*a));
                if (x2i>=0) {
                   signo = "+";
                }
                out.print("X2 ="+xReal+signo+x2i+"i");
            }
            else{
                double x1 = (-b+Math.sqrt(baseRaiz))/(2*a); 
                double x2 = (-b-Math.sqrt(baseRaiz))/(2*a);
                out.print("X1 = "+x1+" <br> X2 = "+x2);
            }

            out.print("</center>");
            out.println("</body>");
            out.println("</html>");
            
            //out.println("<h1>Servlet EcuacionSegundoGrado at " + request.getContextPath() + "</h1>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        switch (opcion) {
            case "calcular":
                processRequest(request, response);
                break;
            case "graficar":
                graficar(request,response);
                break;
        }
        
    }
    
    protected void graficar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        
        double a,b,c;
            try{
            a = Double.valueOf(request.getParameter("a"));
            b = Double.valueOf(request.getParameter("b"));
            c = Double.valueOf(request.getParameter("c"));
            }
            catch(Exception e){
                out.print("<html>"
                        + "<head>"
                        + "<meta charset='UTF-8'>"
                        + "</head>"
                        + "<body><center>Los valores dados no son v&aacute;lidos</center>"
                        + "</body></html>");
                return;
            }
        
        double min=-3;
        double max=3;
        try{
            min = Double.valueOf(request.getParameter("min"));
            max = Double.valueOf(request.getParameter("max"));
            }
            catch(Exception e){
            }
        
        double sumando=0.1;
        
        String valores="";
        for (double x = min; x <= max; x=x+sumando) {
            //x = Math.round(x);
            double y=a*Math.pow(x, 2)+b*x+c;
            valores+="["+x+","+y+"],";
        }
        
        String signob="+";
        if (b<0) {
            signob="";
        }
        String signoc="+";
        if (c<0) {
            signoc="";
        }
        
        String pagina = "<html>\n" +
"  <head>\n" +
"    <meta charset=\"UTF-8\">"
 + "<script type=\"text/javascript\"\n" +
"          src=\"https://www.google.com/jsapi?autoload={\n" +
"            'modules':[{\n" +
"              'name':'visualization',\n" +
"              'version':'1',\n" +
"              'packages':['corechart']\n" +
"            }]\n" +
"          }\"></script>\n" +
"\n" +
"    <script type=\"text/javascript\">\n" +
"      google.setOnLoadCallback(drawChart);\n" +
"\n" +
"      function drawChart() {\n" +
"        var data = google.visualization.arrayToDataTable([\n" +
"          ['X', 'f(x)'],\n" +
            valores+
"        ]);\n" +
"\n" +
"        var options = {\n" +
"          title: 'f(x) = "+a+"x^2 "+signob+""+b+"x "+signoc+""+c+"',\n" +
"          curveType: 'function',\n" +
"          legend: { position: 'bottom' }\n" +
"        };\n" +
"\n" +
"        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));\n" +
"\n" +
"        chart.draw(data, options);\n" +
"      }\n" +
"    </script>\n" +
"  </head>\n" +
"  <body>    "
+ "<form method=\"get\" action=\"EcuacionSegundoGrado\">           \n" +
"            <input type=\"hidden\" name=\"a\" value='"+a+"'>\n" +
"            <input type=\"hidden\" name=\"b\" value='"+b+"'>\n" +
"            <input type=\"hidden\" name=\"c\" value='"+c+"'>\n" +
"            <input type=\"hidden\" name=\"opcion\" value='graficar'>\n"+ 
             "<label>Min:</label><input type=\"text\" name=\"min\" >\n" +
             "<label>Max:</label><input type=\"text\" name=\"max\" >\n" +
"            <input type=\"submit\" value=\"Cambiar rango\">\n" +
"        </form>"+
"<center><div id=\"curve_chart\" style=\"width: 900px; height: 500px\"></div></center>\n" +
"  </body>\n" +
"</html>" ;
        out.print(pagina);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
