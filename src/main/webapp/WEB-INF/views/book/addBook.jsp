<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="p" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" /> </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="description" /> </td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" /> </td>
        </tr>
        <tr>
            <td>Year:</td>
            <td><input type="text" name="year" /> </td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" /> </td>
        </tr>
        <!--
        <tr>
            <td>Isbn</td>
            <td><input type="text" name="Isbn" /> </td>
        </tr>
        -->
    </table>
    <input type="submit"/>
</form>