<%-- 
    Document   : attendanceStatus
    Created on : Mar 8, 2024, 10:10:54 PM
    Author     : trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAP</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
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
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <br>
            <button onclick="goBack()"class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Back</button>
            <br>
            <h3>Attendance report for ${sessionScope.user.name}(${sessionScope.user.id})</h3>
        <c:set var = "check" scope = "request" value = "${check}"/>
        <c:if test="${check==true}">
            <div class="container"> 
                <table class="table table-hover">
                    <thead>
                        <tr> 
                            <th scope="col" class="table-secondary">Semester</th>                  
                        </tr>
                    </thead>
                    <c:forEach items="${requestScope.semesters}" var="s">
                        <tr <c:if test="${s.idSemester == param.idSemester}">class="table-light"</c:if> >
                            <c:if test="${s.idSemester != param.idSemester}">
                                <td><a href="../student/attReport?idSemester=${s.idSemester}"/>${s.nameSemester}</td>
                            </c:if>
                            <c:if test="${s.idSemester == param.idSemester}">
                                <td><strong>${s.nameSemester}</strong></td>
                            </c:if>
                        </tr>
                    </c:forEach>

                </table>
                <table class="table table-hover">
                    <thead>
                        <tr> 
                            <th scope="col" class="table-secondary">Course</th>                  
                        </tr>
                    </thead>
                    <c:forEach items="${requestScope.groups}" var="g">
                        <tr>
                            <td><a href="../student/attReport?rollName=${sessionScope.user.id}&group=${g.idGroup}&idSemester=${param.idSemester}">${g.course.codeCourse}-${g.course.nameCourse}(${g.codeGroup})</a></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </c:if>
        <c:if test="${check!=true}">
                <div class="container row">
                    <table class="table col table-hover col">
                        <thead>
                            <tr> 
                                <th scope="col" class="table-secondary">Semester</th>                  
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.semesters}" var="s">
                            <tr <c:if test="${s.idSemester == param.idSemester}">class="table-light"</c:if>>
                                <c:if test="${s.idSemester != param.idSemester}">
                                    <td><a href="../student/attReport?idSemester=${s.idSemester}"/>${s.nameSemester}</td>
                                </c:if>
                                <c:if test="${s.idSemester == param.idSemester}">
                                    <td><strong>${s.nameSemester}</strong></td>
                                </c:if>
                            </tr>
                        </c:forEach>

                    </table>
                    <table class="table col table-hover">
                        <thead>
                            <tr>
                                <th scope="col" class="table-secondary">Course</th>                  
                            </tr>
                        </thead>

                        <c:forEach items="${requestScope.groups}" var="g">
                            <tr <c:if test="${g.idGroup==param.group}">class="table-light"</c:if>>
                                <c:if test="${g.idGroup != param.group}">
                                    <td><a href="../student/attReport?rollName=${sessionScope.user.id}&group=${g.idGroup}&idSemester=${param.idSemester}">${g.course.codeCourse}-${g.course.nameCourse}(${g.codeGroup})</a></td>
                                </c:if>
                                <c:if test="${g.idGroup==param.group}">
                                    <td><strong>${g.course.codeCourse}-${g.course.nameCourse}(${g.codeGroup})</strong></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </div>
                <h3>... then see report </h3>
                <div class="container align-items-center row" style="width: 70%">

                    <div class="container col-md-8">
                        <table border="1" class="table">
                            <thead>
                                <tr class="table-info">
                                    <td class="col">NO</td>
                                    <td class="col">Date</td>
                                    <td class="col">SLOT</td>
                                    <td class="col">ROOM</td>
                                    <td class="col">LECTURER</td>
                                    <td class="col">GROUP NAME</td>
                                    <td class="col">ATTEDANCE STATUS</td>
                                </tr>
                            </thead>
                            <c:set var="numberOfLession" value="${requestScope.group.course.numberLessionOfCourse}"/>
                            <c:set var="absent" value="0"/>
                            <c:set var="no" value="1"/>
                            <c:forEach  items="${requestScope.status}" var="s">
                                <tr>
                                    <td>${no}</td>
                                    <td>${s.lession.date}</td>
                                    <td>SLOT ${s.lession.slot.idSlot}(${s.lession.slot.timeLine})</td>
                                    <td>${s.lession.room.nameRoom}</td>
                                    <td>${s.lession.lecture.name}</td>
                                    <td>${s.lession.group.codeGroup}</td>
                                    <td>
                                        <c:if test="${s.status == -1}">
                                            <span class="red">absent</span> 
                                            <c:set var="absent" value="${absent+1}"/>
                                        </c:if>
                                        <c:if test="${s.status == 1}">
                                            <span class="green">attended</span> 
                                        </c:if>
                                        <c:if test="${s.status == 0}">
                                            <span>future</span> 
                                        </c:if>
                                    </td>
                                </tr>
                                <c:set var="no" value="${no+1}"/>


                            </c:forEach>

                            <tr class="table-info">
                                <td class="text-center" colspan="7">ABSENT:${absent/numberOfLession*100}%  ABSENT SO FAR ( ABSENT ${absent} ON ${numberOfLession} TOTAL).</td>
                            </tr>
                        </table>
                    </div>
                    <div class="container col-md-4">
                        <canvas id="myChart" style="width:100%;max-width:800px"></canvas>
                    </div>

                </div>



        </c:if>
        <jsp:include page="footer.jsp"></jsp:include>
        </body>
    </html>
    <script>
        const xValues = ["Absent", "Total"];
        const yValues = [${absent/numberOfLession*100}, 100];
        const barColors = [
            "#b91d47",
            "#2b5797",
        ];

        new Chart("myChart", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                        backgroundColor: barColors,
                        data: yValues
                    }]
            },
            options: {
                title: {
                    display: true,
                    text: "Attendance report ${requestScope.group.course.nameCourse}"
                }
            }
        });
</script>

