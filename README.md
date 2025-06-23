⚙️ 1. Dự án nhỏ – Structure đơn giản (Layered Architecture)

src/
└── main/
    ├── java/
    │   └── com/example/app/
    │       ├── common/                 # Các helper, constant, exception, utils
    │       │   ├── exception/
    │       │   ├── dto/
    │       │   ├── mapper/
    │       │   └── util/
    │
    │       ├── config/                 # Spring config (CORS, Swagger, Security)
    │
    │       ├── controller/             # REST Controller
    │       │   ├── UserController.java
    │       │   └── ProductController.java
    │
    │       ├── service/                # Business logic
    │       │   ├── UserService.java
    │       │   └── impl/
    │       │       └── UserServiceImpl.java
    │
    │       ├── repository/             # Spring Data JPA interfaces
    │       │   └── UserRepository.java
    │
    │       ├── entity/ or model/       # Entity JPA
    │       │   └── User.java
    │
    │       ├── dto/                    # Data Transfer Objects
    │       │   └── UserDto.java
    │
    │       ├── mapper/                 # MapStruct or manual mappers
    │       │   └── UserMapper.java
    │
    │       └── AppApplication.java     # Main Spring Boot class
    │
    └── resources/
        ├── application.yml             # Cấu hình chung
        ├── application-dev.yml         # Cấu hình dev
        ├── application-prod.yml        # Cấu hình production
        ├── static/                     # (frontend nếu dùng thymeleaf)
        └── templates/                  # (nếu dùng thymeleaf)

test/
└── java/
    └── com/example/app/
        ├── controller/                 # Test controller (MockMvc)
        ├── service/                    # Unit test cho service
        └── integration/                # Integration test (test DB, API)

