<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Search Books!</h2>


<form action="search_result" method="get">
    <table>
        <tr>
            <td>Book Search:</td>
            <td><input type="text" name="title"/> </td>
        </tr>
        <tr>
            <td>Category Search</td>
            <td><input type="text" name="category"/> </td>
        </tr>
    </table>
    <input type="submit" value="search"/>
</form>