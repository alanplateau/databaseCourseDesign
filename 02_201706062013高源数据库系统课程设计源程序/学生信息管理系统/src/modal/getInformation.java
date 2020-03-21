package modal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class getInformation {
    DataSource dataSource;

    public getInformation(){
       try{
           Context context=new InitialContext();
           dataSource=(DataSource)context.lookup("java:comp/env/jdbc/testDS");
       }
       catch (NamingException ne){
           System.out.println("Exception:"+ne);
       }
    }

    //查询学生个人信息
    public Student getStudent(String Sno){
        try{
            Connection connection=dataSource.getConnection();
            String sql="select *from gaoy_StudentInform02 where gy_Sno02=?";
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,Sno);
            Student tempStu=new Student();
            ResultSet resultSet=pstmt.executeQuery();
            if(resultSet.next()){
                tempStu.setsNo(resultSet.getString(1).trim());
                tempStu.setsName(resultSet.getString(2).trim());
                tempStu.setsSex(resultSet.getString(3).trim());
                tempStu.setsAge(resultSet.getInt(4));
                tempStu.setsHometown(resultSet.getString(5).trim());
                tempStu.setsCredit(resultSet.getFloat(6));
                resultSet.close();
                pstmt.close();
                connection.close();
                return tempStu;//返回结果
            }
            else {
                return null;//没有查询到返回空值
            }

        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            return null;
        }
    }

    //查询老师个人信息
    public Teacher getTeacher(String Tno){
        try{
            Connection connection=dataSource.getConnection();
            String sql="select *from gaoy_Teacher02 where gy_Tno02=?";
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,Tno);
            Teacher teacher=new Teacher();
            ResultSet resultSet=pstmt.executeQuery();
            if(resultSet.next()){
                teacher.settNo(resultSet.getString(1).trim());
                teacher.settName(resultSet.getString(2).trim());
                teacher.settSex(resultSet.getString(3).trim());
                teacher.settAge(resultSet.getInt(4));
                teacher.settTitle(resultSet.getString(5).trim());
                teacher.settTelephone(resultSet.getString(6).trim());
                resultSet.close();
                pstmt.close();
                connection.close();
                return teacher;//返回结果
            }
            else {
                return null;//没有查询到返回空值
            }

        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            return null;
        }
    }

    //------------------------登录-------------------------------------------------------
    public boolean judgeStudent(String userID,String password){
        try{
            Connection connection=dataSource.getConnection();
            String sql="select * from gaoy_LoginStudent02 where gy_Sno02=? and gy_Spassword02=?";
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, password);
            ResultSet resultSet=pstmt.executeQuery();
            if(resultSet.next()){
                resultSet.close();
                pstmt.close();
                connection.close();
                return true;
            }
            resultSet.close();
            pstmt.close();
            connection.close();
            return  false;
        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            return false;
        }
    }

    public boolean judgeTeacher(String userID,String password){
        try{
            Connection connection=dataSource.getConnection();
            String sql="select * from gaoy_LoginTeacher02 where gy_Tno02=? and gy_Tpassword02=?";
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, password);
            ResultSet resultSet=pstmt.executeQuery();
            if(resultSet.next()){
                resultSet.close();
                pstmt.close();
                connection.close();
                return true;
            }
            resultSet.close();
            pstmt.close();
            connection.close();
            return  false;
        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            return false;
        }
    }
    //-----------------------登录-------------------------------------------------------------

    //查询成绩
    public ArrayList<gradeReport> getReport(String Sno,int year,int term){
        ArrayList<gradeReport> reports=new ArrayList<gradeReport>();
        try{
            Connection connection=dataSource.getConnection();
            String sql="select * from gradeSearch_view where gy_Sno02=? and gy_Year02=? and gy_Term02=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Sno);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3,term);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                gradeReport temp=new gradeReport();
                temp.setsNo(resultSet.getString(1).trim());
                temp.setYear(resultSet.getInt(2));
                temp.setTerm(resultSet.getInt(3));
                temp.setcName(resultSet.getString(4).trim());
                temp.settName(resultSet.getString(5).trim());
                temp.setGrade(resultSet.getFloat(6));
                temp.setCredit(resultSet.getFloat(7));
                temp.setAvgGrade(resultSet.getFloat(8));
                reports.add(temp);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return reports;
        }
        catch (SQLException e){
            System.out.println("Exception"+e);
            return null;
        }
    }

    //查询学生课表
    public ArrayList<ClassCourses> getCourses(String Sno){
        try{
            ArrayList<ClassCourses> courses=new ArrayList<ClassCourses>();
            Connection connection=dataSource.getConnection();
            String sql="select ClassCourse_view.gy_Mno02,ClassCourse_view.gy_Cyear02,ClassCourse_view.gy_CLno02,gy_Cname02,\n" +
                    "\tgy_Tname02,gy_Year02,gy_Term02,gy_Ccredit02 \n" +
                    "\tfrom ClassCourse_view,gaoy_Class_Student02\n" +
                    "\twhere gy_Sno02=?\n" +
                    "\tand   ClassCourse_view.gy_Mno02=gaoy_Class_Student02.gy_Mno02\n" +
                    "\tand   ClassCourse_view.gy_Cyear02=gaoy_Class_Student02.gy_Cyear02  \n" +
                    "\tand   ClassCourse_view.gy_CLno02=gaoy_Class_Student02.gy_CLno02 ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Sno);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                ClassCourses temp=new ClassCourses();
                temp.setmNO(resultSet.getString(1));
                temp.setcYear(resultSet.getInt(2));
                temp.setClNo(resultSet.getString(3));
                temp.setcName(resultSet.getString(4));
                temp.settName(resultSet.getString(5));
                temp.setYear(resultSet.getInt(6));
                temp.setTerm(resultSet.getInt(7));
                temp.setCredit(resultSet.getFloat(8));
                courses.add(temp);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return courses;
        }
        catch (SQLException e){
            System.out.println("Exceptiom"+e);
            return null;
        }
    }

    //查询班级课表
    public ArrayList<ClassCourses> getClassCourses(String Mno,int Cyear,String CLno){
        try{
            ArrayList<ClassCourses> courses=new ArrayList<ClassCourses>();
            Connection connection=dataSource.getConnection();
            String sql="select *from ClassCourse_view\n" +
                    "\t where gy_Mno02=?\n" +
                    "\t and gy_Cyear02=?\n" +
                    "\t and gy_CLno02=? ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Mno);
            preparedStatement.setInt(2, Cyear);
            preparedStatement.setString(3, CLno);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                ClassCourses temp=new ClassCourses();
                temp.setmNO(resultSet.getString(1));
                temp.setcYear(resultSet.getInt(2));
                temp.setClNo(resultSet.getString(3));
                temp.setcName(resultSet.getString(4));
                temp.settName(resultSet.getString(5));
                temp.setYear(resultSet.getInt(6));
                temp.setTerm(resultSet.getInt(7));
                temp.setCredit(resultSet.getFloat(8));
                courses.add(temp);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return courses;

        }
        catch (SQLException e){
            System.out.println("Exceptiom"+e);
            return null;
        }
    }

    //查询教师任课表
    public ArrayList<ClassCourses> getTeacherCourses(String Tno){
        try{
            ArrayList<ClassCourses> courses=new ArrayList<ClassCourses>();
            Connection connection=dataSource.getConnection();
            String sql="select * from ClassCourse_view where gy_Tno02=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Tno);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                ClassCourses temp=new ClassCourses();
                temp.setmNO(resultSet.getString(1));
                temp.setcYear(resultSet.getInt(2));
                temp.setClNo(resultSet.getString(3));
                temp.setcName(resultSet.getString(4));
                temp.settName(resultSet.getString(5));
                temp.setYear(resultSet.getInt(6));
                temp.setTerm(resultSet.getInt(7));
                temp.setCredit(resultSet.getFloat(8));
                temp.setcNo(resultSet.getString(9));
                temp.setmName(resultSet.getString(10));
                temp.settNo(resultSet.getString(11));
                courses.add(temp);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return courses;

        }
        catch (SQLException e){
            System.out.println("Exceptiom"+e);
            return null;
        }
    }

    //返回一个结果集，里面包含了所有选修该课程的该班级的学生，如果有成绩的返回的结果集中也包含成绩
    public ArrayList<SimpleStudent> getTeachedStudent(String Mno,int Cyear,String CLno, int year,int term,String Cno,String Tno){
        try{
            ArrayList<SimpleStudent> simpleStudents=new ArrayList<SimpleStudent>();
            Connection connection=dataSource.getConnection();
            //选出这个班的全部学生
            String sql="select gy_Sno02 from gaoy_Class_Student02 where gy_Mno02=? and gy_Cyear02=? and gy_CLno02=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Mno);
            preparedStatement.setInt(2,Cyear);
            preparedStatement.setString(3,CLno);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                SimpleStudent temp=new SimpleStudent();
                temp.setsNo(resultSet.getString(1));
                simpleStudents.add(temp);
            }
            for(int i=0;i<simpleStudents.size();i++){
                String sql1="select gy_Grade02 from gaoy_Grades02 where gy_Sno02=? and gy_Year02=? and " +
                        "gy_Term02=? and gy_Cno02=? and gy_Tno02=?";
                preparedStatement=connection.prepareStatement(sql1);
                preparedStatement.setString(1,simpleStudents.get(i).getsNo());
                preparedStatement.setInt(2,year);
                preparedStatement.setInt(3,term);
                preparedStatement.setString(4, Cno);
                preparedStatement.setString(5,Tno);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    simpleStudents.get(i).setsGrade(resultSet.getFloat(1));
                }
                String sql2="select gy_Sname02 from gaoy_StudentInform02 where gy_Sno02=?";
                preparedStatement=connection.prepareStatement(sql2);
                preparedStatement.setString(1,simpleStudents.get(i).getsNo());
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    simpleStudents.get(i).setsName(resultSet.getString(1));
                }
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return simpleStudents;
        }
        catch (SQLException e){
            System.out.println("Exceptiom"+e);
            return null;
        }
    }

    //查询所有一个班级里的学生
    public ArrayList<Student> getClassStudents(String Mno,int Cyear,String CLno){
        try{
            ArrayList<Student> students=new ArrayList<Student>();
            Connection connection=dataSource.getConnection();
            String sql="select gaoy_StudentInform02.gy_Sno02,gy_Sname02,gy_Ssex02,gy_Sage02,gy_Shometown02,gy_Scredit02\n" +
                    "\tfrom gaoy_StudentInform02,gaoy_Class_Student02\n" +
                    "\twhere gy_Mno02=? and gy_Cyear02=? and gy_CLno02=?\n" +
                    "\tand gaoy_StudentInform02.gy_Sno02=gaoy_Class_Student02.gy_Sno02";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, Mno);
            preparedStatement.setInt(2, Cyear);
            preparedStatement.setString(3, CLno);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Student tempStu=new Student();
                tempStu.setsNo(resultSet.getString(1).trim());
                tempStu.setsName(resultSet.getString(2).trim());
                tempStu.setsSex(resultSet.getString(3).trim());
                tempStu.setsAge(resultSet.getInt(4));
                tempStu.setsHometown(resultSet.getString(5).trim());
                tempStu.setsCredit(resultSet.getFloat(6));
                students.add(tempStu);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return students;
        }
        catch (SQLException e){
            System.out.println("Exceptiom"+e);
            return null;
        }

    }

    //查询所有老师
    public ArrayList<Teacher> getAllTeachers(){
        try {
            ArrayList<Teacher> teachers=new ArrayList<Teacher>();
            Connection connection=dataSource.getConnection();
            String sql="select *from gaoy_Teacher02";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Teacher temp=new Teacher();
                temp.settNo(resultSet.getString(1).trim());
                temp.settName(resultSet.getString(2).trim());
                temp.settSex(resultSet.getString(3).trim());
                temp.settAge(resultSet.getInt(4));
                temp.settTitle(resultSet.getString(5).trim());
                temp.settTelephone(resultSet.getString(6).trim());
                teachers.add(temp);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return teachers;
        }
        catch (SQLException E){
            System.out.println("Exception"+E);
            return null;
        }
    }

    //获得所有课程
    public ArrayList<Course> getAllCourses(){
        try {
            ArrayList<Course> courses=new ArrayList<Course>();
            Connection connection=dataSource.getConnection();
            String sql="select *from gaoy_Courses02";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Course temp=new Course();
                temp.setcNo(resultSet.getString(1).trim());
                temp.setcName(resultSet.getString(2).trim());
                temp.setCreditHours(resultSet.getFloat(3));
                temp.setCheckMode(resultSet.getString(4));
                temp.setCredit(resultSet.getFloat(5));
                courses.add(temp);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return courses;
        }
        catch (SQLException E){
            System.out.println("Exception"+E);
            return null;
        }
    }


}
