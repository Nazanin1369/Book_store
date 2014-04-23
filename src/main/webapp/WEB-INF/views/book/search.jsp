<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Search Books!</h2>


<form action="search" method="post">
    <table>
        <tr>
            <td>Book Search:</td>
            <td><input type="text" name="bookSearch"/> </td>
        </tr>
        <tr>
            <td>Category Search</td>
            <td><input type="text" name="categorySearch"/> </td>
        </tr>
    </table>
    <input type="submit" value="search"/>
</form>