# Smart Inventory Management System

## overview
The Smart Inventory Management System (SIMS) will streamline inventory tracking and restocking for pantry items, allowing users to efficiently manage stock levels through a web-based application.

## Features

### User Features
- **User Registration and Login:** There is registration and login feature for users.
- **Buy Item:** user can buy inventory item by providing the id of item
- **Dashboard Content:** user can view full inventory items.

### Admin Features
- **Admin Dashboard:** Admin have different features to perform on inventory.
- **Manage inventory:** Admin can perform CRUD operation on the inventory items.
- **Alert:** Admin will get alert of low stock and expiry dates.
- **Reports:** Admin will have acess to report of how many items are sold and admin can also download the report in csv form.

## Technologies Used

### Frontend

- **HTML, CSS, JavaScript** - Core frontend technologies.

### Backend

- **Java Spring Boot** - Backend framework for handling inventory logic.
- **postgreSQL** - Database to store users, inventory items and stock movement.

## folder structure
Capstone1-SIMS/
├── src/
│   ├── main/
│   │   ├── java/com/inven/                  # Backend code
│   │   │   ├── InvenApplication.java       # Main application class
│   │   │   ├── controller/                 # REST controllers
│   │   │   ├── service/                    # Business logic
│   │   │   ├── repository/                 # Database access
│   │   │   └── model/                      # Entity classes
│   │   ├── resources/
│   │   │   ├── static/                     # Static files (CSS, JS, images)
│   │   │   ├── templates/                  # Thymeleaf templates
│   │   │   └── application.properties      # Configuration
│   └── test/                               # Unit and integration tests
├── pom.xml                                 # Maven configuration
└── README.md                               # Project documentation

## How to Run

### Prerequisites

- **Java JDK 11+**
- **postgreSQL Database**
- **Spring Boot**

### Installation

1. **Clone the repository:**

```bash
git clone https://github.com/disha0509/Capstone1-SIMS
```

2. **Navigate to the project directory:**

```bash
cd Capstone1-SIMS
```

3. **Configure Database:**

- Open `application.properties` and set your database credentials.

4. **Run the backend:**

```bash
mvn spring-boot:run
```

## Contact
For enquiry, contact:

- **Email:** [bundeladisha05@gmail.com]

```
THANK YOU!!


