public class Person{

    private final String firstName;
    private final String lastName;
    private MoneyStorage moneyStorage;


    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MoneyStorage getMoneyStorage() {
        return moneyStorage;
    }

    public void setMoneyStorage(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
