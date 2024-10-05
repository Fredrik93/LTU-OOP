package freull0;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Inlämningsuppgift1 Syfte:
 * * Definierar fält för ett konto
 *
 * @author Fredrik Ullman, freull-0
 */

public class Account
{
    private BigDecimal amount;
    private final BigDecimal interestRate;
    private final int accountNumber;
    private static int lastAccountNumber = 1000;
    private final freull0.AccountType accountType;

    /**
     * Konstruktorn. Används för att skapa ny instans av objektet
     *
     * @param amount
     *         saldo
     * @param interestRate
     *         räntesats
     * @param accountType
     *         kontotypen
     */
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

    public void depositAmount(BigDecimal amount)
    {
        this.amount = this.amount.add(amount);
    }

    public void withdrawAmount(BigDecimal amount)
    {
        this.amount = this.amount.subtract(amount);
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * hjälpmetod för att visa en formatterad sträng vid stängning av konto
     *
     * @return en test som visas vid stängning av konto
     */
    public String closedAccountMessage()
    {

        // Lägg till önskade mönster för fälten
        DecimalFormat decimalFormatAmount = new DecimalFormat("###,##0.00", setSymbols());
        DecimalFormat decimalFormatInterestRate = new DecimalFormat("0.00", setSymbols());

        // Formattera summan
        String formattedAmount = decimalFormatAmount.format(this.amount);

        // Räkna ut räntesatsen
        BigDecimal calculcatedInterestRateReturn = calculateInterestRate(interestRate, amount);

        // Formattera räntesatsen
        String formattedInterestRate = decimalFormatInterestRate.format(calculcatedInterestRateReturn);

        return accountNumber + " " + formattedAmount + " kr " + accountType.getName() + " " + formattedInterestRate + " kr";
    }

    /**
     * @param interestRate
     *         räntesatsen
     * @param amount
     *         saldo
     * @return räntan i kronor
     */
    private BigDecimal calculateInterestRate(BigDecimal interestRate, BigDecimal amount)
    {
        BigDecimal rate = interestRate.divide(new BigDecimal(100));
        return rate.multiply(amount);
    }

    /**
     * Formatterar kontoinformationen.
     */
    @Override
    public String toString()
    {
        // Lägg till önskade mönster för fälten
        DecimalFormat decimalFormatAmount = new DecimalFormat("###,##0.00", setSymbols());
        DecimalFormat decimalFormatInterestRate = new DecimalFormat("0.0", setSymbols());

        //Formattera summan
        String formattedAmount = decimalFormatAmount.format(this.amount);

        // Formattera räntesatsen
        String formattedInterestRate = decimalFormatInterestRate.format(this.interestRate);

        return accountNumber + " " + formattedAmount + " kr " + accountType.getName() + " " + formattedInterestRate + " %";
    }

    /** Används för att lägga till rätt symboler e.g., komma-separerade värden */
    private DecimalFormatSymbols setSymbols()
    {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        return symbols;
    }

}
