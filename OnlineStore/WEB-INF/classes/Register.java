import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;




public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String Name = request.getParameter("Name");
        String UserName = request.getParameter("UserName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String preference = request.getParameter("preference");
        String password = request.getParameter("password");

   
        
        out.println("<h1>" + UserName + "</h1>");
        out.println("<h1>" + Name + "</h1>");
        out.println("<h1>" + email + "</h1>");
        out.println("<h1>" + gender + "</h1>");
        out.println("<h1>" + preference + "</h1>");
        out.println("<h1>" + password + "</h1>");

        try {
            Connection con=(Connection)getServletConfig().getServletContext().getAttribute("ONLINESTORE");
   
            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO Customers VALUES("+("\'"+UserName+"\'")+","+("\'"+Name+"\'")+","+("\'"+email+"\'")+","+("\'"+gender+"\'")+","+("\'"+preference+"\'")+","+("\'"+password+"\'"+")");
            Integer rs = stmt.executeUpdate(sql);
            out.println("<h1>" + password + "</h1>");
            stmt.close();
            conn.close();
            response.sendRedirect("/OnlineStore/LoginPage");
         }
         catch(Exception e){
            out.println("<h1>" + e+ "</h1>");
            response.sendRedirect("/OnlineStore/RegisterPage");
         }

    }
}


