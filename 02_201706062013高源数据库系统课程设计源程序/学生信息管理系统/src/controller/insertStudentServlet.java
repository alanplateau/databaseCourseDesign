package controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "insertStudentServlet",urlPatterns = {"/insertStudent"})
public class insertStudentServlet extends HttpServlet {
    DataSource dataSource;
    public void init(){
        try{
            Context context=new InitialContext();
            dataSource=(DataSource)context.lookup("java:comp/env/jdbc/testDS");
        }
        catch (NamingException ne){
            System.out.println("Exception:"+ne);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        try {
            String Mno = request.getParameter("Mno").trim();
            int Cyear = Integer.parseInt(request.getParameter("Cyear").trim());
            String CLno = request.getParameter("CLno").trim();
            String Sno = request.getParameter("Sno").trim();
            String Sname = request.getParameter("Sname").trim();
            String Ssex = request.getParameter("Ssex").trim();
            int Sage = Integer.parseInt(request.getParameter("Sage").trim());
            String Shometown = request.getParameter("Shometown").trim();
            Connection connection=dataSource.getConnection();
            String sql="insert into gaoy_StudentInform02 values(?,?,?,?,?,0)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Sno);
            preparedStatement.setString(2, Sname);
            preparedStatement.setString(3, Ssex);
            preparedStatement.setInt(4,Sage);
            preparedStatement.setString(5, Shometown);
            int row1=preparedStatement.executeUpdate();
            String sql2="insert into gaoy_Class_Student02 values(?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql2);
            preparedStatement.setString(1, Mno);
            preparedStatement.setString(2, CLno);
            preparedStatement.setString(3, Sno);
            preparedStatement.setInt(4,Cyear);
            int rows2=preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            String InsertStudentMessage="录入成功";
            session.setAttribute("InsertStudentMessage",InsertStudentMessage);
            response.sendRedirect("/manage/adminPage/StudentSearchUpdate.jsp");
        }
        catch (Exception e){
            System.out.println("Exception"+e);
            String InsertStudentMessage="录入错误";
            session.setAttribute("InsertStudentMessage",InsertStudentMessage);
            response.sendRedirect("/manage/adminPage/StudentSearchUpdate.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
