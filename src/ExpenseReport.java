import java.util.HashMap;
import java.util.Map;

public class ExpenseReport {

    public static void printReport(ExpenseManager manager) {

        if (manager.getExpenses().isEmpty()) {
            System.out.println("\nNo expenses available for the report.");
            return;
        }

        double total = 0;
        double highest = 0;

        String highestCategory = "";
        String highestDescription = "";

        HashMap<String, Double> categoryTotals = new HashMap<>();

        for (Expense expense : manager.getExpenses()) {

            double amount = expense.getAmount();
            total += amount;

            if (amount > highest) {
                highest = amount;
                highestCategory = expense.getCategory();
                highestDescription = expense.getDescription();
            }

            String category = expense.getCategory();

            if (categoryTotals.containsKey(category)) {
                categoryTotals.put(
                        category,
                        categoryTotals.get(category) + amount
                );
            } else {
                categoryTotals.put(category, amount);
            }
        }

        double average = total / manager.getExpenses().size();

        System.out.println("\n========== EXPENSE REPORT ==========");
        System.out.printf("Total Expenses : Rs. %.2f%n", total);
        System.out.printf("Average Expense: Rs. %.2f%n", average);

        System.out.println("\nHighest Expense:");
        System.out.printf(
                "Rs. %.2f - %s (%s)%n",
                highest,
                highestDescription,
                highestCategory
        );

        System.out.println("\nCategory-wise Spending:");

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.printf(
                    "%-15s : Rs. %.2f%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }

        System.out.println("====================================");
    }
}