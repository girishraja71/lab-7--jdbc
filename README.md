# Lab 7 – Database Connection with JDBC in Java Swing

**Course:** INFO 5100 – Application Engineering & Development
**Author:** Girish Raja Thiyagarajan

A Java Swing CRUD application that stores and retrieves user profiles from a local **MySQL** database using **JDBC**. The data is shown in a **JTable**, and the selected row can be edited, updated or deleted.

## Features

| Operation | How it works |
|---|---|
| **Create** | Form screen validates the input and inserts a new user (`INSERT`) |
| **Read** | View screen loads all users from the database into a JTable (`SELECT`) |
| **Update** | Select a row → **Edit** unlocks the fields → **Update** saves the changes (`UPDATE`) |
| **Delete** | Select a row → **Delete** → confirm (`DELETE`) |

Extra features:
- Input validation (required fields, name, phone `123-456-7890`, email format, age 1–120)
- Duplicate email check (UNIQUE column, friendly error message)
- Fields are disabled until **Edit** is clicked; **Edit** turns into **Cancel** to undo changes
- Date of birth with Day / Month / Year drop-downs; age is filled in automatically
- Photo upload with preview, plus a details pop-up with the photo (double-click a row)
- Screens built with the NetBeans GUI Builder (Design tab / `.form` files)

## Technologies

- Java (Swing) – Apache NetBeans
- MySQL 8 (running in Docker)
- JDBC driver: MySQL Connector/J (`com.mysql:mysql-connector-j`, added through Maven)

## Project structure

```
src/main/java/
├── model/User.java                  – user data class
├── utility/DatabaseConnector.java   – JDBC connection + CRUD queries
├── utility/InputValidator.java      – input validation
└── ui/
    ├── MainJFrame.java              – main window with Form / View buttons
    ├── FormJPanel.java              – Create screen
    ├── ViewJPanel.java              – JTable + Edit / Update / Delete
    └── UserFieldsPanel.java         – shared input fields
DatabaseQueries.sql                  – creates the database and table
screenshots/                         – screenshots of the running app
```

## How to run

1. Start MySQL (e.g. the Docker container) on `localhost:3306`.
2. Run `DatabaseQueries.sql` in MySQL Workbench to create the `lab7db` database and `users` table.
3. If needed, change `USERNAME` / `PASSWORD` in `utility/DatabaseConnector.java`.
4. Open the project in NetBeans → **Clean and Build** → **Run** (main class: `ui.MainJFrame`).

## Screenshots

### Create user
![Create user](screenshots/01_create_user.png)

### Update user
![Update user](screenshots/02_update_user.png)

### View users (JTable)
![View table](screenshots/03_view_table.png)

### Delete user
![Delete confirm](screenshots/04_delete_confirm.png)

### Duplicate email check
![Duplicate email](screenshots/05_duplicate_email.png)

### Data in MySQL Workbench
![Workbench rows](screenshots/06_workbench_rows.png)
