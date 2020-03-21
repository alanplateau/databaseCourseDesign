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

@WebServlet(name = "ChangeTeacherPassServlet",urlPatterns = {"/changeTeacherPass"})
public class ChangeTeacherPassServlet extends HttpServlet {
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
        try{
            String userID = (String)session.getAttribute("userID");
            String firstPass = request.getParameter("password").trim();
            String secondPass = request.getParameter("SurePassword").trim();
            if (firstPass.equals(secondPass)) {
                Connection connection=dataSource.getConnection();
                String sql="update gaoy_LoginTeacher02 set gy_Tpassword02=? where gy_Tno02=?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, firstPass);
                preparedStatement.setString(2, userID);
                int rows=preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                String ChangePassMessage="修改成功";
                session.setAttribute("ChangePassMessage",ChangePassMessage);
                response.sendRedirect("/manage/TeacherPage/ChangePassword.jsp");
            }
            else {
                String ChangePassMessage="两次密码不一致";
                session.setAttribute("ChangePassMessage",ChangePassMessage);
                response.sendRedirect("/manage/TeacherPage/ChangePassword.jsp");
            }
        }
        catch (Exception e){
            System.out.println("Exception"+e);
            String ChangePassMessage="修改错误";
            session.setAttribute("ChangePassMessage",ChangePassMessage);
            response.sendRedirect("/manage/TeacherPage/ChangePassword.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
