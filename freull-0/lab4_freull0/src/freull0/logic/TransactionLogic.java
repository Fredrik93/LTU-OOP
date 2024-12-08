package freull0.logic;

import freull0.model.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionLogic
{
    /**
     * Används för att sätt in pengar på ett konto
     *
     * @param amount
     *         saldo
     * @return flagga som indikerar om uttaget är godkänt
     */
    public boolean deposit(Account account, int amount)
    {
        if(account == null)
        {
            return false;
        }
        if(amount > 0)
        {
            //add transaction
            account.createTransaction(LocalDateTime.now(), amount, account.getAmount(), account.getAccountNumber());
            //deposit transaction
            account.depositAmount(BigDecimal.valueOf(amount));
            return true;
        }
        return false;
    }

    /**
     * Används för att ta pengar på ett konto
     *
     * @param amount
     *         saldo
     * @return flagga som indikerar om insättningen är godkänd
     */
    public boolean withdraw(Account account, int amount)
    {
        if(account != null)
        {
            return account.withdraw(account.getAccountNumber(), amount);
        }
        return false;
    }
}
