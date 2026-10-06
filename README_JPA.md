# QuanLyQuanAo - JPA/Hibernate

Project đã được nâng cấp từ JDBC sang JPA với Hibernate ORM.

## Công nghệ
- Java 17
- Jakarta Servlet 6 / JSP 3.1
- JPA 3.1
- Hibernate ORM 6.4.10.Final
- MySQL
- Tomcat 10.1+
- Maven

## Database
1. Mở MySQL.
2. Chạy file `../database.sql` để tạo database `sql_quanlyquanao`.
3. Mặc định JPA kết nối:
   - Host: localhost
   - Port: 3306
   - Database: sql_quanlyquanao
   - User: root
   - Password: 12345

Nếu máy bạn dùng mật khẩu MySQL khác, sửa `src/main/resources/META-INF/persistence.xml`.

## Import Eclipse
1. Giải nén ZIP.
2. Eclipse → File → Import → Maven → Existing Maven Projects.
3. Chọn thư mục `Quanlyquanao`.
4. Finish và chờ Maven tải dependency.
5. Project Facets/Server chọn Tomcat 10.1+.
6. Run on Server.

## JPA nằm ở đâu?
- `src/main/resources/META-INF/persistence.xml`: cấu hình persistence unit.
- `com.quanao.jpa.JPAUtil`: tạo EntityManager.
- `com.quanao.model.Category`: JPA Entity + quan hệ 1-N với Clothing.
- `com.quanao.model.Clothing`: JPA Entity + quan hệ N-1 với Category.
- `com.quanao.model.User`: JPA Entity.
- `com.quanao.dao.impl.*`: CRUD/query bằng EntityManager và JPQL.

Các Servlet/JSP và Service cũ được giữ lại để hạn chế thay đổi giao diện và luồng xử lý.
`DBConnection` chỉ còn là lớp tương thích và không còn được DAO sử dụng.
