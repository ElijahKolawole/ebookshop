// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/eshopquery")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class EshopQueryServlet extends HttpServlet {

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
      out.println("<head><title>Query Response</title></head>");
      out.println("<body>");

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "datame1");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Execute a SQL SELECT query
          String[] authors = request.getParameterValues("author");  // Returns an array of Strings
          if (authors == null) {
            out.println("<h2>No author selected. Please go back to select author(s)</h2>");
            return; // Exit doGet()
         }  //else  Okay to perform the database query using the above codes
         String sqlStr = "SELECT * FROM books WHERE author IN (";
         for (int i = 0; i < authors.length; ++i) {
            if (i < authors.length - 1) {
               sqlStr += "'" + authors[i] + "', ";  // need a commas
            } else {
               sqlStr += "'" + authors[i] + "'";    // no commas
            }
         }
         sqlStr += ") AND qty > 0 ORDER BY author ASC, title ASC";

         out.println("<h3>Thank you for your query.</h3>");
         out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

                // Step 4: Process the query result
         // Print the <form> start tag
         out.println("<form method='get' action='eshoporder'>");
         
         // For each row in ResultSet, print one checkbox inside the <form>
               
               out.println("<table border=1 cellpadding='10'>");
                 out.println("<caption>Books in our invenotory</caption>");
                 out.println("<thead>");
                  out.println("<tr> <td></td><td><h2>ID</h2></td><td><h2>AUTHOR</h2></td><td><h2>TITLE</h2></td><td><h2>PRICE</h2></td></tr>");
                 out.println("</thead>");
                 
                     
                 
               

         while(rset.next()) {
             out.println("<tbody>");
            // out.println("<p><input type='checkbox' name='id' value="
            //       + "'" + rset.getString("id") + "' />"
            //        + rset.getString("id") + ", "
            //       + rset.getString("author") + ", "
            //       + rset.getString("title") + ", $"
            //       + rset.getString("price") + "</p>");
             out.println("<tr> " + "<td><input type='checkbox' name='id' value='"+rset.getString("id")+"'></td>" + "<td>"+ rset.getString("id") +"</td>" + 
               "<td>"+ rset.getString("author") + "<td>"+ rset.getString("title") + "<td>"+ rset.getString("price") 
               +"</td>"+"</td>" +"</td>"+"< /tr>"  );
            
         }
          out.println("</tbody>");
         out.println("</table>");
      // Enter Buyer's Information to be stored in the database with the order informtation
         out.println("<p>Enter your Name: <input type='text' name='cust_name' /></p>");
         out.println("<p>Enter your Email: <input type='text' name='cust_email' /></p>");
         out.println("<p>Enter your Phone Number: <input type='text' name='cust_phone' /></p>");
         
 
         // Print the submit button and </form> end-tag
         out.println("<p><input type='submit' value='ORDER' />");
         out.println("</form>");


      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
   }
}