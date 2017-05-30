<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table border="0">
        <tr class="nav-header">
            <td colspan="5">
                <h2>
                    Title Placeholder
                    <div style="float: right">
                    <form action="ProcessServlet" method="POST">
                        <input type="text" placeholder="Username"/><br/>
                        <input type="password" placeholder="Password"/><br/>
                        <button type="submit" name="location" value="login">Login</button>
                    </form>
                    </div>
                </h2>
            </td>
        </tr>
        <tr class="nav-bar">
            <td>
                <a href="ProcessServlet">Trang chủ</a>
            </td>
            <td>
                <a href="ProcessServlet?location=explore">Khám Phá</a>
            </td>
            <td>Góc nhìn</td>
            <td>Diễn đàn</td>
        </tr>
    </table>
</div>