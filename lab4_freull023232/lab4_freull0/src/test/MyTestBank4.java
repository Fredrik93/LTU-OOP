package test;

import freull0.controller.BankController;

import java.io.IOException;

public class MyTestBank4
{
    public static void main(String[] args) throws IOException {
        BankController bank = new BankController();
        bank.createCustomer("John", "Johnson", "1");
        bank.createSavingsAccount("1");
        bank.deposit("1", 1001, 10000);
        bank.withdraw("1", 1001, 1000);
        bank.createCustomer("Fred", "Johnson", "12");
        bank.createCreditAccount("12");
        bank.deposit("12", 1002, 1999);
        bank.createCustomer("Axel", "Johnson", "123");
        bank.createCustomer("Patrik", "Ullm", "1234");

        bank.saveAllCustomersToFile();
        bank.loadAllCustomersFromFile();
        System.out.println("banky! " +
                bank.printStatementOfAccountTransactions("1", 1001)
        );
    }
}
