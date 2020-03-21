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

@WebServlet(name = "deleteCourseServlet",urlPatterns = {"/deleteCourse"})
public class deleteCourseServlet extends HttpServlet {
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
        String Cno=request.getParameter("Cno").trim();
        HttpSession session=request.getSession();
        try{
            Connection connection=dataSource.getConnection();
            String sql="delete from gaoy_Courses02 where gy_Cno02=?";//非级联删除，只能删除没有课表的课程
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Cno);
            int rows=preparedStatement.executeUpdate();
            System.out.println("rows:-------------------"+rows);
            preparedStatement.close();
            connection.close();
            String InsertCourseMessage="删除成功";
            session.setAttribute("InsertCourseMessage",InsertCourseMessage);
            response.sendRedirect("/manage/adminPage/CourseSearchUpdate.jsp");
        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            String InsertCourseMessage="删除错误";
            session.setAttribute("InsertCourseMessage",InsertCourseMessage);
            response.sendRedirect("/manage/adminPage/CourseSearchUpdate.jsp");
        }
    }
}
