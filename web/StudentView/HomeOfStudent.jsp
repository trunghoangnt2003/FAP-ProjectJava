<%-- 
    Document   : HomeOfStudent
    Created on : Jan 30, 2024, 5:00:31 PM
    Author     : trung
--%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.DayOfWeek"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css"
            rel="stylesheet"
            />
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>
            <div class="container" style="height: 600px">
                <ul class="list-group list-group-light">
                    <li class="list-group-item px-3 border-0 active text-center" aria-current="true">
                        Thông tin lớp học và điểm danh
                    </li>
                <%
                        LocalDate currentDate = LocalDate.now();

                        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);
                        String month = "";
                        if(monday.getMonthValue()<=9){
                            month="0"+monday.getMonthValue();
                        }else {
                             month=monday.getMonthValue()+"";
                        }
                        String day = "";
                        if(monday.getDayOfMonth()<=9){
                        day="0"+monday.getDayOfMonth();
                    }else{
                        day = monday.getDayOfMonth()+"";
                    }
                %>
                <li class="list-group-item px-3 border-0"><a href="student/schedule?year=<%=monday.getYear()%>&week=<%=day%>%2F<%=month%>">Thời khóa biểu</a></li>
                <li class="list-group-item px-3 border-0"><a href="student/score?idSemester=3">Điểm số</a></li>

            </ul>  
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
