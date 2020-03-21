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

@WebServlet(name = "insertCourseServlet",urlPatterns = {"/insertCourse"})
public class insertCourseServlet extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        try{
            String Cno = request.getParameter("Cno");
            String Cname = request.getParameter("Cname");
            float CreditHours=Float.parseFloat(request.getParameter("CreditHours")) ;
            String CheckMode=request.getParameter("CheckMode");
            float Credit=Float.parseFloat(request.getParameter("Credit"));
            Connection connection=dataSource.getConnection();
            String sql="insert into gaoy_Courses02 values(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Cno);
            preparedStatement.setString(2, Cname);
            preparedStatement.setFloat(3, CreditHours);
            preparedStatement.setString(4, CheckMode);
            preparedStatement.setFloat(5, Credit);
            int rows=preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            String InsertCourseMessage="新增成功";
            session.setAttribute("InsertCourseMessage",InsertCourseMessage);
            response.sendRedirect("/manage/adminPage/CourseSearchUpdate.jsp");
        }
        catch (Exception e){
            System.out.println("Exception" + e);
            String InsertCourseMessage="新增错误";
            session.setAttribute("InsertCourseMessage",InsertCourseMessage);
            response.sendRedirect("/manage/adminPage/CourseSearchUpdate.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
