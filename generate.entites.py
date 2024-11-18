import mysql.connector
import os

TYPE_MAPPING = {
    'varchar': 'String',
    'char': 'String',
    'text': 'String',
    'int': 'Long',
    'bigint': 'Long',
    'datetime': 'LocalDateTime',
    'date': 'LocalDate',
    'boolean': 'Boolean',
    'float': 'Float',
    'double': 'Double',
    'decimal': 'BigDecimal'
}

def map_data_type(mysql_type):
    mysql_type = mysql_type.split('(')[0].lower()  # Loại bỏ kích thước nếu có (vd: varchar(255) -> varchar)
    return TYPE_MAPPING.get(mysql_type, 'String')  # Mặc định là String nếu không tìm thấy kiểu phù hợp

conn = mysql.connector.connect(
    host='localhost',      
    user='root',
    password='root',
    database='shopapp' 
)

# Tạo một đối tượng cursor để thực hiện các truy vấn
cursor = conn.cursor()

# Thực hiện truy vấn để lấy tên các bảng
cursor.execute("SHOW TABLES")
tables = cursor.fetchall()

# Hàm để tạo model cho Java Spring với Lombok
def create_model(name, is_dto=False):
    # Viết hoa chữ cái đầu tiên của tên bảng
    model_name = name.capitalize() + ("Dto" if is_dto else "")
    
    # Lấy thông tin các cột của bảng
    cursor.execute(f"DESCRIBE {name}")
    columns = cursor.fetchall()

    # Tạo mã model cho Java Spring với các annotation JPA và Lombok
    model_code = (
        "import lombok.Data;\n"
        "import lombok.NoArgsConstructor;\n"
        "import lombok.AllArgsConstructor;\n"
        "import lombok.Builder;\n\n"
    )

    # Thêm các import bổ sung nếu là model
    if not is_dto:
        model_code += "import javax.persistence.*;\nimport java.time.LocalDate;\nimport java.time.LocalDateTime;\n\n"

    # Thêm các annotation Lombok
    model_code += f"@Data\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n"
    
    # Nếu là model, thêm các annotation JPA
    if not is_dto:
        model_code += f"@Entity\n@Table(name = \"{name}\")\n"
    
    # Tạo lớp model hoặc DTO
    model_code += f"public class {model_name} {{\n"
    
    # Thêm các thuộc tính vào model dựa trên các cột
    for column in columns:
        column_name = column[0]
        column_type = column[1]
        java_type = map_data_type(column_type)
        
        # Kiểm tra nếu cột là khóa chính trong model (không áp dụng cho DTO)
        if not is_dto and "PRI" in column:
            model_code += f"    @Id\n"
            if "auto_increment" in column_type.lower():
                model_code += "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n"
        
        # Thêm annotation @Column cho model, nhưng không cho DTO
        if not is_dto:
            model_code += f"    @Column(name = \"{column_name}\")\n"
        model_code += f"    private {java_type} {column_name};\n\n"
    
    model_code += "}\n"
    
    return model_code

# Lựa chọn giữa Model và DTO
choice = input("Nhập '1' để render Model hoặc '2' để render DTO: ")

# Xác định xem người dùng chọn Model hay DTO
is_dto = choice == "2"

# Đặt đường dẫn lưu trữ tệp model bên trong thư mục "generatemodels/models" hoặc "generatemodels/dtos"
subfolder = "dtos" if is_dto else "models"
output_dir = os.path.join(os.path.abspath(""), "generatemodels", subfolder)
os.makedirs(output_dir, exist_ok=True)

# Duyệt qua các bảng và ghi mã model hoặc DTO ra tệp cho mỗi bảng
for table in tables:
    table_name = table[0]
    model_code = create_model(table_name, is_dto)
    
    # Ghi mã vào tệp .java trong thư mục "generatemodels/models" hoặc "generatemodels/dtos"
    file_path = os.path.join(output_dir, f"{table_name.capitalize()}{'Dto' if is_dto else ''}.java")
    with open(file_path, "w") as file:
        file.write(model_code)
    print(f"{'DTO' if is_dto else 'Model'} for table '{table_name}' has been written to '{file_path}'.")

# Đóng cursor và kết nối
cursor.close()
conn.close()
