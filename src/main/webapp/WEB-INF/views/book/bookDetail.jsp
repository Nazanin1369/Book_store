<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="../../cart/add?id=${book.id}" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td>${book.title}</td>
        </tr>
        <tr>
            <td>Author:</td>
            <td>${book.author}</td>
        </tr>
        <tr>
            <td>Price:</td>
            <td>${book.price}</td>
        </tr>
        <tr>
            <td>Quantity:</td>
            <td><input type="text" name="qty"/> </td>
        </tr> 
    </table>
    <button type="submit">Add To Cart</button>
</form>   