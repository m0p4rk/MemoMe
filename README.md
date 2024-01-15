### MemoMe

## Overview
MemoMe is a Java-based web application for note management. Users can create, delete, and view notes in a personalized dashboard. This application is built using Spring Boot, integrating MyBatis for database interaction, and offers a robust backend API with a simple yet effective frontend interface.

## Features
- User Registration and Authentication
- Note Creation and Deletion
- Asynchronous Note Retrieval for Dashboard
- **File Upload and Download Functionality**

## Prerequisites
- JDK 17 or higher
- Maven 3.6 or higher
- MySQL Database

## Installation
1. Clone the repository:
   ```
   git clone [repository URL]
   ```

2. Navigate to the project directory:
   ```
   cd MemoMe
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Set up the MySQL database:
   - Create a new database named `memome`.
   - Update the `application.properties` file with your database credentials.

5. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage
1. **Register a User**: Access `/register` to create a new user account.
2. **Login**: Navigate to `/login` and enter your credentials to log in.
3. **Save Note**: Add new notes from the dashboard.
4. **View Notes**: View your notes asynchronously in the dashboard.
5. **Delete Notes**: Delete notes from the dashboard.
6. **File Upload and Download**: Use the file upload and download features from the dashboard.

## API Endpoints
- POST `/register.do` - Register a new user.
- POST `/login.do` - Authenticate a user.
- POST `/logout.do` - Logout a user.
- GET `/dashboard/notesync` - Retrieve notes asynchronously for the logged-in user.
- POST `/api/notes/create` - Create a new note.
- DELETE `/api/notes/delete/{noteId}` - Delete a specific note.
- POST `/api/files/upload` - Upload a file.
- GET `/api/files/download/{fileName}` - Download a file.

## Dependencies
- Spring Boot Starter Web
- MyBatis Spring Boot Starter
- Spring Boot DevTools
- MySQL Connector Java
- Spring Boot Starter Test
- MyBatis Spring Boot Starter Test
- Lombok
- Spring Security Crypto

## Contributing
Contributions to the MemoMe project are welcome. Please ensure to follow the coding standards and write clean, well-documented code.

## License
MIT License
