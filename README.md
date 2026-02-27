# Distributor Management Web Application

## Overview
This README provides a comprehensive guide for the Distributor Management web application, which is designed to aid distributors in managing their operations efficiently. Built with cutting-edge technologies to ensure high performance, scalability, and security, this application serves as a reliable backbone for distributor management.

## Technologies Used
- **Spring Boot**: Version 3.5.4
- **Java**: Version 21
- **Spring Security**: JWT authentication for secure access
- **JPA**: Java Persistence API for data management
- **Databases Supported**: H2 (for development) and MySQL (for production)

## Features
- **User Authentication**: Secure user authentication powered by JWT enabling token-based security.
- **Data Persistence**: Utilizing JPA for effective data handling and transactions.
- **Database Support**: Seamless integration with both H2 and MySQL databases, allowing for flexibility in deployment.
- **RESTful API**: Provides a RESTful interface for easy integration with front-end applications or third-party services.

## Getting Started
### Prerequisites
- Java 21 installed on your local machine.
- Maven for dependency management and building the project.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/NoeliaPodmoguilny/distribuidora_backend.git
   cd distribuidora_backend
   ```
2. Install dependencies:
   ```bash
   mvn install
   ```
3. Configure application properties in `src/main/resources/application.properties` for database connection settings.

### Running the Application
To run the application, use the following command:
```bash
mvn spring-boot:run
```

## API Reference
Documentation for the API endpoints can be found in the `src/main/resources/docs` folder or generated using Swagger UI.

## Contributing
We welcome contributions from the community! Please adhere to the [contributing guidelines](CONTRIBUTING.md) if you wish to contribute.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For inquiries or support, please contact [Noelia Podmoguilny](mailto:your_email@example.com).