package freull0;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Inlämningsuppgift1 Syfte:
 *
 * @author Fredrik Ullman, freull-0
 */

public class Account
{
    private final BigDecimal amount;
    private final BigDecimal interestRate;
    private int accountNumber;
    private static int lastAccountNumber = 1000;
    private final AccountType accountType;

    public Account(BigDecimal amount, BigDecimal interestRate, AccountType accountType)
    {

        this.amount = amount;
        this.interestRate = interestRate;

        lastAccountNumber++;
        accountNumber = lastAccountNumber;

        this.accountType = accountType;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public BigDecimal getInterestRate()
    {
        return interestRate;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    @Override
    public String toString()
    {
        // Create a DecimalFormatSymbols object for customizing symbols
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
        symbols.setDecimalSeparator(',');

        // Define a DecimalFormat with a pattern and the custom symbols
        DecimalFormat decimalFormatAmount = new DecimalFormat("0.00", symbols);
        DecimalFormat decimalFormatInterestRate = new DecimalFormat("0.0", symbols);

        // Format the BigDecimal to a string. and add two decimals
        String formattedAmount = decimalFormatAmount.format(this.amount);
        // Format interest rate as well. both are comma separated
        String formattedInterestRate = decimalFormatInterestRate.format(this.interestRate);

        return accountNumber + " " + formattedAmount + " kr " + accountType.getName() + " " + formattedInterestRate + " %";
    }
}
