package freull0;

import java.math.BigDecimal;

/**
 * Syfte: Inlämningsuppgift 2
 *
 * @author Fredrik Ullman, freull-0
 *
 *
 *                               todo:  Klassen SavingsAccount ska, förutom det som ingick i första inlämningsuppgiften, innehålla en begränsning på
 *                                 ett fritt uttag per år.
 */
public class SavingsAccount extends Account
{
    private static final int yearlyFreeWithdrawals = 1;
    private static final int MAX_AMOUNT_OF_FREE_WITHDRAWALS = 1;
    private int withDrawalsCounter = 0;

    public SavingsAccount(BigDecimal amount, BigDecimal interestRate, AccountType accountType)
    {
        super(amount, interestRate, accountType);
    }

    @Override
    void withdrawAmount(BigDecimal amount)
    {
        // Kolla om man nått gränsen för årliga gratisuttag
        if(withDrawalsCounter < MAX_AMOUNT_OF_FREE_WITHDRAWALS)
        {
            super.amount = getAmount().subtract(amount);
            withDrawalsCounter++;
        }
        //Om man nått gränsen:
        else
        {
            //Uttag efter det första fria uttaget beläggs med en uttagsränta på 2% av uttaget belopp
            // Tar man ut 500 kr så dras 510 kr från kontot (eftersom 2% av 500 är 0,02*500).
            BigDecimal twoPercent = new BigDecimal("0.02");
            BigDecimal withdrawalFee = amount.multiply(twoPercent);
            BigDecimal withdrawedAmountWithFee = amount.add(withdrawalFee);

            super.amount = getAmount().subtract(withdrawedAmountWithFee);
        }

    }
}
