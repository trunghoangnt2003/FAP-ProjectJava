<%-- 
    Document   : schedule
    Created on : Feb 2, 2024, 1:23:07 PM
    Author     : trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                    <c:forEach begin="0" end="12" var="slot">
                        <tr class="table-light">
                            <td>SLOT ${slot}</td>
                            <c:forEach begin="2" end="8" var="thu">
                                <c:set var="listNumber" value="list${thu}"/>
                                <c:set var="list" value="${requestScope[listNumber]}"/>
                                
                                <c:if test="${not empty list}">
                                    <c:set var="flag" value="1" />
                                    <c:forEach items="${list}" var="lession">                                        
                                        <c:if test="${lession.slot.idSlot == slot}">
                                            <td>
                                                <c:set var="flag" value="0"/>
                                                ${lession.group.course.codeCourse} <br> at: ${lession.room.nameRoom}
                                                <c:if test="${lession.status == 0}">
                                                    <span style="color: red;font-size: 12px"><br>NOT YET<br></span> 
                                                </c:if>
                                                <a href="../lecture/take?id=${lession.idLession}">Attendance</a>
                                                <div style="text-align: center;background: darkslategrey;color: white;border-radius:30px 30px 30px 30px;width: 95px">
                                                    (${lession.slot.timeLine})
                                                </div>
                                            </td>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag == 1}">
                                        <td>-</td>
                                    </c:if>
                                        
                                </c:if>
                                <c:if test="${empty list}">
                                        <td>-</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:forEach>                   
                </table>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>


