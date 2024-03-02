<%-- 
    Document   : attendance
    Created on : Feb 8, 2024, 10:56:52 PM
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
        </style>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </head>
    <body>

        <%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
        %>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <br>
                <button onclick="goBack()"class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Back</button>
                <br>
                <div class="container">
                    <h2>Course: ${requestScope.lession.group.course.nameCourse}-${requestScope.lession.group.course.codeCourse}</h2>
                <h3>Lecture: ${requestScope.lession.lecture.name}</h3>
                <h4>Date of lession: ${requestScope.lession.date}</h4>
                <h5>Slot: ${requestScope.lession.slot.idSlot}</h5>
                <h5>Semester: ${requestScope.lession.group.semester.codeSemester}</h5>
            </div>
            <c:if test="${requestScope.lession.status==0}">
                <div class="container-fluid">
                    <form action="<%=url%>/lecture/take" method="post">
                        <table class="table align-middle mb-0 bg-white table-hover">
                            <thead class="bg-light">
                                <tr class="table-dark">
                                    <th>STT</th>
                                    <th>Thông Tin Sinh Viên</th>
                                    <th>Mã Số Sinh Viên</th>
                                    <th>Trạng Thái</th>
                                </tr>
                            </thead>
                            <c:set var="count" value="0"></c:set>
                                <tbody>
                                <c:forEach items="${requestScope.status}" var="status">
                                    <tr class="table-light">
                                        <c:set var="count" value="${count+1}"></c:set>
                                        <td>${count}</td>
                                        <td>
                                            <div class="d-flex align-items-center">
                                                <img
                                                    src="../img/${status.student.id}.png"
                                                    alt=""
                                                    style="width: 80px; height: 80px"
                                                    class="rounded-circle"
                                                    />
                                                <div class="ms-3">
                                                    <p class="fw-bold mb-1">${status.student.name}</p>
                                                    <p class="text-muted mb-0">${status.student.email}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <p class="fw-normal mb-1">${status.student.id}</p>
                                        </td>
                                        <td>
                                            <input class="form-check-input" type="hidden" value="${status.student.id}" name="id"/>
                                            <!-- Default radio -->
                                            <div class="form-check">
                                                <input class="form-check-input"
                                                       <c:if test="${status.status==1}">checked</c:if>
                                                       type="radio" name="${status.student.id}" value="1" required/>
                                                <label class="form-check-label" for="flexRadioDefault1"> attended </label>
                                            </div>

                                            <!-- Default checked radio -->
                                            <div class="form-check">
                                                <input class="form-check-input"
                                                       <c:if test="${status.status==0}">checked</c:if>
                                                       type="radio" name="${status.student.id}" value="0" required/>
                                                <label class="form-check-label" for="flexRadioDefault2"> absent </label>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                        <br>
                        <br>
                        <input class="form-check-input" type="hidden" value="${requestScope.reTake}" name="reTake"/>
                        <input class="form-check-input" type="hidden" value="${requestScope.lession.idLession}" name="idLession"/>
                        <button data-mdb-ripple-init type="submit" class="btn btn-primary btn-block mb-4">SAVE</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${requestScope.lession.status==1}">
                <div class="container-fluid">
                    <table class="table align-middle mb-0 bg-white table-hover">
                        <thead class="bg-light">
                            <tr class="table-dark">
                                <th>STT</th>
                                <th>Thông Tin Sinh Viên</th>
                                <th>Mã Số Sinh Viên</th>
                                <th>Trạng Thái</th>
                                <th>Ngày Điểm Danh</th>
                            </tr>
                        </thead>
                        <c:set var="count" value="0"></c:set>
                            <tbody>
                            <c:forEach items="${requestScope.status}" var="status">
                                <tr class="table-light">
                                    <c:set var="count" value="${count+1}"></c:set>
                                    <td>${count}</td>
                                    <td>
                                        <div class="d-flex align-items-center">
                                            <img
                                                src="../img/${status.student.id}.png"
                                                alt=""
                                                style="width: 80px; height: 80px"
                                                class="rounded-circle"
                                                />
                                            <div class="ms-3">
                                                <p class="fw-bold mb-1">${status.student.name}</p>
                                                <p class="text-muted mb-0">${status.student.email}</p>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <p class="fw-normal mb-1">${status.student.id}</p>
                                    </td>
                                    <td>
                                        <div>
                                            <c:if test="${status.status==0}">
                                                <span class="red">absent</span>
                                            </c:if>
                                            <c:if test="${status.status==1}">
                                                <span class="green">attended</span>
                                            </c:if>
                                        </div>
                                    </td>
                                    <td>
                                        ${status.date}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                    <br>
                    <br>
                    <form action="<%=url%>/lecture/take" method="get">
                        <input class="form-check-input" type="hidden" value="1" name="reTake"/>
                        <input class="form-check-input" type="hidden" value="${requestScope.lession.idLession}" name="id"/>
                        <button data-mdb-ripple-init type="submit" class="btn btn-primary btn-block mb-4">Re Take</button>
                    </form>


                </div>
            </c:if>


        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
