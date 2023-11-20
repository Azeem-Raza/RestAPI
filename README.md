# Project Title: RESTful Web Service with Database Integration

## Overview

This project implements a RESTful web service that connects webpages to a database. 
It supports CRUD (Create, Read, Update, Delete) operations using HTTP methods such as GET, POST, and DELETE. 
The project is built using Java and a MariaDB database.

## Components

### 1.  Students.java`

This is the main class that initializes and runs the web service.

### 2. `StudentDAO.java`

Handles database operations such as selecting all students, selecting a student by ID, inserting a new student, and deleting a student.

### 3. `StudentResource.java`

Implements the RESTful resource for students, handling HTTP requests such as GET, POST, and DELETE.
