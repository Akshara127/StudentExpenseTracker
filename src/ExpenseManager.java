import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private final ArrayList<Expense> expenses;

    public ExpenseManager() {
        this(new ArrayList<>());
    }

    public ExpenseManager(ArrayList<Expense> expenses) {
        this.expenses = (expenses != null) ? expenses : new ArrayList<>();
    }

    public Expense addExpense(double amount, String category, String description, String date) {
        int nextId = expenses.isEmpty() ? 1 : expenses.get(expenses.size() - 1).getId() + 1;
        Expense expense = new Expense(nextId, amount, category, description, date);
        expenses.add(expense);
        return expense;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public boolean deleteExpense(int id) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId() == id) {
                expenses.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public List<Expense> search(String keyword) {
        ArrayList<Expense> result = new ArrayList<>();

        if (keyword == null) {
            return result;
        }

        String searchText = keyword.toLowerCase();

        for (Expense expense : expenses) {
            if (expense.getCategory().toLowerCase().contains(searchText)
                    || expense.getDescription().toLowerCase().contains(searchText)
                    || expense.getDate().toLowerCase().contains(searchText)) {
                result.add(expense);
            }
        }

        return result;
    }

    public double getTotalExpense() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    public void checkBudget(double budget) {
        double total = getTotalExpense();

        System.out.println("\n----- Budget Status -----");
        System.out.printf("Your budget: Rs. %.2f%n", budget);
        System.out.printf("Amount spent: Rs. %.2f%n", total);

        if (total > budget) {
            System.out.println("WARNING: You have exceeded your budget!");
        } else {
            double remaining = budget - total;
            System.out.printf("Remaining budget: Rs. %.2f%n", remaining);

            if (remaining <= budget * 0.20) {
                System.out.println("Alert: You are close to your budget limit.");
            } else {
                System.out.println("You are within your budget.");
            }
        }
    }
}