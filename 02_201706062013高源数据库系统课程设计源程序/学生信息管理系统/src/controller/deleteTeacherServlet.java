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

@WebServlet(name = "deleteTeacherServlet",urlPatterns = {"/deleteTeacher"})
public class deleteTeacherServlet extends HttpServlet {
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
        String Tno=request.getParameter("Tno").trim();
        HttpSession session=request.getSession();
        try{
            Connection connection=dataSource.getConnection();
            String sql="delete from gaoy_Teacher02 where gy_Tno02=?";//非级联删除，只能删除没有课表的老师
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Tno);
            int rows=preparedStatement.executeUpdate();
            System.out.println("rows:-------------------"+rows);
            preparedStatement.close();
            connection.close();
            String InsertTeacherMessage="删除成功";
            session.setAttribute("InsertTeacherMessage",InsertTeacherMessage);
            response.sendRedirect("/manage/adminPage/TeacherSearchUpdate.jsp");
        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            String InsertTeacherMessage="删除错误";
            session.setAttribute("InsertTeacherMessage",InsertTeacherMessage);
            response.sendRedirect("/manage/adminPage/TeacherSearchUpdate.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
