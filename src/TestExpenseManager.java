import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TestExpenseManager {

    public static void main(String[] args) throws IOException {
        testExpenseManagerCore();
        testFileManagerUsesProjectRoot();
        System.out.println("All basic ExpenseManager tests passed.");
    }

    private static void testExpenseManagerCore() {
        ExpenseManager manager = new ExpenseManager(new ArrayList<>());

        Expense first = manager.addExpense(100, "Food", "Lunch", "03-09-2026");
        Expense second = manager.addExpense(250, "Travel", "Bus pass", "03-09-2026");

        if (manager.getExpenses().size() != 2) {
            throw new AssertionError("Add expense test failed.");
        }

        if (manager.search("food").size() != 1) {
            throw new AssertionError("Search test failed.");
        }

        if (!manager.deleteExpense(first.getId())) {
            throw new AssertionError("Delete test failed.");
        }

        if (manager.getExpenses().size() != 1
                || manager.getExpenses().get(0).getId() != second.getId()) {
            throw new AssertionError("Post-delete state test failed.");
        }
    }

    private static void testFileManagerUsesProjectRoot() throws IOException {
        String originalUserDir = System.getProperty("user.dir");
        Path tempRoot = Files.createTempDirectory("student-expense-project");
        Path nestedDir = Files.createDirectory(tempRoot.resolve("nested"));
        Files.createDirectories(tempRoot.resolve("src"));
        Files.writeString(tempRoot.resolve("README.md"), "project root");

        try {
            System.setProperty("user.dir", nestedDir.toString());

            FileManager fileManager = new FileManager("data/expenses.txt");
            ArrayList<Expense> expenses = new ArrayList<>();
            expenses.add(new Expense(1, 15.5, "Food", "Lunch", "03-09-2026"));
            fileManager.saveExpenses(expenses);

            File expectedFile = tempRoot.resolve("data/expenses.txt").toFile();
            if (!expectedFile.exists()) {
                throw new AssertionError("FileManager should resolve data files from the project root.");
            }
        } finally {
            System.setProperty("user.dir", originalUserDir);
        }
    }
}
