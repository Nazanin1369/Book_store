<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="${book.id}" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" value="${book.title}"/> </td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" value="${book.author}"/> </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" value="${book.price}"/> </td>
        </tr>
    </table>
    <input type="submit" value="update"/>
</form>
        
<form action="../../cart/add/${book.id}" method="post">
    <button type="submit">AddToCart</button>
</form> 
       

<form action="../delete/${book.id}" method="post">
    <button type="submit">Delete</button>
</form>
    
    