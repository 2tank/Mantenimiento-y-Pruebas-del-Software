package bank;

public class BankAccount {
    private int balance = 0;
    public BankAccount(int startingBalance) {
        this.balance = startingBalance;
    }
    public boolean withdraw(int amount) {
        
        if(amount < 0){
            throw new IllegalArgumentException("Se ha intentado sacar una cantidad negativa");
        }
        
        if(balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public int deposit(int amount) {
        if (amount <0)
            throw new IllegalArgumentException("Amount cannot be negative");
        balance += amount;
        return balance;
    }
    public int getBalance() {
        return balance;
    }

    // Calculate the payment per month for a loan
    public double payment(double total_amount, double interest, int npayments){
        
        if(total_amount < 0 || interest < 0  || npayments <= 0){
            throw new IllegalArgumentException("Incorrect value in payment");
        }
        if(interest == 0){
            return total_amount / npayments;
        }
        return total_amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1));
    }

    // Calculate the pending amount for a loan in a month
    public double pending (double amount, double inte, int npayments, int month){
        double res;
        if(amount <= 0 || inte < 0 || npayments <= 0 || month < 0){
            throw new IllegalArgumentException("Incorrect value in pending");
        }
        else if(month==0){
            res=amount;
        }else{
            double ant=pending(amount, inte, npayments, month-1);
            res = ant - (payment(amount,inte,npayments) - inte*ant);
        }
        return res;
    }
}