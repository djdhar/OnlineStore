import java.io.*;
import java.util.*;
import java.text.*;
import User.User;
import Dress.Dress;

import javax.servlet.*;
import javax.servlet.http.*;


import java.sql.*;
import java.time.LocalDateTime;

class newDiscountFirst implements Comparator<Dress>
{
    public int compare(Dress d1, Dress d2)
    {
        return d2.getDiscount().compareTo(d1.getDiscount());
    }
}

class newArrivalFirst implements Comparator<Dress>
{
    public int compare(Dress d1, Dress d2)
    {
        return d2.getMoment().compareTo(d1.getMoment());
    }
}


public class SearchBox extends HttpServlet {

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;
    
        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
    
            titleCase.append(c);
        }
    
        return titleCase.toString();
    }

    public String getTime(java.util.Date date){
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = localDateFormat.format(date);
        return time;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String UserName = request.getParameter("Username");
        String Password = request.getParameter("password");
        String searchTerm = request.getParameter("Search");

       


        
        out.println("<h1>" + UserName + "</h1>");
        out.println("<h1>" + Password + "</h1>");

        try {
            Connection con=(Connection)getServletConfig().getServletContext().getAttribute("ONLINESTORE");

            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Customers where username = "+("\'"+UserName+"\'")+" and "+"password = "+("\'"+Password+"\'")+"";
            ResultSet rs = stmt.executeQuery(sql);



            if(!rs.isBeforeFirst()){
                response.sendRedirect("/OnlineStore/LoginPage");
            }
         
            while(rs.next()){
                //Retrieve by column name
      
                String username = rs.getString("username");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                String preference = rs.getString("preference");
                String password = rs.getString("password");

                Statement stmt2 = conn.createStatement();
                String sql2;
                sql2 = "SELECT * FROM Dress";
                ResultSet rs2 = stmt2.executeQuery(sql2);

                Vector<Dress> dresslist = new Vector<>();

                while(rs2.next()){
                    Integer id = rs2.getInt("id");
                    String dressname = toTitleCase(rs2.getString("name"));
                    java.util.Date moment = rs2.getTimestamp("moment");
                    String time = getTime(moment);
                    Integer price = rs2.getInt("price");
                    Integer discount = rs2.getInt("discount");

                    Dress currentdress = new Dress(id,gender,dressname, moment,time,price,discount);
                    if(currentdress.name.toLowerCase().contains(searchTerm.toLowerCase())){
                        dresslist.add(currentdress);
                    }

                    else{
                        continue;
                    }
                }


                if(preference.equals("cheap")){
                    Collections.sort(dresslist,new newDiscountFirst());
                }
                else{
                    Collections.sort(dresslist,new newArrivalFirst());
                }

                out.println("<h1>" + username + "</h1>");
                out.println("<h1>" + name + "</h1>");
                out.println("<h1>" + email + "</h1>");
                out.println("<h1>" + gender + "</h1>");
                out.println("<h1>" + preference + "</h1>");
                out.println("<h1>" + password + "</h1>");
                
                User user = new User(username, name, email,gender, preference,password);

                request.setAttribute("user",user);
                request.setAttribute("dresslist",dresslist);
                request.setAttribute("A",gender);

                ServletContext context= getServletContext();
                RequestDispatcher rd= context.getRequestDispatcher("/search.jsp");
                rd.forward(request, response);

                //request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

             }
            rs.close();
            stmt.close();
            conn.close();
         }
         catch(Exception e){
            out.println("<p>" + e+ "</p>");
            //response.sendRedirect("/OnlineStore/LoginPage");
         }

    }
}


