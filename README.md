# Student Expense Tracker

## 1. Project Overview
Student Expense Tracker is a command-line Java application for recording and reviewing everyday student expenses. It allows a user to add, view, search, delete, and analyse expense records.

## 2. Main Features
1. Add an expense
2. View all expenses
3. Search expenses by category, description, or date
4. Delete an expense by ID
5. View total, average, highest, and category-wise expenses
6. Save records to a local text file
7. Load saved records when the application starts
8. Validate user input and handle common input/file errors

## 3. Technologies Used
- Java
- Java Collections (`ArrayList`, `HashMap`)
- File Handling (`FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`)
- Exception Handling
- Command Line / Terminal
- Git and GitHub

## 4. Project Structure

```text
StudentExpenseTracker/
├── src/
│   ├── Main.java
│   ├── Expense.java
│   ├── ExpenseManager.java
│   ├── FileManager.java
│   ├── ExpenseReport.java
│   ├── InputValidator.java
│   ├── Menu.java
│   └── TestExpenseManager.java
├── data/
│   └── expenses.txt
├── tests/
├── README.md
├── statement.md
└── .gitignore
```

## 5. Requirements
Install a JDK that provides `javac` and `java`.

Check the installation:

```text
java -version
javac -version
```

## 6. How to Run

Open a terminal in the project root folder.

### Compile
Windows PowerShell / Command Prompt:

```text
javac -d out src\*.java
```

### Run the application

```text
java -cp out Main
```

### Run the basic tests

```text
java -cp out TestExpenseManager
```

Expected test message:

```text
All basic ExpenseManager tests passed.
```

## 7. How Data Is Stored
The application stores expense records in:

```text
data/expenses.txt
```

Each record uses the following format:

```text
id|amount|category|description|date
```

The program loads existing records at startup and saves changes when an expense is added/deleted or when the user exits.

## 8. Basic User Workflow

```text
Start
  |
Load saved expenses
  |
Display menu
  |
Choose operation
  |
Add / View / Search / Delete / Report
  |
Return to menu
  |
Exit -> Save data -> End
```

## 9. Testing
The `TestExpenseManager.java` class performs basic checks for:
- Adding expenses
- Searching expenses
- Deleting expenses
- Checking the resulting collection state

For the final submission, additional manual validation should be performed using the menu and documented in the project report.

## 10. Notes for Evaluation
The application is intentionally command-line based so that it can be compiled and executed from a terminal without a GUI-based setup.
