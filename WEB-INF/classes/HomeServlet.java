// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/home")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class HomeServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query
      out.println("<html>");
      out.println("<head><title>Home</title></head>");
      out.println("<body>");
            out.println("<h1>Welcome to JSP app used to query our book store</h1>");

     out.println("<ol>"); 
         out.println("<li>");
            out.println("<ul>");
               out.println("<h2><i>USING doGet() METHOD</i></h2>");
               out.println("<li><a href='querybook.html'>Query And GET response for Single Author</a></li>");
               out.println("<li><a href='querybookmv.html'>Query And GET response for Multiple Author</a></li>");
               out.println("<li><a href='querybookmp.html'>Query And GET response for Multiple Selected Search Parameters.</a></li>");
               out.println("<li><a href='querybookpulldownmenu.html'>Query And GET response for Uding Drop Down Menu.</a></li>");
               out.println("<li><a href='querybooktextfield.html'>Query And GET response for Uding Text Field.</a></li>");
               out.println("<li><a href='querybooktextarea.html'>Query And GET response for Uding Text Area.</a></li>");
               out.println("<li><a href='eshopquery.html'>Query And GET response to Purchase a book form an author.</a></li>");

            out.println("</ul></li>");

            out.println("<li>");
            out.println("<ul>");               
            out.println("<h2><i>USING doPost() METHOD</i></h2>");
            out.println("<li><a href='querybookpost.html'>Query And GET response for Single Author using POST method</a></li>");
              
            

            out.println("</ul></li>");

      out.println("</ol>");      
 
      out.println("</body></html>");
      out.close();
   }

}