
## Giới thiệu

Dự án này là một ứng dụng Spring Boot nhỏ, được xây dựng theo kiến trúc Layered Architecture. Ứng dụng sử dụng Spring Data JPA để tương tác với cơ sở dữ liệu và cung cấp API REST cho người dùng.

## Cấu trúc Dự Án

Cấu trúc thư mục của dự án như sau:

```
src/
└── main/
    ├── java/
    │   └── com/example/app/
    │       ├── common/                 # Các helper, constant, exception, utils
    │       │   ├── exception/
    │       │   ├── dto/
    │       │   ├── mapper/
    │       │   └── util/
    │       ├── config/                 # Cấu hình Spring (CORS, Swagger, Security)
    │       ├── controller/             # REST Controller
    │       │   ├── UserController.java
    │       │   └── ProductController.java
    │       ├── service/                # Logic nghiệp vụ
    │       │   ├── UserService.java
    │       │   └── impl/
    │       │       └── UserServiceImpl.java
    │       ├── repository/             # Các interface Spring Data JPA
    │       │   └── UserRepository.java
    │       ├── entity/ or model/       # Entity JPA
    │       │   └── User.java
    │       ├── dto/                    # Data Transfer Objects
    │       │   └── UserDto.java
    │       ├── mapper/                 # MapStruct hoặc các mapper thủ công
    │       │   └── UserMapper.java
    │       └── AppApplication.java     # Lớp chính của Spring Boot
    └── resources/
        ├── application.yml             # Cấu hình chung
        ├── application-dev.yml         # Cấu hình môi trường phát triển
        ├── application-prod.yml        # Cấu hình môi trường sản xuất
        ├── static/                     # (frontend nếu dùng thymeleaf)
        └── templates/                  # (nếu dùng thymeleaf)

test/
└── java/
    └── com/example/app/
        ├── controller/                 # Kiểm tra controller (MockMvc)
        ├── service/                    # Kiểm tra đơn vị cho service
        └── integration/                # Kiểm tra tích hợp (kiểm tra DB, API)
```
