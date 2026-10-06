<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Thêm quần áo</title>

</head>

<body>

<h1>THÊM QUẦN ÁO</h1>


<form
    action="${pageContext.request.contextPath}/clothing"
    method="post">


    <input type="hidden"
           name="action"
           value="add">


    Tên quần áo:

    <input type="text"
           name="clothingName"
           required>

    <br><br>


    Loại:

    <select name="cateId">

        <option value="1">
            Áo
        </option>

        <option value="2">
            Quần
        </option>

        <option value="3">
            Váy
        </option>

        <option value="4">
            Áo khoác
        </option>

        <option value="5">
            Đồ thể thao
        </option>

    </select>

    <br><br>


    Size:

    <input type="text"
           name="size">

    <br><br>


    Màu:

    <input type="text"
           name="color">

    <br><br>


    Số lượng:

    <input type="number"
           name="quantity"
           min="0"
           required>

    <br><br>


    Giá:

    <input type="number"
           name="price"
           min="0"
           required>

    <br><br>


    Hình ảnh:

    <input type="text"
           name="image">

    <br><br>


    Mô tả:

    <br>

    <textarea
        name="description"
        rows="5"
        cols="40">
    </textarea>

    <br><br>


    <button type="submit">
        Lưu
    </button>


</form>


<br>


<a href="${pageContext.request.contextPath}/clothing">
    ← Quay lại danh sách
</a>


</body>

</html>