import java.io.*;
import java.util.ArrayList;


public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = resolveFilePath(filePath);
    }

    private String resolveFilePath(String filePath) {
        File requestedFile = new File(filePath);

        if (requestedFile.isAbsolute()) {
            return requestedFile.getAbsolutePath();
        }

        File currentDir = new File(System.getProperty("user.dir")).getAbsoluteFile();
        File projectRoot = currentDir;

        while (projectRoot != null) {
            File candidate = new File(projectRoot, filePath);
            File srcDir = new File(projectRoot, "src");
            File readme = new File(projectRoot, "README.md");

            if (srcDir.exists() || readme.exists()) {
                return candidate.getAbsolutePath();
            }

            projectRoot = projectRoot.getParentFile();
        }

        return new File(currentDir, filePath).getAbsolutePath();
    }

    public static void saveExpenses(ExpenseManager manager) {
        if (manager == null) {
            return;
        }

        FileManager defaultFileManager = new FileManager("data/expenses.txt");
        defaultFileManager.saveExpenses(manager.getExpenses());
    }

    public static void loadExpenses(ExpenseManager manager) {
        if (manager == null) {
            return;
        }

        FileManager defaultFileManager = new FileManager("data/expenses.txt");
        ArrayList<Expense> loadedExpenses = defaultFileManager.loadExpenses();
        manager.getExpenses().clear();
        manager.getExpenses().addAll(loadedExpenses);
    }

    public void saveExpenses(ArrayList<Expense> expenses) {
        File file = new File(filePath);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Expense expense : expenses) {
                writer.write(expense.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save expenses.");
        }
    }

    public ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 5) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    double amount = Double.parseDouble(parts[1]);
                    expenses.add(new Expense(id, amount, parts[2], parts[3], parts[4]));
                } catch (NumberFormatException e) {
                    // Skip invalid records rather than stopping the application.
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not read saved expenses.");
        }

        return expenses;
    }
}
