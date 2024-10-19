package freull0;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Syfte: Sparkonto för en kund Inlämningsuppgift 2
 *
 * @author Fredrik Ullman, freull-0
 */
public class SavingsAccount extends Account
{
    private static final int yearlyFreeWithdrawals = 1;
    private static final int MAX_AMOUNT_OF_FREE_WITHDRAWALS = 1;
    private int withDrawalsCounter = 0;

    public SavingsAccount(BigDecimal amount, BigDecimal interestRate)
    {
        super(amount, interestRate, AccountType.SPARKONTO);
    }

    public boolean withdraw(int accountNumber, int amount, Account account)
    {
        int negatedAmount = amount * -1;

        //Kolla så beloppet är mer än 0
        if(amount < 0)
        {
            return false;
        }
        if(account != null)
        {
            if(account.getAccountType().equals(AccountType.SPARKONTO))
            {
                if(account.getAmount().intValue() >= amount && amount > 0)
                {

                    // Uttag, om withdraw returnerar sant
                    if(account.getAmount().compareTo(new BigDecimal(amount)) >= 0)
                    {
                        //är beloppet högre än saldot så returnera false
                        if(!account.withdrawAmount(BigDecimal.valueOf(amount)))
                        {
                            return false;
                        }
                        //Lägg till transaktion
                        account.addTransaction(LocalDateTime.now(), negatedAmount, account.getAmount(), accountNumber);
                        return true;
                    }
                }
            }
            //Else-if för läsbarhet. hade räckt med if och sedan else.
            else if(account.getAccountType().equals(AccountType.KREDITKONTO))
            {
                {
                    BigDecimal withdrawAmountPlusCurrentAmount = account.amount.add(BigDecimal.valueOf(negatedAmount));
                    if(withdrawAmountPlusCurrentAmount.compareTo(new BigDecimal("-5000")) < 0)
                    {
                        //om summan man vill ta ut överstiger kreditgränsen på -5000
                        // System.out.println("Withdraw is over the creditlimit" + withdrawAmountPlusCurrentAmount);
                        return false;
                    }
                    //Lägg till transaktion
                    account.addTransaction(LocalDateTime.now(), negatedAmount, account.amount, accountNumber);
                    // uttag
                    return account.withdrawAmount(BigDecimal.valueOf(amount));

                }
            }
        }
        return false;
    }

    @Override
    boolean withdrawAmount(BigDecimal amountToWithdraw)
    {

        // Kolla om man nått gränsen för årliga gratisuttag. då vi gör en koll (rad 96 Banklogic) så inkrementeras withdrawalsCounter en gång för mycket,
        //därför behöver vi kolla <= istället för enbart <
        if(withDrawalsCounter < MAX_AMOUNT_OF_FREE_WITHDRAWALS)
        {
            amount = getAmount().subtract(amountToWithdraw);
            withDrawalsCounter++;
            return true;
        }
        //Om man nått gränsen:
        else
        {
            //Uttag efter det första fria uttaget beläggs med en uttagsränta på 2% av uttaget belopp
            // Tar man ut 500 kr så dras 510 kr från kontot (eftersom 2% av 500 är 0,02*500).
            BigDecimal twoPercent = new BigDecimal("0.02");
            BigDecimal withdrawalFee = amountToWithdraw.multiply(twoPercent);
            BigDecimal withdrawedAmountWithFee = amountToWithdraw.add(withdrawalFee);
            // Beloppet är för högt
            if(withdrawedAmountWithFee.compareTo(getAmount()) > 0)
            {
                return false;
            }
            super.amount = getAmount().subtract(withdrawedAmountWithFee);
            withDrawalsCounter++;
            return true;
        }
    }

    @Override
    void addTransaction(LocalDateTime date, int transactionAmnt, BigDecimal currentAmount, int accountNumber)
    {
        BigDecimal transactionAmount = BigDecimal.valueOf(transactionAmnt);
        // Sätter man in t.ex 500 men saldot är 0 så kommer det bli fel i transaktionen, så byt ut currentAmount ( saldo) till uttagsbeloppet
        if(currentAmount.compareTo(BigDecimal.ZERO) == 0)
        {
            currentAmount = transactionAmount;
        }
        //här behöver jag tänka på räntan som är 2% vid andra uttaget, det är den summan jag vill ha. alltså 102, inte 100.
        if(withDrawalsCounter > 1)
        {
            BigDecimal interestRate = new BigDecimal("0.02");
            BigDecimal interestAmountTotal = transactionAmount.multiply(interestRate);
            transactionAmount = transactionAmount.add(interestAmountTotal);
        }
        Transaction t = new Transaction(date, transactionAmount.intValue(), currentAmount, accountNumber);
        getTransactions().add(t);
    }

}
