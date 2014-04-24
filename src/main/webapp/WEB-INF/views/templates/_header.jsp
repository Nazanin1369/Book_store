<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>BookStore!!</h1>

<c:set var="principal" value="${SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()}"/>
<c:if test="{not empty principal">
    <div style="float:right">
        Hello ${principal}!&nbsp;&nbsp; <a href="../logout">Logout</a> 
    </div>
</c:if>