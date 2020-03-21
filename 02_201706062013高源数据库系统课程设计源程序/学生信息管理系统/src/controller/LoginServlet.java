package controller;

import modal.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = {"/login.do"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID=request.getParameter("userID");
        String password=request.getParameter("password");
        String userType=request.getParameter("userType");
        getInformation judge=new getInformation();
        if(userType.equals("teacher")){
            if(judge.judgeTeacher(userID,password)){
                HttpSession session=request.getSession();
                session.setAttribute("userType",userType);
                session.setAttribute("userID",userID);
                response.sendRedirect("TeacherPage/index.jsp");
            }
            else{
                response.sendRedirect("login.jsp");
            }
        }
        else if(userType.equals("student")) {
            if (judge.judgeStudent(userID, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userType", userType);
                session.setAttribute("userID", userID);
                response.sendRedirect("StudentPage/index.jsp");
            }
            else{
                response.sendRedirect("login.jsp");
            }
        }
        else if(userType.equals("manager")){
                if(userID.equals("admin")&&password.equals("admin")){
                    response.sendRedirect("adminPage/index.jsp");
                }
                else{
                    response.sendRedirect("login.jsp");
                }
            }
        else {
                response.sendRedirect("login.jsp");
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
