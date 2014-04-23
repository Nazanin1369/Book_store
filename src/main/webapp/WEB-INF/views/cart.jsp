<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>This is my Cart!</h2>
There are ${cart.size()} items in your cart<br/>

<form action="./checkout" method="post">
    <table>  
        <tr>
            <th>Book</th>
            <th>Quantity</th>
        </tr>
        <c:forEach items="${cart}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>        

    </table>
    <button type="submit">Check Out</button>

</form>