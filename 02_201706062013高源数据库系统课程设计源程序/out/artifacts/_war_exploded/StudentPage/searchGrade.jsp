<%@ page import="java.util.ArrayList" %>
<%@ page import="modal.gradeReport" %>
<%@ page import="modal.getInformation" %><%--
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
                <a class="nav-link" href="myInformation.jsp">
                    <i class="fa fa-address-card" aria-hidden="true"></i>
                    <span class="nav-link-text">个人信息</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
                <a class="nav-link" href="ChangePassword.jsp">
                    <i class="fa fa-lock" aria-hidden="true"></i>
                    <span class="nav-link-text">修改密码</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                <a class="nav-link" href="tables.html">
                    <i class="fa fa-graduation-cap" aria-hidden="true"></i>
                    <span class="nav-link-text">成绩查询</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                <a class="nav-link" href="learnedCourse.jsp">
                    <i class="fa fa-fw fa-table"></i>
                    <span class="nav-link-text">已选修课程</span>
                </a>
            </li>
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                <a class="nav-link" href="ClassCourseSearch.jsp">
                    <i class="fa fa-university" aria-hidden="true"></i>
                    <span class="nav-link-text">班级课程查询</span>
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
<div class="content-wrapper">
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <form action="searchGrade.jsp">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">选择学期</li>
                <li class="breadcrumb-item">
                    <select name="term" id="term">
                        <option value="20171">2017/2018(1)</option>
                        <option value="20172">2017/2018(2)</option>
                        <option value="20181">2018/2019(1)</option>
                        <option value="20182">2018/2019(2)</option>
                    </select>
                </li>
                <li class="breadcrumb-item"><input type="submit" class="btn btn-default btn-sm" value="查询"></li>
            </ol>
        </form>
        <!-- Example DataTables Card-->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> 学生成绩查询
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>课程</th>
                            <th>教师</th>
                            <th>学分</th>
                            <th>成绩</th>
                            <th>该课平均成绩</th>
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
                        <%! ArrayList<gradeReport> reports=new ArrayList<gradeReport>();%>
                        <%! getInformation getInformation=new getInformation();%>
                        <% if(request.getParameter("term")!=null&&request.getParameter("term")!=""){
                            String tempTerm=request.getParameter("term");
                            int year=Integer.parseInt(tempTerm.substring(0,4));
                            int term=Integer.parseInt(tempTerm.substring(4,5));
                            System.out.println(year);
                            System.out.println(term);
                            reports=getInformation.getReport((String)session.getAttribute("userID"),year,term);
                            for(int i=0;i<reports.size();i++){%>
                        <tr>
                            <td><%=reports.get(i).getcName()%></td>
                            <td><%=reports.get(i).gettName()%></td>
                            <td><%=reports.get(i).getCredit()%></td>
                            <td><%=reports.get(i).getGrade()%></td>
                            <td><%=reports.get(i).getAvgGrade()%></td>
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
