package freull0.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Inlämningsuppgift4 Syfte: Definierar fält för ett konto
 *
 * @author Fredrik Ullman, freull-0
 */

public abstract class Account implements Serializable
{
    protected BigDecimal amount;
    private final BigDecimal interestRate;
    private final int accountNumber;
    private static int lastAccountNumber = 1000;
    private final AccountType accountType;

    private final List<Transaction> transactions = new ArrayList<>();

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

    public abstract boolean withdraw(int accountNumber, int amount);

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void depositAmount(BigDecimal amount)
    {
        this.amount = this.amount.add(amount);
    }

    abstract boolean withdrawAmount(BigDecimal amount);

    public int getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Hämta kontotyp
     */
    public AccountType getAccountType()
    {
        return accountType;
    }

    public List<Transaction> getTransactions()
    {
        return List.copyOf(transactions);
    }

    /**
     * Lägg till transaktion i lista
     *
     * @param transaction
     *         objektet som håller transaktionsinformation
     */
    public void addTransactionToList(Transaction transaction)
    {
        transactions.add(transaction);
    }

    /**
     * Create a transaction
     *
     * @param date
     *         datum
     * @param transactionAmount
     *         belopp på transaktionen
     * @param currentAmount
     *         saldo
     * @param accountNumber
     *         kontonummer
     */
    public abstract void createTransaction(LocalDateTime date, int transactionAmount, BigDecimal currentAmount,
            int accountNumber);

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
    public BigDecimal calculateInterestRate(BigDecimal interestRate, BigDecimal amount)
    {
        // är summan under 0 så applicera skuldräntan på 5%
        if(amount.compareTo(BigDecimal.ZERO) < 0)
        {
            BigDecimal penaltyRate = new BigDecimal("5.00");
            BigDecimal divisor = new BigDecimal("100");
            return penaltyRate.divide(divisor, 5, RoundingMode.HALF_UP).multiply(amount);

        }
        BigDecimal rate = interestRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP);
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
    public DecimalFormatSymbols setSymbols()
    {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        return symbols;
    }
    public static void setLastAccountNumber(int lastAccountNumber) {
        Account.lastAccountNumber = lastAccountNumber;
    }
}
