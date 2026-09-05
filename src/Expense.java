public class Expense {
    private int id;
    private double amount;
    private String category;
    private String description;
    private String date;

    public Expense(int id, double amount, String category, String description, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-10.2f %-15s %-25s %-12s",
                id, amount, category, description, date);
    }

    public String toFileString() {
        return id + "|" + amount + "|" + category.replace("|", "/") + "|"
                + description.replace("|", "/") + "|" + date.replace("|", "/");
    }
}
