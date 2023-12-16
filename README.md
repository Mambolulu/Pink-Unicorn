---

# Mei Leaf - Tea Management and Sales System

This project implements a comprehensive backend system for Mei Leaf, a company specializing in various tea types and accessories, with a focus on integrating databases into web applications.

## Description

The system manages products, users, and purchases, featuring functionalities like customer registration, product listing, and purchase processing. It's designed for efficient operation and user engagement through a point and ranking system.

## Getting Started

### Dependencies

- Java JDK 11 or higher
- Spring Boot 2.x
- PostgreSQL for data persistence
- Docker for containerization
- Prometheus and Grafana for monitoring

### Installing and Running

- Clone the repository.
- Navigate to the root folder of the project.
- To start the application and all related services using Docker Compose, open a terminal in the project root and run:

  ```bash
  docker-compose up --force-recreate
  ```

This command will set up and launch all necessary components, including the Spring Boot application and the PostgreSQL database, as defined in your `docker-compose.yaml` file.

## Features

- **Customer Registration**: Register with roles and permissions.
- **Product Management**: List and manage various tea types.
- **Purchase Processing**: Implement discount and ranking system.
- **Statistics and Reporting**: Admin features for sales and product popularity analysis.
- **Order History**: View and manage customer purchase history.
- **Monitoring**: Use Prometheus and Grafana for application monitoring.
- **Testing**: Comprehensive testing across various application layers.

## Documentation

Detailed documentation on each class and module is available in the `docs` folder.

## Authors

- [Christian Vahamonde Gallego](https://github.com/Mambolulu)
- [Aaron Olivieri](https://github.com/aOlivieri94)
- [Valentin Lenz](https://github.com/VLenzers)
- [Elia Schudel](https://github.com/EliaSchudel)

## Version History

- 0.1
    - Initial Release

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.

## Acknowledgments

- Special thanks to the Spring Boot framework and Docker community.
- Inspired by the real-world tea company, Mei Leaf.

---