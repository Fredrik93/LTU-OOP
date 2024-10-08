import freull0.BankLogic;

public class MyTestBank
{

    public static void main(String[] args)
    {
        BankLogic bank = new BankLogic();
        bank.createCustomer("Jim", "Nilsson", "666");
        bank.createSavingsAccount("666");
        bank.deposit("666", 1001, 1000);

        System.out.println(bank.getCustomer("666"));

        System.out.println(bank.withdraw("666", 1001, 500));
        System.out.println(bank.getCustomer("666"));

    }
}