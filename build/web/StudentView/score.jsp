<%-- 
    Document   : newjsp
    Created on : Feb 14, 2024, 4:24:36 PM
    Author     : trung
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <h3>Grade report for ${sessionScope.user.name}(${sessionScope.user.id})</h3>
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
                                <td><a href="../student/score?idSemester=${s.idSemester}"/>${s.nameSemester}</td>
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
                    <c:forEach items="${requestScope.group}" var="g">
                        <tr>
                            <td><a href="../student/score?rollName=${sessionScope.user.id}&group=${g.idGroup}&idSemester=${param.idSemester}">${g.course.codeCourse}-${g.course.nameCourse}(${g.codeGroup})</a></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </c:if>
        <c:if test="${check!=true}">
            <div class="container row">
                <table class="table col table-hover">
                    <thead>
                        <tr> 
                            <th scope="col" class="table-secondary">Semester</th>                  
                        </tr>
                    </thead>
                    <c:forEach items="${requestScope.semesters}" var="s">
                        <tr <c:if test="${s.idSemester == param.idSemester}">class="table-light"</c:if>>
                            <c:if test="${s.idSemester != param.idSemester}">
                                <td><a href="../student/score?idSemester=${s.idSemester}"/>${s.nameSemester}</td>
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

                    <c:forEach items="${requestScope.group}" var="g">
                        <tr <c:if test="${g.idGroup==param.group}">class="table-light"</c:if>>
                            <c:if test="${g.idGroup != param.group}">
                                <td><a href="../student/score?rollName=${sessionScope.user.id}&group=${g.idGroup}&idSemester=${param.idSemester}">${g.course.codeCourse}-${g.course.nameCourse}(${g.codeGroup})</a></td>
                            </c:if>
                            <c:if test="${g.idGroup==param.group}">
                                <td><strong>${g.course.codeCourse}-${g.course.nameCourse}(${g.codeGroup})</strong></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <h3>... then see report </h3>
                <div class="container align-items-center" style="width: 70%">

                    <table border="1" class="table">


                        <c:set var="sum" value="0"></c:set>
                        <c:set var="percent" value="0"></c:set>
                            <tbody>
                                <tr class="table-dark">
                                    <th scope="col">GRADE CATEGORY</th>
                                    <th scope="col">GRADE ITEM</th>
                                    <th scope="col">WEIGHT</th>
                                    <th scope="col">VALUE</th>
                                </tr>
                            <c:forEach items="${requestScope.category}" var="category">
                                <c:set var="count" value="0"/>
                                <c:forEach items="${point}" var="p">
                                    <c:if test="${p.category.idCategory==category.idCategory}">
                                        <c:set var="count" value="${count+1}"/>
                                    </c:if>
                                </c:forEach>
                                <c:set var="totalPercent" value="0"/>
                                <c:set var="check" value="0"/>
                                <c:set var="countCheck" value="0"/>
                                <c:set var="total" value="0"/>
                                <tr>
                                    <td rowspan="${count+1}">${category.nameCategory} </td>

                                    <c:forEach items="${point}" var="p">
                                        <c:if test="${p.category.idCategory==category.idCategory}">

                                        <c:if test="${check!=0}"><tr></c:if>
                                            <td>${p.describe}</td>
                                            <fmt:formatNumber value="${p.percent*100.0}" pattern="#0.0" var="roundedValue" />
                                            <c:set var="totalPercent" value="${totalPercent+p.percent}"/>
                                            <td>${roundedValue}%</td>
                                            <td>
                                                <c:forEach items="${scores}" var="s">
                                                    <c:if test="${s.point.idPoint == p.idPoint}">
                                                        ${s.value}
                                                        <c:set var="total" value="${total+s.value}"/>
                                                        <c:set var="countCheck" value="${countCheck+1}"/>
                                                        <c:set var="sum" value="${sum + s.point.percent*s.value}"></c:set>
                                                        <c:set var="percent" value="${percent + s.point.percent}"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                &nbsp;
                                            </td>

                                            <c:if test="${check!=0}"></tr></c:if>
                                            <c:set var="check" value="${check+1}"/>
                                        </c:if>

                                </c:forEach>
                                <tr class="table-secondary">
                                    <td><b>TOTAL</b></td>
                                    <fmt:formatNumber value="${totalPercent*100.0}" pattern="#0.0" var="totalPercent" />
                                    <td><b>${totalPercent}%</b></td>
                                    <td> <b>
                                            <c:if test="${count == countCheck}">
                                                <fmt:formatNumber value="${total/count}" pattern="#0.0" var="total" />
                                                ${total}

                                            </c:if>
                                            &nbsp;
                                        </b>
                                    </td>
                                </tr>

                                </tr>    

                            </c:forEach>


                        </tbody>
                    </table>

                    <fmt:formatNumber value="${sum}" pattern="#0.0" var="total" />
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">COURSE TOTAL</th>
                                <th scope="col">AVERAGE</th>
                                    <c:if test="${!(percent<1)}">
                                    <th scope="col">${total}</th>
                                    </c:if>
                                    <c:if test="${percent<1}">
                                    <th scope="col"></th>
                                    </c:if>



                            </tr>
                        </thead>
                        <tr class="table-dark">
                            <th scope="col"></th>
                            <th scope="col">STATUS</th>
                            <th scope="col">
                                <c:if test="${percent<1}">
                                    <span style="color: green">STUDYING</span>
                                </c:if>
                                <c:if test="${!(percent<1)}">
                                    <c:if test="${total>=5.0}">
                                        <span style="color: greenyellow">PASSED</span>
                                    </c:if>
                                    <c:if test="${total<5.0}">
                                        <span  style="color: red">NOT PASSED</span>
                                    </c:if>
                                </c:if>

                            </th>                            
                        </tr>
                    </table>



                </div>


            </div>
        </c:if>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
