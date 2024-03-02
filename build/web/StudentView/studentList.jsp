<%-- 
    Document   : studentList
    Created on : Feb 25, 2024, 11:46:33 AM
    Author     : trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAP</title>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <br>
            <button onclick="goBack()"class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Back</button>
            <br>
            <div class="container-fluid">

                <table class="table align-middle mb-0 bg-white">
                    <thead class="bg-light">
                        <tr>
                            <th>STT</th>
                            <th>Thông Tin Sinh Viên</th>
                            <th>Mã Số Sinh Viên</th>
                        </tr>
                    </thead>

                    <tbody>
                    <c:set var="count" value="0"></c:set>
                    <c:forEach items="${requestScope.list}" var="student">
                        <tr>
                            <c:set var="count" value="${count+1}"></c:set>
                            <td>${count}</td>
                            <td>
                                <div class="d-flex align-items-center">
                                    <img
                                        src="../img/${student.id}.png"
                                        alt=""
                                        style="width: 80px; height: 80px"
                                        class="rounded-circle"
                                        />
                                    <div class="ms-3">
                                        <p class="fw-bold mb-1">${student.name}</p>
                                        <p class="text-muted mb-0">${student.email}</p>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <p class="fw-normal mb-1">${student.id}</p>
                            </td>                                        
                        </tr>
                    </c:forEach>
                </tbody>

            </table>


        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
