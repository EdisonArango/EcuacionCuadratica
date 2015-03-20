package model;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Validacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edisonarango
 */
public class Cuadratica {
    
    public static void graficar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
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
        
        String[] parametros = {"a","b","c"};
        for (int i = 0; i < parametros.length; i++) {
            String dato = request.getParameter(parametros[i]);
            request.setAttribute(parametros[i], dato);
        }
        //valores ="[1.0,1.0],[2.0,2.0],";
        request.setAttribute("valores", valores);
        request.setAttribute("signob", signob);
        request.setAttribute("signoc", signoc);
        request.getRequestDispatcher("/WEB-INF/grafica.jsp").forward(request, response);
    
    }
    
    public static void calcular(HttpServletRequest request, HttpServletResponse response)
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
            if (Validacion.isNumeric(request.getParameter("a"))||Validacion.isNumeric(request.getParameter("b"))||Validacion.isNumeric(request.getParameter("c"))){
                a = Double.valueOf(request.getParameter("a"));
                b = Double.valueOf(request.getParameter("b"));
                c = Double.valueOf(request.getParameter("c"));
            }
            else{
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
    
}
