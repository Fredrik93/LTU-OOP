import freull0.BankLogic;

public class MyTestBank
{

    public static void main(String[] args)
    {
        BankLogic bank = new BankLogic();
        bank.createCustomer("Jim", "Nilsson", "666");
        bank.createCreditAccount("666");

        System.out.println(bank.getAccount("666", 1001));
        bank.withdraw("666", 1001, 10000);
        System.out.println(bank.getAccount("666", 1001));

    }
}