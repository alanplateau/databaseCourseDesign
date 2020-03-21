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

@WebServlet(name = "insertTeacherServlet",urlPatterns = {"/insertTeacher"})
public class insertTeacherServlet extends HttpServlet {
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
            String Tno = request.getParameter("Tno").trim();
            String Tname = request.getParameter("Tname").trim();
            String Tsex = request.getParameter("Tsex").trim();
            int Tage = Integer.parseInt(request.getParameter("Tage"));
            String Ttitle = request.getParameter("Ttitle");
            String Ttelephone = request.getParameter("Ttelephone");
            Connection connection=dataSource.getConnection();
            String sql="insert into gaoy_Teacher02 values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Tno);
            preparedStatement.setString(2,Tname);
            preparedStatement.setString(3,Tsex);
            preparedStatement.setInt(4,Tage);
            preparedStatement.setString(5,Ttitle);
            preparedStatement.setString(6,Ttelephone);
            int rows=preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            String InsertTeacherMessage="新增成功";
            session.setAttribute("InsertTeacherMessage",InsertTeacherMessage);
            response.sendRedirect("/manage/adminPage/TeacherSearchUpdate.jsp");
        }
        catch (Exception E){
            System.out.println("Exception" + E);
            String InsertTeacherMessage="新增错误";
            session.setAttribute("InsertTeacherMessage",InsertTeacherMessage);
            response.sendRedirect("/manage/adminPage/TeacherSearchUpdate.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
