<%@ page contentType="text/html; charset=UTF-8" %>

<%

    com.quanao.model.Clothing c =
        (com.quanao.model.Clothing)
        request.getAttribute("clothing");

%>


<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Sửa quần áo</title>

</head>

<body>


<h1>SỬA QUẦN ÁO</h1>


<form
    action="${pageContext.request.contextPath}/clothing"
    method="post">


    <input type="hidden"
           name="action"
           value="update">


    <input type="hidden"
           name="clothingId"
           value="<%=c.getClothingId()%>">


    Tên quần áo:

    <input type="text"
           name="clothingName"
           value="<%=c.getClothingName()%>"
           required>

    <br><br>


    Loại:

    <select name="cateId">


        <option value="1"
            <%= c.getCateId() == 1
                ? "selected" : "" %>>

            Áo

        </option>


        <option value="2"
            <%= c.getCateId() == 2
                ? "selected" : "" %>>

            Quần

        </option>


        <option value="3"
            <%= c.getCateId() == 3
                ? "selected" : "" %>>

            Váy

        </option>


        <option value="4"
            <%= c.getCateId() == 4
                ? "selected" : "" %>>

            Áo khoác

        </option>


        <option value="5"
            <%= c.getCateId() == 5
                ? "selected" : "" %>>

            Đồ thể thao

        </option>


    </select>


    <br><br>


    Size:

    <input type="text"
           name="size"
           value="<%=c.getSize()%>">

    <br><br>


    Màu:

    <input type="text"
           name="color"
           value="<%=c.getColor()%>">

    <br><br>


    Số lượng:

    <input type="number"
           name="quantity"
           value="<%=c.getQuantity()%>"
           min="0">

    <br><br>


    Giá:

    <input type="number"
           name="price"
           value="<%=c.getPrice()%>"
           min="0">

    <br><br>


    Hình ảnh:

    <input type="text"
           name="image"
           value="<%=c.getImage()%>">

    <br><br>


    Mô tả:

    <br>

    <textarea
        name="description"
        rows="5"
        cols="40"><%=c.getDescription()%></textarea>

    <br><br>


    <button type="submit">
        Cập nhật
    </button>


</form>


<br>


<a href="${pageContext.request.contextPath}/clothing">
    ← Quay lại danh sách
</a>


</body>

</html>