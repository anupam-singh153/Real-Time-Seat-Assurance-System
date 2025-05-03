# Real-Time Seat Assurance System
A web-based system to manage and optimize bus seat allocation for students and teachers, ensuring they get accurate seat availability updates based on real-time driver feedback.

# Project Overview
In many colleges, students rush to the bus ground after class because they are unsure if they will get a seat. This creates unnecessary stress, disrupts the last lecture, and leads to unfair seat holding (e.g., leaving bags to reserve seats).

# The Real-Time Seat Assurance System solves this problem by:
✅ Providing live seat availability feedback (Green, Orange, Red) from drivers.
✅ Allowing admins to reassign buses dynamically based on real-time demand.
✅ Giving students and teachers clear information about their bus routes, stops, and availability.
✅ Ensuring secure access using role-based authentication.

# Key Features
Live Driver Feedback
Drivers provide updates on seat availability using a simple traffic light system:

🟢 Green → Seats available

🟠 Orange → Full (one person per seat)

🔴 Red → Overcrowded (some standing)

Dynamic Bus Scheduling
Admins can reallocate unlinked buses to busy routes based on the feedback received.

# Admin Controls

Manage (Create, Update, Delete, Read) members (students, drivers).

Modify routes and stops as needed.

Assign buses to routes and monitor feedback.

# Student Dashboard

View bus routes, stops, and real-time availability.

Check bus locations inside the campus.

#Secure Login System
Role-based access for Admin, Driver, and Student users.

# Technology Stack
Backend → Spring Boot, REST APIs

Frontend → React.js, HTML, CSS

Database → MySQL

Authentication → JWT (or session-based, depending on your implementation)

Optional Enhancements → WebSockets (for real-time updates), Google Maps API (for live bus location)

# How It Works
Driver logs in → sends live feedback on passenger load.

Admin monitors feedback → reallocates buses if needed.

Student logs in → checks their assigned bus, route, stop, and seat availability.

System ensures everyone knows where to go, reducing rush and improving the commute experience.

