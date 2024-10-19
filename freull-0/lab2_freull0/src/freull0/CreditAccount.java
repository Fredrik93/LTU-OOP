package freull0;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * Inlämningsuppgift2 Syfte: innehåller logiken för Kreditkonto
 *
 * @author Fredrik Ullman, freull-0
 */
public class CreditAccount extends Account
{
    // Kreditgräns
    private final BigDecimal creditLimit = new BigDecimal("5000");
    // Ränta
    private final BigDecimal interestRate = new BigDecimal("1.1");
    // Skuldränta
    private final BigDecimal penaltyFeeRate = new BigDecimal("5.0");

    /**
     * Konstruktorn. Används för att skapa ny instans av objektet
     *
     * @param amount
     *         saldo
     * @param interestRate
     *         räntesats
     */
    public CreditAccount(BigDecimal amount, BigDecimal interestRate)
    {
        super(amount, interestRate, AccountType.KREDITKONTO);
        this.amount = amount;

    }

    public boolean withdraw(int accountNumber, int amount)
    {
        int negatedAmount = amount * -1;

        //Kolla så beloppet är mer än 0
        if(amount < 0)
        {
            return false;
        }

        BigDecimal withdrawAmountPlusCurrentAmount = this.amount.add(BigDecimal.valueOf(negatedAmount));
        if(withdrawAmountPlusCurrentAmount.compareTo(new BigDecimal("-5000")) < 0)
        {
            //om summan man vill ta ut överstiger kreditgränsen på -5000
            // System.out.println("Withdraw is over the creditlimit" + withdrawAmountPlusCurrentAmount);
            return false;
        }
        //Lägg till transaktion
        createTransaction(LocalDateTime.now(), negatedAmount, this.amount, accountNumber);
        // uttag
        return withdrawAmount(BigDecimal.valueOf(amount));
    }

    /**
     * Hjälpmetod för att ta ut belopp
     *
     * @param amountToWithdraw
     *         uttagsbelopp
     * @return flagga om uttag gick bra
     */
    @Override
    public boolean withdrawAmount(BigDecimal amountToWithdraw)
    {

        // om uttagsbelopp är mer än saldot, dra av det som går ner så saldot är
        // Lägg till resten på kreditsaldot
        // negera kreditsaldot och visa det istället i toString om saldot är
        // mindre än 0
        BigDecimal amountToBeWithdrawnFromCreditAmount;
        if(amountToWithdraw.compareTo(amount) > 0)
        {
            BigDecimal creditAmount = amount.add(creditLimit);
            // om uttagsbeloppet är högre än kredit + saldo
            if(amountToWithdraw.compareTo(creditAmount) > 0)
            {
                return false;
            }
            // behåll resten av uttagsbeloppet
            amountToBeWithdrawnFromCreditAmount = amountToWithdraw.subtract(amount);
            // få ner saldot till 0
            amount = BigDecimal.ZERO;
            // lägg till mellanskillnaden i kreditsaldot
            creditAmount.add(amountToWithdraw);

            amount = amount.subtract(amountToBeWithdrawnFromCreditAmount);
            return true;

        }
        else
        {
            amount = amount.subtract(amountToWithdraw);
            return true;
        }

    }

    @Override
    void createTransaction(LocalDateTime date, int transactionAmount, BigDecimal currentAmount, int accountNumber)
    {
        if(getAccountType().equals(AccountType.KREDITKONTO))
        {
            currentAmount = getAmount().add(BigDecimal.valueOf(transactionAmount));
        }
        // Sätter man in t.ex 500 men saldot är 0 så kommer det bli fel i transaktionen, så byt ut currentAmount ( saldo) till uttagsbeloppet
        if(currentAmount.compareTo(BigDecimal.ZERO) == 0)
        {
            currentAmount = BigDecimal.valueOf(transactionAmount);
        }
        //här behöver jag tänka på räntan som är 2% vid andra uttaget, det är den summan jag vill ha. alltså 102, inte 100.
        Transaction t = new Transaction(date, transactionAmount, currentAmount, accountNumber);
        addTransactionToList(t);
    }

    @Override
    public BigDecimal getAmount()
    {
        return super.getAmount();
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
        DecimalFormat decimalFormatPenaltyRate = new DecimalFormat("0", setSymbols());

        // Formattera summan
        String formattedAmount = decimalFormatAmount.format(this.amount);

        // Formattera räntesatsen
        String formattedInterestRate = decimalFormatInterestRate.format(this.interestRate);
        //Om summan är negativ visa skuldränta istället för vanlig ränta
        if(this.amount.compareTo(BigDecimal.ZERO) < 0)
        {
            formattedInterestRate = decimalFormatPenaltyRate.format(this.penaltyFeeRate);
        }

        return getAccountNumber() + " " + formattedAmount + " kr " + getAccountType().getName() + " " + formattedInterestRate + " %";
    }
}