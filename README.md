

# 🏨 Hotel Management System

[![Language](https://img.shields.io/badge/Language-Java-blue)](https://www.java.com)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/DoniaEid/HotelManagement?style=social)](https://github.com/DoniaEid/HotelManagement)

---

## 📌 Overview
The **Hotel Management System** is a user-friendly application designed to efficiently manage hotel operations.  
It is built following **Object-Oriented Programming (OOP) principles** like **Encapsulation, Inheritance, and Polymorphism** to ensure clean, maintainable code.  

The system allows users to:  
- Manage customer information (Customer Management)  
- Handle room bookings and reservations (Room & Reservation Management)  
- Track additional services such as food, amenities, and other hotel services (Service Management)  
- Calculate bills and total costs automatically  
- Generate simple reports for hotel management

---

## 🛠️ Features
- Add, edit, and delete customer information  
- Manage room availability and bookings  
- Assign additional services to each room  
- Automatic calculation of total bills for each stay  
- Search, filter, and update customer or booking data  
- Data persistence using **File I/O**  
- Implemented using **Java Swing GUI** for a friendly interface  
- Full implementation of **OOP concepts**:
  - **Encapsulation:** data hidden in classes, accessed via getters/setters  
  - **Inheritance:** common functionality shared across related classes  
  - **Polymorphism:** method overriding and overloading for flexible operations  
- Easy to extend for future features like database integration  

---

## 💻 Technologies & Tools
- **Java**  
- **Java Swing** (GUI)  
- **File I/O** (data storage)  
- **OOP Concepts**: Encapsulation, Inheritance, Polymorphism  
- **Git & GitHub**

---

## 📂 Project Structure

```text
HotelManagement/
├── src/
│   ├── models/               # Core classes representing entities
│   │   ├── Customer.java      # Customer data (name, age, contact, etc.)
│   │   ├── Room.java          # Hotel rooms (number, type, price, availability)
│   │   └── Service.java       # Additional services (service name, price)
│   │
│   ├── managers/             # Handles operations on data
│   │   ├── CustomerManager.java # Add, edit, delete, search customers
│   │   ├── RoomManager.java     # Manage rooms, availability, reservations
│   │   └── ServiceManager.java  # Manage additional services
│   │
│   └── gui/                  # Java Swing GUI classes
│       ├── MainFrame.java     # Main application window
│       ├── CustomerPanel.java # GUI for managing customers
│       ├── RoomPanel.java     # GUI for managing rooms
│       └── ServicePanel.java  # GUI for managing services
│
├── data/                     # Persistent storage
│   ├── customer.txt           # Customer information
│   ├── room.txt               # Room information
│   └── service.txt            # Service information
│
├── README.md                 # Project documentation
└── LICENSE                   # License file
```

## 🎬 Demo Video
<p align="center">
  <a href="https://drive.google.com/file/d/1EUTAEjolvTYcNxjnlOo9hTsaPZVLf1yk/view?usp=sharing" target="_blank">
    Watch Demo Video
  </a>
</p>
