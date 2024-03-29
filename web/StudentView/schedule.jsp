<%-- 
    Document   : schedule
    Created on : Feb 2, 2024, 1:23:07 PM
    Author     : trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Lession"%>
<%@page import="model.Status"%>
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
            .background {
                font-weight: 500;
                background-color: #6b90da;

            }
            body{
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
                color: #333;
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
                <div>
                    <br>
                    <button onclick="goBack()"class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Back</button>
                    <br>
                </div>
                <div class="container-fluid">
                    <strong><h1>Activities for ${sessionScope.user.id}(${sessionScope.user.name})</h1> <br> </strong>
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
                    <thead class="thead-dark" style="background: blue">
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
                                    <c:forEach items="${list}" var="status">                                        
                                        <c:if test="${status.lession.slot.idSlot == slot}">
                                            <td>
                                                <c:set var="flag" value="0"/>
                                                <a href="groupInformation?lession=${status.lession.idLession}">${status.lession.group.course.codeCourse}</a> 
                                                <br> at: ${status.lession.room.nameRoom}<br>
                                                    <c:if test="${status.status == -1}">
                                                    <span class="red">absent</span> 
                                                    </c:if>
                                                    <c:if test="${status.status == 1}">
                                                    <span class="green">attended</span> 
                                                    </c:if>
                                                    <c:if test="${status.status == 0}">
                                                    <span>(-)</span> 
                                                    </c:if>
                                                    <br>
                                                <div style="text-align: center;background: darkslategrey;color: white;border-radius:30px 30px 30px 30px;width: 95px">
                                                    (${status.lession.slot.timeLine})
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
            <br>
            More note / Chú thích thêm: <br>
            <br>
            <span class="green">(attended)</span>: ${sessionScope.user.name} had attended this activity / ${sessionScope.user.name} đã tham gia hoạt động này<br>
            <span class="red">(absent)</span>: ${sessionScope.user.name} had NOT attended this activity / ${sessionScope.user.name} đã vắng mặt buổi này<br>
            (-): no data was given / chưa có dữ liệu<br>

        </div>

        <script>
            function getWeekStartDate(year, week) {
                var startDate = new Date(year, 0, (week - 1) * 7 + 1);
                var day = startDate.getDate();
                var month = startDate.getMonth() + 1;
                var formattedDate = (day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month);
                return formattedDate;
            }

            function getWeekEndDate(year, week) {
                var endDate = new Date(year, 0, week * 7);
                var day = endDate.getDate();
                var month = endDate.getMonth() + 1;
                var formattedDate = (day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month);
                return formattedDate;
            }
        </script>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
