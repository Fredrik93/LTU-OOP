package freull0;

public class MyTestBank
{

    public static void main(String[] args)
    {
        BankLogic bank = new BankLogic();
        // create customer
        bank.createCustomer("Mike", "doe", "1234");
        //   bank.createCustomer("John", "doe", "122345");
        //   System.out.println(bank.getAllCustomers());

        //   bank.changeCustomerName("Fred", "ull", "1234");

        //   System.out.println(bank.getAllCustomers());

        bank.createSavingsAccount("1234");
        bank.createSavingsAccount("1234");

    }
}
