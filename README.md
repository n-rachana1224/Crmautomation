A CRM (Customer Relationship Management) application built using Spring Boot, JPA, MySQL, and Workflow Automation.

This system automates:
✔ Lead Status Progress
✔ Opportunity Management
✔ Quotes & Sales Orders
✔ Invoice Completion
✔ Support Ticket SLA Monitoring
✔ Email + SMS Notification automation

🚀 Tech Stack Used
Layer	Technology
Backend	Spring Boot, REST API
Database	MySQL
ORM	Hibernate (JPA)
Scheduler	Spring Scheduler
Email Service	Gmail SMTP
SMS Service	Console Simulation
Build Tool	Maven
Language	Java 17
📂 Project Modules

1️⃣ Lead Management
2️⃣ Opportunity Management
3️⃣ Quote Management
4️⃣ Sales Order Management
5️⃣ Invoice Management
6️⃣ Ticketing System with SLA Automation
7️⃣ Email Notifications
8️⃣ SMS Notifications (Simulated in Console)

⚙️ Features
🔹 Workflow Automation
Event	Automation
Lead → QUALIFIED	Auto send Email + SMS
Quote → ACCEPTED	Customer Notification
Invoice → PAID	Payment Confirmation Notification
Ticket → SLA Breach	Escalation Alert
🔹 Status Transition
Module	Status Workflow
Leads	NEW → CONTACTED → QUALIFIED → CONVERTED
Opportunities	QUALIFIED → PROPOSAL → NEGOTIATION → WON / LOST
Quotes	CREATED → SENT → ACCEPTED / REJECTED
Sales Order	CREATED → APPROVED → COMPLETED
Invoice	DRAFT → SENT → PAID / OVERDUE
Tickets	OPEN → IN_PROGRESS → RESOLVED → CLOSED
🛢️ Database Configuration

📌 In application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/crm
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update


📌 Create database before running:

CREATE DATABASE crm;

📬 Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=yourgmail@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


⚠ Must enable 2-step verification & create App Password in Gmail.

📲 SMS Notification

✔ Simulated in Spring Boot Console
(No payment or API required)

▶ How to Run the Backend

1️⃣ Install MySQL & create DB → crm
2️⃣ Open Backend in Eclipse / IntelliJ
3️⃣ Run AutomationApplication.java
4️⃣ Open API tester tools like:

✔ Talend API Tester
✔ Postman
✔ Browser (for GET requests)

🧪 API Testing Endpoints
📌 Leads
Action	Method	Endpoint
Create Lead	POST	/api/leads
Get All Leads	GET	/api/leads
Update Status	PUT	/api/leads/{id}/status?status=QUALIFIED
📌 Tickets
Action	Method	Endpoint
Create Ticket	POST	/api/tickets
Get All Tickets	GET	/api/tickets

⏱ SLA Scheduler runs every 1 minute & logs breach notifications.

📌 Example JSON Request
➤ Create Lead
{
  "leadName": "John Doe",
  "companyName": "ABC Corp",
  "email": "jd@gmail.com",
  "phone": "9876543210",
  "region": "North",
  "category": "IT"
}

👩‍💻 Developer

Rachana N
Java Full Stack Developer
CRM Automation Project

🏁 Project Outcome

✔ Demonstrates real business workflow
✔ Automated Notification System
✔ Enterprise-level CRM lifecycle
✔ Excellent for Final Year / Internship Project

