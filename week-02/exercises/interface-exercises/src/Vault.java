public class Vault implements MoneyStorage {

    private double balance;
    private String description;

    public Vault(double startingBalance, String description) {
        this.balance = startingBalance;
        this.description = description;
    }

    public Vault(){}

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }
    @Override
    public double withdraw(double amount) {
        // can't withdraw a negative amount
        if (balance - amount < 0.0) {
            double amountToReturn = balance;
            balance = 0;
            return amountToReturn;
        } else {
            balance -= amount;
            return amount;
        }
    }
}
