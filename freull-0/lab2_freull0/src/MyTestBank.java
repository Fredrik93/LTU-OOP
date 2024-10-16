import freull0.BankLogic;

public class MyTestBank
{
    //[2024-10-15 17:33:00 1 000,00 kr Saldo: 0 kracc: 1001, 2024-10-15 17:33:00 -4 000,00 kr Saldo: -500,00 kracc: 1001, 2024-10-15 17:33:00 10,00 kr Saldo: -3000 kracc: 1001]
    public static void main(String[] args)
    {
        BankLogic bank = new BankLogic();
        // Customer Jim
        bank.createCustomer("Jim", "Nilsson", "10");
      /*  bank.createCreditAccount("666");
        bank.deposit("666", 1001, 1000);
        bank.withdraw("666", 1001, 4000);
        bank.deposit("666", 1001, 10);

        bank.createCreditAccount("666");
        bank.deposit("666", 1002, 23);
        bank.withdraw("666", 1002, 10);
        bank.deposit("666", 1002, 150); */

        //Customer Lis
        bank.createCustomer("Lis", "fis", "1");
        bank.createSavingsAccount("1");
        bank.deposit("1", 1001, 500);
   
        System.out.println(bank.getCustomer("1"));
        System.out.println(bank.getTransactions("1", 1001));
    }
}