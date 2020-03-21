<%@ page import="java.util.ArrayList" %>
<%@ page import="modal.gradeReport" %>
<%@ page import="modal.getInformation" %>
<%@ page import="modal.Student" %>
<%@ page import="modal.ClassCourses" %><%--
  Created by IntelliJ IDEA.
  User: 13345
  Date: 2019/7/5
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin - Start Bootstrap Template</title>
    <!-- Bootstrap core CSS-->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../css/sb-admin.css" rel="stylesheet">
</head>
<%
    String mess = (String) session.getAttribute("InsertStudentMessage");
    if ("".equals(mess) || mess == null) {

    } else {%>
<script type="text/javascript">
    alert("<%=mess%>");
</script>
<% session.setAttribute("InsertStudentMessage", "");
}%>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.html">高校成绩管理系统</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
                <a class="nav-link" href="index.jsp">
                    <i class="fa fa-bandcamp" aria-hidden="true"></i>
                    <span class="nav-link-text">主页</span>
                </a>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
                <a class="nav-link" href="StudentSearchUpdate.jsp">
                    <i class="fa fa-address-card" aria-hidden="true"></i>
                    <span class="nav-link-text">查询,新增，删除学生</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
                <a class="nav-link" href="TeacherSearchUpdate.jsp">
                    <i class="fa fa-lock" aria-hidden="true"></i>
                    <span class="nav-link-text">查询，新增老师</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
                <a class="nav-link" href="CourseSearchUpdate.jsp">
                    <i class="fa fa-lock" aria-hidden="true"></i>
                    <span class="nav-link-text">查询，新增课程</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                <a class="nav-link" href="ClassCourseSearch.jsp">
                    <i class="fa fa-graduation-cap" aria-hidden="true"></i>
                    <span class="nav-link-text">班级课表查询</span>
                </a>
            </li>

        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" style="margin-right: 400px;">
                    <i class="fa fa-anchor" aria-hidden="true"></i>
                    欢迎您</a>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                    <i class="fa fa-fw fa-sign-out"></i>退出登录</a>
            </li>
        </ul>
    </div>
</nav>
<%! getInformation getInformation = new getInformation();%>
<div class="content-wrapper">
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <form action="StudentSearchUpdate.jsp">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">选择班级</li>
                <li class="breadcrumb-item">
                    <select name="major" id="major">
                        <option value="0605001">计算机专业科学与技术</option>
                        <option value="0605002">软件工程</option>
                        <option value="0605003">数字媒体</option>
                    </select>
                </li>
                <li>
                    <select name="Cyear" id="Cyear">
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                        <option value="2018">2018</option>
                    </select>
                </li>
                <li>
                    <select name="class" id="class">
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                    </select>
                </li>
                <li>
                    <input type="submit" value="查询" class="btn btn-default btn-sm">
                </li>
            </ol>
        </form>
        <!-- Example DataTables Card-->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> 学生查询
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>生源地</th>
                            <th>已修学分</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <%--<tfoot>--%>
                        <%--<tr>--%>
                        <%--<th>Name</th>--%>
                        <%--<th>Position</th>--%>
                        <%--<th>Office</th>--%>
                        <%--<th>Age</th>--%>
                        <%--<th>Start date</th>--%>
                        <%--<th>Salary</th>--%>
                        <%--</tr>--%>
                        <%--</tfoot>--%>
                        <tbody>
                        <%! ArrayList<Student> students = new ArrayList<Student>();%>
                        <% String major = request.getParameter("major");%>
                        <% String Cyear = request.getParameter("Cyear");%>
                        <% String CLno = request.getParameter("class");%>
                        <%if (major != null && major != "" && Cyear != null && Cyear != "" && CLno != null && CLno != "") {%>
                        <% students = getInformation.getClassStudents(major, Integer.parseInt(Cyear), CLno);
                            System.out.println("-------------------" + students.size());%>
                        <tr>
                            <form action="/manage/insertStudent?Mno=<%=major%>&Cyear=<%=Cyear%>&CLno=<%=CLno%>"
                                  method="post">
                                <td><input type="text" name="Sno"></td>
                                <td><input type="text" name="Sname"></td>
                                <td><select name="Ssex" id="Ssex">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select></td>
                                <td><input type="text" name="Sage"></td>
                                <td><input type="text" name="Shometown"></td>
                                <td>0</td>
                                <td><input type="submit" value="增加"></td>
                            </form>
                        </tr>

                        <% for (int i = 0; i < students.size(); i++) {%>
                        <tr>
                            <form action="/manage/deleteStudent?Sno=<%=students.get(i).getsNo()%>" method="post">
                                <td><%=students.get(i).getsNo()%>
                                </td>
                                <td><%=students.get(i).getsName()%>
                                </td>
                                <td><%=students.get(i).getsSex()%>
                                </td>
                                <td><%=students.get(i).getsAge()%>
                                </td>
                                <td><%=students.get(i).getsHometown()%>
                                </td>
                                <td><%=students.get(i).getsCredit()%>
                                </td>
                                <td><input type="submit" value="删除"></td>
                            </form>
                        </tr>
                        <%}%>
                        <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="../login.jsp">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="../vendor/chart.js/Chart.min.js"></script>
    <script src="../vendor/datatables/jquery.dataTables.js"></script>
    <script src="../vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="../js/sb-admin-datatables.min.js"></script>
    <script src="../js/sb-admin-charts.min.js"></script>
</div>
</body>

</html>
