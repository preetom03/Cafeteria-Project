# RUET Cafeteria Order Automation System 🍔☕

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-OpenJFX-blue?style=for-the-badge)
![XML](https://img.shields.io/badge/Database-XML-success?style=for-the-badge)
![SSLCommerz](https://img.shields.io/badge/Payment-SSLCommerz-red?style=for-the-badge)

## 📖 About the Project
This is a desktop-based **Cafeteria Order Automation System** developed as part of our academic course (CSE 2100). The system is designed to streamline the food ordering process for university students and staff. It provides a user-friendly graphical interface to browse the menu, place orders, and make secure simulated payments, completely replacing manual paper-based token systems.

## ✨ Key Features
* **Interactive UI:** Intuitive dashboard for users built with JavaFX and FXML (`BorderPane` layout).
* **Dynamic Menu:** Browse items categorized by Breakfast, Lunch, Fast Food, and Beverages.
* **Real-time Cart Management:** Automatically calculates quantities, sub-totals, and grand totals.
* **Serverless Data Storage:** Utilizes local XML files (`menu.xml`, `orders.xml`) for lightweight, fast, and structured data persistence without needing an external RDBMS.
* **Integrated Payment Gateway:** Simulates real-world digital transactions using the **SSLCommerz Sandbox API**, providing both Online and Offline payment handling.

## 🛠️ Technology Stack
* **Core Logic:** Java
* **Frontend / GUI:** JavaFX, FXML, CSS (Custom styling)
* **Database / Storage:** XML (Extensible Markup Language)
* **Payment Integration:** SSLCommerz API (Sandbox)

## 🚀 How to Run the Project
1.  **Prerequisites:** Ensure you have the **Java Development Kit (JDK)** and **JavaFX SDK** installed on your system.
2.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/preetom03/Cafeteria-Project.git](https://github.com/preetom03/Cafeteria-Project.git)
    ```
3.  **Open the Project:** Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
4.  **Run the App:** Execute the `Main.java` file (or the class containing your `start()` method) to launch the application.
5.  **Payment Testing:** Select the "Online" payment option at checkout to see the SSLCommerz dummy gateway in action.

## 👥 Contributors
This project was collaboratively developed by our team:
* **Shahi Raja Lashkor (2303057):** API Integration Lead & Technical Writer (SSLCommerz Integration, Project Documentation).
* **Md. Sarour Jahan (2303003):** Backend Developer (Core logic).
* **Preetom Barmon (2303025):** Project Lead / UI/UX Designer (JavaFX GUI, FXML layout).