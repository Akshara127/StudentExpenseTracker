import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ExpenseManager manager = new ExpenseManager();

        FileManager.loadExpenses(manager);

        boolean running = true;

        System.out.println("=================================");
        System.out.println("     STUDENT EXPENSE TRACKER");
        System.out.println("=================================");

        while (running) {

            Menu.showMenu();

            int choice = InputValidator.readMenuChoice(
                    scanner,
                    "Enter your choice: ",
                    1,
                    7
            );

            switch (choice) {

                case 1:
                    System.out.println("\n--- Add Expense ---");

                    double amount = InputValidator.readPositiveAmount(
                            scanner,
                            "Enter amount: "
                    );

                    String category = InputValidator.readNonEmpty(
                            scanner,
                            "Enter category: "
                    );

                    String description = InputValidator.readNonEmpty(
                            scanner,
                            "Enter description: "
                    );

                    String date = InputValidator.readNonEmpty(
                            scanner,
                            "Enter date (DD-MM-YYYY): "
                    );

                    int id = manager.getExpenses().size() + 1;

                    Expense expense = new Expense(
                            id,
                            amount,
                            category,
                            description,
                            date
                    );

                    manager.addExpense(expense);

                    FileManager.saveExpenses(manager);

                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- All Expenses ---");

                    if (manager.getExpenses().isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {
                        for (Expense e : manager.getExpenses()) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- Search Expense ---");

                    String keyword = InputValidator.readNonEmpty(
                            scanner,
                            "Enter category, description or date: "
                    );

                    List<Expense> results = manager.search(keyword);

                    if (results.isEmpty()) {
                        System.out.println("No matching expenses found.");
                    } else {
                        for (Expense e : results) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- Delete Expense ---");

                    int deleteId = InputValidator.readPositiveInteger(
                            scanner,
                            "Enter expense ID: "
                    );

                    if (manager.deleteExpense(deleteId)) {
                        FileManager.saveExpenses(manager);
                        System.out.println("Expense deleted successfully.");
                    } else {
                        System.out.println("Expense ID not found.");
                    }
                    break;

                case 5:
                    ExpenseReport.printReport(manager);
                    break;

                case 6:
                    System.out.println("\n--- Budget Check ---");

                    double budget = InputValidator.readPositiveAmount(
                            scanner,
                            "Enter your monthly budget: "
                    );

                    manager.checkBudget(budget);
                    break;

                case 7:
                    FileManager.saveExpenses(manager);
                    System.out.println("Thank you for using Student Expense Tracker!");
                    running = false;
                    break;
            }
        }

        scanner.close();
    }
}