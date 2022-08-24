public class BankAccount implements MoneyStorage {

    private double balance;
    private String description;

    public BankAccount(double startingBalance, String accountNumber) {
        this.balance = startingBalance;
        this.description = accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return String.format("Bank Account #: %s", description);
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
        // can overdraft, up to 25 dollars
        if (balance - amount < - 25.0) {
            double amountToReturn = balance + amount;
            balance = - 25.0;
            return amountToReturn;
        }
        balance -= amount;
        return amount;

    }


}
