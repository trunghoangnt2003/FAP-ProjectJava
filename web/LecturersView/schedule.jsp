<%-- 
    Document   : schedule
    Created on : Feb 2, 2024, 1:23:07 PM
    Author     : trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Lession"%>
<%@page import="model.Group"%>
<%@page import="model.Course"%>
<%@page import="model.Room"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAP</title>
        <style>
            .red{
                color: red;
            }
            .green{
                color: green;
            }
            th{
                width: 350px;
            }
        </style>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <br>
                <button onclick="goBack()"class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Back</button>
                <br>
                <div class="container-fluid">
                    <strong><h1>Activities for ${sessionScope.user.email}(${sessionScope.user.name})</h1> <br> </strong>
                Note: These activities do not include extra-curriculum activities, such as club activities ...<br>

                Chú thích: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc bộ ...<br>

                Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br>
                Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br>
                Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br>
                Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br>
                Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br>
                Little UK (LUK) thuộc tầng 5 tòa nhà Delta<br>      
                <br>
            </div>
            <div class="container-fluid">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr class="table-info">
                            <th rowspan="2">
                                <form action="schedule" id="formSchedule" method="GET">
                                    <span class="auto-style1" style="color: red"><strong>Year</strong></span>
                                    <select name="year" onchange="this.form.submit()">
                                        <option value="2021" <%= (request.getParameter("year") != null && request.getParameter("year").equals("2021")) ? "selected=\"selected\"" : "" %>>2021</option>
                                        <option value="2022" <%= (request.getParameter("year") != null && request.getParameter("year").equals("2022")) ? "selected=\"selected\"" : "" %>>2022</option>
                                        <option value="2023" <%= (request.getParameter("year") != null && request.getParameter("year").equals("2023")) ? "selected=\"selected\"" : "" %>>2023</option>
                                        <option value="2024" <%= (request.getParameter("year") != null && request.getParameter("year").equals("2024")) ? "selected=\"selected\"" : "" %>>2024</option>
                                        <option value="2025" <%= (request.getParameter("year") != null && request.getParameter("year").equals("2025")) ? "selected=\"selected\"" : "" %>>2025</option>
                                    </select>
                                </form>

                                <form action="schedule" method="GET">  
                                    <input name="year" value="${requestScope.year}" type="hidden">
                                    Week
                                    <select name="week" onchange="this.form.submit()">
                                        <c:forEach items="${requestScope.weeksOfYear}" var="day">
                                            <option value="${day.value}" <c:if test="${requestScope.week == day.value}">selected = "selected"</c:if>>${day.detail}</option>
                                        </c:forEach>
                                    </select>

                                </form>
                            </th>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            <th>Saturday</th>
                            <th>Sunday</th>
                        </tr>
                        <tr class="table-info">
                            <th>${requestScope.date2}</th>
                            <th>${requestScope.date3}</th>
                            <th>${requestScope.date4}</th>
                            <th>${requestScope.date5}</th>
                            <th>${requestScope.date6}</th>
                            <th>${requestScope.date7}</th>
                            <th>${requestScope.date8}</th>
                                                    
                        </tr>
                        
                    </thead>

                    <%for(int slot = 0;slot<=12;slot++){%>
                    <tr class="table-light">
                        <td>SLOT <%=slot%></td>

                        <%
                            
                            for(int i = 2;i<=8;i++){
                                Object obj = request.getAttribute("list"+i);
                                if(obj!=null){
                                    boolean flag = true;
                                    ArrayList<Lession> list = (ArrayList<Lession>) obj;
                                    if(list.isEmpty()){%>
                        <td>-</td>
                        <%}else{
                              
                    for(Lession lession : list){
                       if(lession.getSlot().getIdSlot()==slot){ flag = false;%>
                        <td>
                            <%= lession.getGroup().getCourse().getCodeCourse() %><br>
                            at: <%= lession.getRoom().getNameRoom()%> <br>
                            <%if(lession.getStatus()==0){%><span style="color: red;font-size: 12px">NOT YET<br></span><%}%>
                            <a href="../lecture/take?id=<%=lession.getIdLession()%>">Attendance</a>
                            <div style="text-align: center;background: darkslategrey;color: white;border-radius:30px 30px 30px 30px;width: 95px">
                                (<%=lession.getSlot().getTimeLine()%>)
                            </div>
                        </td>
                        <%}else{ %>

                        <%}}
                            if(flag){%>
                        <td>-</td>
                        <%}}}else { %>
                        <%}}%>





                    </tr> 
                    <%}%>



                </table>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
