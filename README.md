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

## Q&A (in german)
<details>
**Frage:**
<summary> Gegen was schützt ein Salt und wie unterscheidet sich dieser zu einem Pepper?</summary>

**Antwort:**

Ein **Salt** und ein **Pepper** sind beides Techniken, die in Verbindung mit Passwort-Hashing verwendet werden, um die Sicherheit von Passwörtern zu erhöhen. Sie dienen jedoch unterschiedlichen Zwecken und haben unterschiedliche Verwendungszwecke:

**Salt:**

- **Schutz vor Rainbow Tables:** Ein Salt (Salz) ist eine zufällige Zeichenfolge, die zu einem Passwort hinzugefügt wird, bevor es gehasht wird. Das Ergebnis des Hashing-Vorgangs enthält sowohl das ursprüngliche Passwort als auch das Salt. Dies verhindert effektiv, dass Angreifer vorberechnete Rainbow Tables verwenden, um Passwörter zu knacken.

- **Einzigartigkeit:** Für jeden Benutzer wird normalerweise ein eindeutiges Salt erstellt und mit seinem Passwort kombiniert. Dadurch wird sichergestellt, dass selbst Benutzer mit denselben Passwörtern unterschiedliche gehashte Werte haben, da das Salt unterschiedlich ist.

- **Speicherung:** Das Salt wird normalerweise zusammen mit dem gehashten Passwort in der Datenbank gespeichert.

**Pepper:**

- **Zusätzliche Sicherheitsschicht:** Ein Pepper ist ein zufälliger Wert, der dem Passwort vor dem Hashing hinzugefügt wird, ähnlich wie ein Salt. Der entscheidende Unterschied besteht darin, dass der Pepper nicht in der Datenbank gespeichert wird. Stattdessen wird er sicher an einem anderen Ort (z. B. in einer Konfigurationsdatei) aufbewahrt, der nicht direkt mit der Datenbank in Verbindung steht.

- **Schutz vor Datenbankkompromittierung:** Der Pepper bietet zusätzlichen Schutz, da ein Angreifer, der Zugriff auf die Datenbank erhält, nicht in der Lage ist, Passwörter zu knacken, ohne auch den Pepper zu kennen. Selbst wenn die Passwort-Hashes und Salts kompromittiert werden, ist der Pepper erforderlich, um die Passwörter zu rekonstruieren.

- **Erhöhte Sicherheit:** Der Pepper ist im Wesentlichen ein Geheimnis, das die Sicherheit des Passwort-Hashing-Verfahrens erhöht.

Zusammengefasst schützt ein Salt vor Rainbow-Tables und stellt sicher, dass gehashte Passwörter einzigartig sind, während ein Pepper eine zusätzliche Sicherheitsebene bietet, um selbst bei einem Datenbankkompromiss die Passwörter zu schützen. Die Verwendung von Salt und Pepper in Kombination kann dazu beitragen, die Sicherheit von Passwort-Hashing-Verfahren erheblich zu erhöhen.

</details>

<details>
**Frage:**
<summary> Was spricht dagegen, das JWT eines Users im localstorage abzuspeichern? Was wäre eine bessere Alternative?</summary>

**Antwort:**

Das Speichern des JWT (JSON Web Token) eines Benutzers im `localstorage` kann Sicherheitsprobleme aufwerfen. Hier sind einige Gründe, warum dies problematisch sein kann, und eine bessere Alternative:

**Probleme beim Speichern im localstorage:**

1. **Anfällig für XSS-Angriffe:** Wenn Ihre Anwendung anfällig für Cross-Site Scripting (XSS) ist, können Angreifer das `localstorage` ausnutzen, um Zugriff auf das JWT eines Benutzers zu erhalten.

2. **Kein Schutz vor CSRF-Angriffen:** Das `localstorage` wird standardmäßig nicht vor Cross-Site Request Forgery (CSRF)-Angriffen geschützt. Ein Angreifer kann eine Seite erstellen, die eine Anfrage an Ihre Anwendung sendet, ohne dass der Benutzer es merkt, da das JWT im `localstorage` gespeichert ist.

3. **Kein Ablaufzeitmanagement:** Das `localstorage` speichert Daten dauerhaft im Browser des Benutzers. Wenn das JWT keine Ablaufzeitverwaltung hat, könnte ein abgelaufenes JWT im `localstorage` verbleiben und zu Authentifizierungsfehlern führen.

**Bessere Alternative:**

Eine sicherere Alternative ist die Verwendung von HttpOnly-Cookies für die Speicherung des JWT. Hier sind die Vorteile:

1. **Sicherheit:** HttpOnly-Cookies können nicht über JavaScript zugegriffen werden, wodurch sie vor XSS-Angriffen geschützt sind.

2. **CSRF-Schutz:** Cookies sind standardmäßig vor CSRF-Angriffen geschützt, wenn Sie die richtigen Sicherheitsvorkehrungen treffen.

3. **Ablaufzeitmanagement:** Sie können Cookies mit einer Ablaufzeit versehen, um sicherzustellen, dass abgelaufene JWTs automatisch entfernt werden.

4. **Same-Site-Attribut:** Durch Festlegen des Same-Site-Attributs können Sie Cookies vor Cross-Site Request Forgery (CSRF)-Angriffen schützen.

Insgesamt ist die Verwendung von HttpOnly-Cookies eine sicherere und empfohlene Methode zur Speicherung von JWTs und anderen sensiblen Daten, die für die Authentifizierung verwendet werden.

---
