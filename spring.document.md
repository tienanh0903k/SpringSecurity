my-spring-boot-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── myapp/
│   │   │               ├── MySpringBootApplication.java
│   │   │               ├── controller/
│   │   │               │   └── UserController.java
│   │   │               ├── service/
│   │   │               │   └── UserService.java
│   │   │               ├── repository/
│   │   │               │   └── UserRepository.java
│   │   │               ├── model/
│   │   │               │   └── User.java
│   │   │               ├── dto/
│   │   │               │   └── UserDto.java
│   │   │               ├── security/
│   │   │               │   ├── JwtTokenProvider.java
│   │   │               │   ├── SecurityConfig.java
│   │   │               └── exception/
│   │   │                   ├── CustomException.java
│   │   │                   └── GlobalExceptionHandler.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/
│   │   │   └── templates/
│   │   └── docker/
│   │       └── Dockerfile
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── myapp/
│   │                   └── UserControllerTests.java
│
├── .gitignore
├── pom.xml
└── README.md
