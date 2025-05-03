
# Real-Time Seat Assurance System

A web-based system to manage and optimize college bus seat allocation, ensuring students and teachers get real-time seat availability updates through driver feedback.

---

## Project Setup & Run Instructions

### 1️⃣ Clone the Repository
```bash
git clone <your-repo-link>
cd newSeatAssuranceSystem
```

### 2️⃣ Backend Setup (Spring Boot)
- Navigate to the backend directory.
- Update `application.properties` with your local MySQL database credentials.
- Run the Spring Boot application:
```bash
./mvnw spring-boot:run
```

### 3️⃣ Frontend Setup (React)
- Navigate to the frontend directory.
- Install dependencies:
```bash
npm install
```
- Start the React app:
```bash
npm start
```

---

## Dependencies & Configurations

✅ **Backend**
- Java 17+
- Spring Boot
- MySQL Database
- Maven

✅ **Frontend**
- Node.js
- React.js
- Axios (for API calls)

✅ **Authentication**
- JWT (JSON Web Token) for secure role-based access

---

## Project Features

- Live seat availability updates from drivers (Green, Orange, Red status).
- Dynamic bus scheduling based on real-time demand.
- Admin panel for managing routes, buses, members, and feedback.
- Student dashboard to check bus routes, stops, and seat availability.
- Secure login system for Admin, Driver, and Student roles.

---

## Screenshots / Demo

### Admin Dashboard
![Admin Dashboard Screenshot](screenshots/admin_dashboard.png)

### Driver Feedback Panel
![Driver Panel Screenshot](screenshots/driver_panel.png)

### Student Bus View
![Student Dashboard Screenshot](screenshots/student_dashboard.png)

*(If you have actual screenshots, place them in a `/screenshots` folder and replace the image file names above.)*

---

## Important Notes
- Make sure your local MySQL database is running and matches the schema provided in the project.
- If using external APIs or third-party services, ensure they are properly configured (API keys, if any).
- Keep your GitHub repository public or **accessible** during the evaluation period.

---

✅ **GitHub Repository Link (to submit):** [Insert your GitHub link here]

