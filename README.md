# MemoMe

## Overview
MemoMe is a sophisticated Java-based web application designed for efficient note management. It enables users to seamlessly create, delete, and access their notes through a personalized dashboard. Developed using the robust Spring Boot framework, MemoMe integrates MyBatis for effective database interactions. It boasts a sturdy backend API paired with an intuitive frontend, making note management both simple and powerful.

## Features
- **User Registration and Authentication**: Secure system for user creation and login.
- **Note Creation and Deletion**: Effortlessly manage your notes within the application.
- **Asynchronous Note Retrieval**: Instantly access your notes on the dashboard without delays.
- **CSV Download Functionality**: Conveniently download your notes in CSV format.

## Prerequisites
- **Java Development Kit (JDK)**: Version 17 or higher required.
- **Maven**: Version 3.6 or higher.
- **MySQL Database**: For data storage and management.

## Installation
1. **Clone the Repository**:
   ```
   git clone [repository URL]
   ```

2. **Project Setup**:
   ```
   cd MemoMe
   ```

3. **Build with Maven**:
   ```
   mvn clean install
   ```

4. **Database Configuration**:
   - Establish a new database named `memome`.
   - Adjust the `application.properties` file to match your database details.

5. **Launch the Application**:
   ```
   mvn spring-boot:run
   ```
   (Alternatively, you can run MemoMeApplication.java)

## Usage
1. **User Registration**: Visit `/register` to set up a new user account.
2. **Login**: Go to `/login` and enter your credentials.
3. **Note Management**: Add, view, and delete notes via the dashboard.
4. **CSV Download**: Utilize the CSV download feature on the dashboard.

## API Endpoints
- **User Management**: `/register.do`, `/login.do`, `/logout.do`
- **Notes Management**: Asynchronous retrieval (`/dashboard/notesync`), creation (`/api/notes/create`), deletion (`/api/notes/delete/{noteId}`), and update (`/api/notes/update`).
- **File Management**: File upload (`/api/files/upload` - not yet implemented) and download (`/api/cdn/download/` - restricted to logged-in users).

## Dependencies
- Various Spring Boot Starters (Web, DevTools, Security Crypto)
- MyBatis Starters (Spring Boot Starter, Spring Boot Starter Test)
- MySQL Connector Java
- Lombok

## Contributing
We encourage contributions to MemoMe. Adherence to coding standards and comprehensive documentation is crucial for all contributions.

## License
MemoMe is under the MIT License, promoting open-source collaboration and sharing.
