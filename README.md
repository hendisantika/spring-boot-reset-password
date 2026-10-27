# Spring Boot Reset Password Demo

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-reset-password/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-reset-password/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen?logo=springboot)
[![Dependabot](https://img.shields.io/badge/Dependabot-enabled-blue?logo=dependabot)](.github/dependabot.yml)

A Spring Boot application demonstrating a complete password reset workflow with email notification functionality. This
application showcases user authentication, password management, and email-based password recovery.

## Features

- User Registration (Sign Up)
- User Authentication (Login)
- Password Reset via Email
- H2 In-Memory Database
- RESTful API with Spring Data REST
- Thymeleaf Templates for UI
- SHA-256 Password Encryption
- Email Notification System
- Angular.js Frontend Integration

## Technologies Used

- **Spring Boot**: 4.1.2
- **Java**: 25 (LTS)
- **Database**: H2 (file-based)
- **ORM**: Hibernate/JPA
- **Template Engine**: Thymeleaf
- **Frontend**: Angular.js 1.0.8, Bootstrap
- **Build Tool**: Maven
- **Security**: SHA-256 encryption for passwords

## Prerequisites

- JDK 25 or higher (e.g. Eclipse Temurin 25)
- Maven 3.9+ (or use the included Maven wrapper)

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/hendisantika/spring-boot-reset-password.git
   cd spring-boot-reset-password
   ```

2. **Build the project**
   ```bash
   ./mvnw clean package
   ```

3. **Configure Email Settings** (Optional)

   Update `src/main/resources/email.properties` with your email credentials:
   ```properties
   emailUsername=your-email@gmail.com
   emailPassword=your-app-password
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   The application will start on `http://localhost:8080`

## Application Configuration

Key configuration in `src/main/resources/application.properties`:

```properties
server.port=8080
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.datasource.url=jdbc:h2:file:~/spring-boot-reset-password
spring.datasource.username=naruto
spring.datasource.password=naruto
```

## Available Endpoints

### Web Pages

- `/signup.html` - User registration page
- `/login.html` - User login page
- `/index.html` - Main application page (requires authentication)
- `/reset.html` - Password reset request page

### REST API

- `GET /persons` - List all persons (Spring Data REST endpoint)
- `POST /persons` - Create a new person
- Access H2 Console: `http://localhost:8080/h2`

### H2 Database Console

Access the H2 database console at: `http://localhost:8080/h2`

Connection details:

- **JDBC URL**: `jdbc:h2:file:~/spring-boot-reset-password`
- **Username**: `naruto`
- **Password**: `naruto`

## Project Structure

```
src/main/java/com/hendisantika/
├── config/
│   ├── ResetPassDemoFilter.java        # Security filter for route protection
│   └── WebMvcConfiguration.java        # Spring MVC configuration
├── dto/
│   ├── Message.java                    # Message DTO
│   └── ResetPasswordRequest.java       # Password reset request DTO
├── entity/
│   └── Person.java                     # User entity
├── repository/
│   └── PersonRepository.java           # JPA repository
├── service/
│   ├── PersonService.java              # Service interface
│   └── PersonServiceImpl.java          # Service implementation
├── util/
│   └── ResetPasswordDemoUtil.java      # Utility methods (encryption, validation)
└── SpringBootResetPassword2Application.java
```

## Key Features Implementation

### Password Encryption

Passwords are encrypted using SHA-256 algorithm before storage:

```java
public static String encryptSHY2(String password) {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(password.getBytes());
    byte[] byteData = md.digest();
    // Convert to hex string
    ...
}
```

### Email Validation

Email addresses are validated using regex pattern:

```java
Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
        Pattern.CASE_INSENSITIVE);
```

### Authentication Filter

A custom servlet filter handles authentication and redirects:

- Unauthenticated users → redirect to signup page
- Authenticated users → access to protected resources

## Development

### Running Tests

```bash
./mvnw test
```

### Building for Production

```bash
./mvnw clean package -DskipTests
java -jar target/spring-boot-reset-password2-0.0.1-SNAPSHOT.jar
```

## Continuous Integration

GitHub Actions ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) runs on every push and pull request to `main`
(and can be triggered manually):

- **build** - sets up Temurin JDK 25 with Maven caching and runs `mvn -B package` (compile + tests).
- **dependency-submission** - on pushes to `main` only, uploads the Maven dependency graph to GitHub so Dependabot alerts
  are more accurate. It is skipped on pull requests because their tokens are read-only.

The workflow uses least-privilege `contents: read` permissions by default, cancels outdated runs on the same branch and
has a 30-minute timeout.

## Dependency Management

[Dependabot](.github/dependabot.yml) checks for updates every day at 05:00 (Asia/Jakarta):

- **Maven** - updates are grouped (`spring-boot`, `testing`, `maven-plugins`, `minor-and-patch`) to reduce PR noise,
  committed as `build(deps): ...` and labeled `dependencies` + `java`.
- **GitHub Actions** - all action updates are grouped into a single PR, committed as `ci(deps): ...` and labeled
  `dependencies` + `github_actions`.

## Fixed Issues

This version includes fixes for:

1. **Lombok Configuration**: Added proper annotation processor configuration for Lombok
2. **Spring Data REST Path**: Fixed repository path configuration to use single path segment
3. **Build Compilation**: Resolved compilation errors related to Lombok annotation processing
4. **JDK 25**: Upgraded the project and CI to Java 25 (LTS)
5. **CI on Pull Requests**: Dependency graph submission no longer fails Dependabot / pull request builds

## Database Schema

The application uses a simple `PERSON` table with the following structure:

- `id` (Primary Key, Auto-generated)
- `email` (String)
- `password` (String, SHA-256 encrypted)

## Notes

- The H2 database file is stored in your home directory: `~/spring-boot-reset-password.mv.db`
- Password reset requires email configuration to be properly set up
- All passwords are stored as SHA-256 hashes
- The application uses session-based authentication

## Future Enhancements

- Implement JWT-based authentication
- Add password strength validation
- Implement password reset token expiration
- Add unit and integration tests
- Upgrade to modern frontend framework (React/Vue/Angular)
- Add Spring Security for better security management
- Implement remember-me functionality

## Contributing

Feel free to submit issues and enhancement requests!

## Author

- **Hendi Santika**
- Email: hendisantika@gmail.com
- Telegram: @hendisantika34

## License

This project is created for educational purposes.
