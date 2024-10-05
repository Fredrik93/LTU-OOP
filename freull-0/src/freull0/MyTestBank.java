package freull0;

public class MyTestBank
{

    public static void main(String[] args)
    {
        freull0.BankLogic bank = new BankLogic();
        // create customer
        bank.createCustomer("Mike", "doe", "7505121231");
        bank.createCustomer("Johnny", "doe", "12");
        bank.createSavingsAccount("12");
     /*   bank.createSavingsAccount("7505121231");
        bank.createSavingsAccount("7505121231");
        bank.createSavingsAccount("7505121231");
        bank.createSavingsAccount("7505121231");
        bank.createSavingsAccount("7505121231");
        bank.createSavingsAccount("7505121231");
        bank.deposit("7505121231", 1001, 500);
        bank.withdraw("7505121231", 1001, 1);
        System.out.println(bank.getCustomer("12"));
        System.out.println(bank.getCustomer("7505121231"));
        System.out.println(bank.getAccount("7505121231", 1004));
        System.out.println(bank.closeAccount("12",1001));*/
        System.out.println(bank.getCustomer("12"));

    }
}
