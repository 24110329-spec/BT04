<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Quản lý quần áo</title>

</head>

<body>

<h1>QUẢN LÝ QUẦN ÁO</h1>


<a href="${pageContext.request.contextPath}/views/clothing/add.jsp">
    + Thêm quần áo
</a>


<br><br>


<table border="1" cellpadding="10">

    <tr>

        <th>ID</th>

        <th>Tên quần áo</th>

        <th>Loại</th>

        <th>Size</th>

        <th>Màu</th>

        <th>Số lượng</th>

        <th>Giá</th>

        <th>Thao tác</th>

    </tr>


<%

    java.util.List<com.quanao.model.Clothing> list =
        (java.util.List<com.quanao.model.Clothing>)
        request.getAttribute("list");


    if (list != null) {

        for (com.quanao.model.Clothing c : list) {

%>


    <tr>

        <td>
            <%= c.getClothingId() %>
        </td>


        <td>
            <%= c.getClothingName() %>
        </td>


        <td>
            <%= c.getCateId() %>
        </td>


        <td>
            <%= c.getSize() %>
        </td>


        <td>
            <%= c.getColor() %>
        </td>


        <td>
            <%= c.getQuantity() %>
        </td>


        <td>
            <%= c.getPrice() %>
        </td>


        <td>

            <a href="${pageContext.request.contextPath}/clothing?action=edit&id=<%=c.getClothingId()%>">
                Sửa
            </a>

            |

            <a href="${pageContext.request.contextPath}/clothing?action=delete&id=<%=c.getClothingId()%>"
               onclick="return confirm('Bạn có chắc muốn xóa không?');">
                Xóa
            </a>

        </td>

    </tr>


<%

        }

    }

%>


</table>


</body>

</html>