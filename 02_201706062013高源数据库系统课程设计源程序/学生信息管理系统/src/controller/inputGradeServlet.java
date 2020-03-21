package controller;

import modal.ClassCourses;

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
import java.util.ArrayList;

@WebServlet(name = "inputGradeServlet",urlPatterns = {"/inputGrade"})
public class inputGradeServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastGrade=request.getParameter("lastGrade");
        String Sno=request.getParameter("Sno");
        if(lastGrade==null||lastGrade==""){
            String resultMessage="请先输入成绩";
            request.getSession().setAttribute("resultMessage",resultMessage);
            response.sendRedirect("/manage/TeacherPage/viewGrade.jsp");
        }
        else{
            HttpSession session=request.getSession();
            ArrayList<ClassCourses> teachCourses=(ArrayList<ClassCourses>)session.getAttribute("teachCourses");
            int CurrentIndex=(int)session.getAttribute("CurrentIndex");
            try{
                float lastGrade1=Float.parseFloat(lastGrade);
                Connection connection=dataSource.getConnection();
                int year=teachCourses.get(CurrentIndex).getYear();
                int term=teachCourses.get(CurrentIndex).getTerm();
                String Cno=teachCourses.get(CurrentIndex).getcNo();
                String Tno=teachCourses.get(CurrentIndex).gettNo();
                String sql="delete from gaoy_Grades02 where gy_Sno02=? and gy_Year02=? and" +
                        " gy_Term02=? and gy_Cno02=? and gy_Tno02=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, Sno);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, term);
                preparedStatement.setString(4, Cno);
                preparedStatement.setString(5, Tno);
                int rows=preparedStatement.executeUpdate();
                String sql1="insert into gaoy_Grades02 values(?,?,?,?,?,?)";
                preparedStatement=connection.prepareStatement(sql1);
                preparedStatement.setString(1, Sno);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, term);
                preparedStatement.setString(4, Cno);
                preparedStatement.setString(5, Tno);
                preparedStatement.setFloat(6,lastGrade1);
                int rows1=preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                String resultMessage="录入成功";
                session.setAttribute("resultMessage",resultMessage);
                response.sendRedirect("/manage/TeacherPage/viewGrade.jsp");
            }
            catch (NumberFormatException e){
                System.out.println("Exception"+e);
                String resultMessage="请输入数字";
                session.setAttribute("resultMessage",resultMessage);
                response.sendRedirect("/manage/TeacherPage/viewGrade.jsp");
            }
            catch (SQLException e){
                System.out.println("Exception"+e);
                String resultMessage="录入错误";
                session.setAttribute("resultMessage",resultMessage);
                response.sendRedirect("/manage/TeacherPage/viewGrade.jsp");
            }

        }
    }
}
