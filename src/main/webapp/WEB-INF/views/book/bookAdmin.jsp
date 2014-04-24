<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Books currently in the shop</h2>
<table>
    <c:forEach var="book" items="${books}">
        <tr>
<!--           <td>${book.title}</td> -->
            <td>${book.author}</td>
            <td>${book.price}</td>
            <td><a href="update/${book.id}">edit</a></td>
        </tr>
    </c:forEach>
</table>

<a href="add"> Add a Book</a>