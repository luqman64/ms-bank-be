# Bank App

Application developed by Luqman Nul Hakim as part of the Maybank technical assessment.

### Features
- Update description field.
- Search fund transactions using customer id, account number or description.
- Quartz scheduler to insert records from dataSource.txt into db (runs every hour).
- List of records with pagination.

### Startup Guide
1) Clone repo and open project using any preferred IDE.
2) Create bank_db database in local MySQL server.
3) Import structure and dataset contained in SQL files (in bank_db.zip) into bank_db database.
4) Configure db connection in application.properties
5) Run application and open http://localhost:8080/bank-app in browser.

### Tools used
- Intellij IDEA Community Edition
- Java 17
- MySQL 8.0.41
