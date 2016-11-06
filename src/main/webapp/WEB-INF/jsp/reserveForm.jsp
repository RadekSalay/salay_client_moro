<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Reserve ticket</title>
</head>

<body>
<form:form method="post" action="/reserve" modelAttribute="ticket">
    <table>
        <tr>
            <td>firstName : </td>
            <td><form:input path="firstName" /></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td>lastName :</td>
            <td><form:input  path="lastName" /></td>
            <td><form:errors path="lastName"/></td>

        </tr>
        <tr>
            <td>email :</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email"/></td>

        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
