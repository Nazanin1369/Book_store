<%-- 
    Document   : register
    Created on : Apr 23, 2014, 10:35:33 PM
    Author     : Nazanin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <h1>Registration</h1>
   <form action="account/registration" method="post">
        <table>
            <tr>
                <td>First name</td>
                <td><input type="text" name='r_firstName' /></td>
            </tr>
            <tr>
                <td>Last name</td>
                <td><input type="text" name='r_lastName' /></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="text" name='r_dateOfBirth' /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name='r_email' /></td>
            </tr> 
            <tr>
                <td>User name</td>
                <td><input type="text" name='r_username' /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td> <input type="password" name='r_password' /></td>
            </tr>
            <tr>
                <td></td>
                <td> <input type="submit" value="register"/></td>
            </tr>
        </table>
   </form>
</html>
