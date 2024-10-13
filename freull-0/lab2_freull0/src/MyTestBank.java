import freull0.BankLogic;

public class MyTestBank
{

    public static void main(String[] args)
    {
        BankLogic bank = new BankLogic();
        bank.createCustomer("Jim", "Nilsson", "666");
       
        bank.createCreditAccount("666");
        bank.deposit("666", 1001, 1000);

        bank.withdraw("666", 1001, 2000);
        bank.deposit("666", 1001, 0);

        System.out.println(bank.getCustomer("666"));
      
    }
}