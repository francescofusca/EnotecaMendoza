
# Enoteca Mendoza 🍷






**Web Design University Project**

## Project Description

**Enoteca Mendoza** is a web-based **e-commerce application** dedicated to wine sales, developed for a well-known restaurant located in Cosenza, *Hostaria de Mendoza*, specifically in the town of Rende.

The project aims to deliver a complete, secure, and well-structured platform that allows users to browse a wine catalog, manage online purchases and orders, while administrators can fully control the system through a dedicated admin panel.

---

## Main Features

### Authentication and User Management

* User registration via email, password, and personal data
* Email uniqueness validation
* Login with registered credentials
* Two user roles: **customer** and **administrator**
* Role-based access control to application features

### Product Catalog and Search

* Main page displaying all wines with images and prices
* Search by wine name and filtering by category
* Wines organized by type (red, white, rosé, etc.)
* Product detail page with description, price, and available quantity
* Real-time display of stock availability

### Shopping Cart

* Add wines to the cart with selectable quantities
* Automatic calculation of the order total
* Ability to update quantities or remove items
* Continuous stock availability checks
* Cart persistence until user logout

### Payment and Order Management

* Virtual balance associated with each user account
* Balance top-up functionality
* Balance validation before order confirmation
* Generation of detailed order summaries
* Order status management (pending, confirmed, shipped)

### Administration Panel

* Add new wines to the catalog
* Edit product prices, descriptions, and images
* Update warehouse stock quantities
* View and manage all customer orders
* Access to sales statistics

---

## Transaction Management

The application uses database transactions to ensure **data consistency** during critical operations.

### Purchase Process

During a purchase, a single transaction handles:

1. User balance deduction
2. Stock quantity reduction
3. Order creation

If any step fails (e.g. insufficient balance), the entire transaction is rolled back to prevent inconsistencies such as unpaid orders or incorrect stock updates.

### Inventory Management

When administrators update product price and quantity simultaneously, transactions guarantee that either all changes are saved or none are applied.

### Balance Top-Up

Transactions ensure that balance recharges are correctly registered and safely persisted, avoiding data loss due to technical errors.

---

## Technologies Used

### Backend

* Spring Boot 2.7
* Spring Security – Authentication and authorization
* Spring Data JPA & Hibernate – ORM and database mapping
* @Transactional – Safe handling of complex operations
* MySQL 8.0 – Relational database
* JWT – Stateless authentication
* BigDecimal – Accurate monetary calculations

### Frontend

* Angular 14
* TypeScript
* NgRx – Application state management
* RxJS – Asynchronous communication handling
* Angular Router – Page navigation and route protection
* HTTP Interceptors – Automatic JWT token injection
* Bootstrap 5 – Responsive and modern UI design

### Frontend–Backend Communication

* REST APIs using JSON data exchange
* CORS configuration to enable communication between frontend (port 4200) and backend (port 8080)

---

## Database Design

* One user can place multiple orders
* One order can include multiple wines
* Each wine belongs to a category
* Logical deletion is used to preserve order history and data integrity

---

## Product Image Management

To efficiently populate the wine catalog, a **Python-based automation script** was developed:



https://github.com/user-attachments/assets/92840632-7332-496b-bf48-394985c3551f



* For each wine, the script performs a Google image search
* The first three images are automatically downloaded
* These images are then used as product photos within the application

This approach significantly reduced manual work and sped up the catalog creation process while ensuring consistent visual content.

---

## Project Status

Completed academic project developed for a university course in **Web Design / Web Engineering**.

---

Se in seguito vuoi aggiungere **istruzioni di avvio**, **screenshot**, o una **sezione di valutazione tecnica**, posso integrarle mantenendo lo stesso livello formale.
