# GPC Backend

Welcome to the API and Backend Implementation readme for the GPC System! This document provides comprehensive information about the backend architecture and implementation details of the GPC. By leveraging Java and the Spring Boot framework, the backend offers a powerful and scalable solution for optimizing parking lot operations.

To fully utilize the LotMaster System, please make sure you have set up and run the corresponding [GPC Frontend](https://github.com/bouchraakl/GPC) repository. The frontend repository contains the user interface and interacts with the backend API to provide a seamless parking management experience.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [Project Detailed Documentation](#project-detailed-documentation)
- - [Contributors](#contributors)

## Features

- **Hospital Item Registration**: Rotary Club members can add detailed information about donated hospital items, including descriptions, features, conditions, donation dates, and other relevant details.
- **Asset Tracking**: The system allows for the tracking of registered assets. Members can view detailed information for each asset, such as its category, condition, status, and description. This facilitates the control and organization of hospital items.
- **Search and Filtering**: The GPC system offers search and filtering capabilities that allow Rotary Club members to locate specific assets based on criteria such as condition, status, asset ID, and category name. This helps in quickly finding desired items.
- **Inventory Management**: The system enables members to track the available stock of hospital items, including current quantity, entry and exit dates, and storage locations.
Reports: GPC generates reports to assist in Rotary management. This includes information such as a complete history of all donations made, among other relevant data.
- **Notifications and Alerts**: The system can send automatic notifications to Rotary Club members regarding new requests for hospital items, status updates, and other important information related to donations and inventory management.


## Technologies Used

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Getting Started

Follow the instructions below to get the parking management system backend up and running on your local machine.

### Prerequisites

- Java Development Kit (JDK) 11 or above
- IntelliJ IDEA (or any other Java IDE of your choice)
- PostgreSQL (local installation or remote connection)
- Postman (for testing the API)

### Installation

1. Clone this repository to your local machine using:

   ```shell
   git clone https://github.com/bouchraakl/ParkingManagerBackend.git
   
2. Open the project in your Java IDE (e.g., IntelliJ IDEA).

### Configuration

1. Configure the database connection properties in the `application.properties` file. Provide the necessary details such as database URL, username, and password.

2. If required, modify any other application-specific configurations in the `application.properties` file.

### Running the Application

1. Build the project to download dependencies and compile the code.
2. Run the application using your IDE's run or debug configuration.
3. Once the application is running, you can test the API endpoints using Postman or any other API testing tool.

## Project Detailed Documentation
You can find the detailed project documentation on our wiki : 

## Contributors
| Role             |Name   | E-mail   |
| ------------------ | ------ | ------- |
| Developer        | Bouchra Akl | [bushraakl1234@gmail.com](mailto:bushraakl1234@gmail.com) |
| Developer | Gustavo Piegat | [gustavopiegat2004@gmail.com](mailto:gustavopiegat2004@gmail.com) |
| Mentor          | Giovanni Rizzato | [giovanirizzato@gmail.com](mailto:giovanirizzato@gmail.com) |
| Developer      | Jean Moschen | [jeanfelipe0610@gmail.com](mailto:jeanfelipe0610@gmail.com) |
| Scrum Master          | Vinicius Gabriel Aquino | [comercial.viniciusgabriel@gmail.com](mailto:comercial.viniciusgabriel@gmail.com) |





  
