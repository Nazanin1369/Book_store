<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Books currently in the store</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Category</th>
        <th></th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.price}</td>
            <td>${book.category.name}</td>
            <td><a href="view/${book.id}">view</a></td>
        </tr>
    </c:forEach>
</table>