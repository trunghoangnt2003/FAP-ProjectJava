<%-- 
    Document   : groupInformation
    Created on : Feb 21, 2024, 1:26:31 AM
    Author     : trung
--%>

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
            <div class="container">
                <br>
                <button onclick="goBack()" class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Back</button>
                <br>
                <br>
                <h1>Activity detail</h1>
                <br>
                <br>
                <br>
                <div class="container row">
                    <table class="table col">

                        <tbody>
                            <tr>
                                <td>Date</td>
                            </tr>
                            <tr>
                                <td>Slot</td>
                            </tr>
                            <tr>
                                <td>Student group</td>
                            </tr>
                            <tr>
                                <td>Instructor:</td>
                            </tr>
                            <tr>
                                <td>Course</td>
                            </tr>
                            <tr>
                                <td>Room</td>
                            </tr>

                        </tbody>
                    </table>
                    <table class="table col">

                        <tbody>
                            <tr>
                                <td>${requestScope.lession.date}</td>
                        </tr>
                        <tr>
                            <td>Slot ${requestScope.lession.slot.idSlot}</td>
                        </tr>
                        <tr>
                            <td><a href="listStudent?idGroup=${requestScope.lession.group.idGroup}">${requestScope.lession.group.codeGroup}</a></td>
                        </tr>
                        <tr>
                            <td>${requestScope.lession.lecture.name}</td>
                        </tr>
                        <tr>
                            <td>${requestScope.lession.group.course.nameCourse}</td>
                        </tr>
                        <tr>
                            <td>${requestScope.lession.room.nameRoom}</td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
