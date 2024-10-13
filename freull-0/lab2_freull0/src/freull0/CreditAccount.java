package freull0;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CreditAccount extends Account
{
    // Kreditgräns
    private final BigDecimal creditLimit = new BigDecimal("5000");
    // Ränta
    BigDecimal interestRate = new BigDecimal("1.1");
    // Skuldränta
    BigDecimal penaltyFeeRate = new BigDecimal("5.0");
    // Kreditsaldo
    BigDecimal creditAmount = creditLimit;

    /**
     * Konstruktorn. Används för att skapa ny instans av objektet
     *
     * @param amount
     *            saldo
     * @param creditAmount
     *            kreditSaldo
     * @param interestRate
     *            räntesats
     * @param accountType
     *            kontotypen
     */

    /**
     * När kreditkontot skapas så är saldot 0 kr, kreditgränsen är satt till
     * 5000 kr. M an kan göra uttag så länge saldot inte passerar kreditgränsen,
     * saldot ska kunna bli lägst -5000 kr då kreditgränsen är 5000 kr.
     */
    public CreditAccount(BigDecimal amount, BigDecimal interestRate, AccountType accountType)
    {
        super(amount, interestRate, accountType);
        this.amount = amount;

    }

    @Override
    public boolean withdrawAmount(BigDecimal amountToWithdraw)
    {

        // om uttagsbelopp är mer än saldot, dra av det som går ner så saldot är
        // Lägg till resten på kreditsaldot
        // negera kreditsaldot och visa det istället i toString om saldot är
        // mindre än 0
        BigDecimal amountToBeWithdrawnFromCreditAmount = this.amount;
        if((amountToWithdraw.compareTo(amount)) > 0)
        {
            BigDecimal creditAmount = amount.add(creditLimit);
           // System.out.println("uttagsbelopp:" + amountToWithdraw + " saldo + kredit: " + creditAmount);

            // om uttagsbeloppet är högre än kredit + saldo
            if(amountToWithdraw.compareTo(creditAmount) == 1 ){
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
         //   System.out.println("uttagsbelopp:" + amountToWithdraw + " saldo: " + amount);
      
            amount = amount.subtract(amountToWithdraw);
            return true;
        }

    }

    public void closeAccount()
    {
        // Om saldot är under noll
        if(amount.compareTo(BigDecimal.ZERO) < 0)
        {
            BigDecimal penaltyFeeRateInPercent = penaltyFeeRate.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            BigDecimal penaltyFee = getAmount().multiply(penaltyFeeRateInPercent);
            BigDecimal amountWithSubtractedPenaltyFee = getAmount().subtract(penaltyFee);
            super.amount = amountWithSubtractedPenaltyFee;
        }
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

        // Formattera summan
        String formattedAmount = decimalFormatAmount.format(this.amount);

        // Formattera räntesatsen
        String formattedInterestRate = decimalFormatInterestRate.format(this.interestRate);

        return getAccountNumber() + " " + formattedAmount + " kr " + getAccountType().getName() + " "
                + formattedInterestRate + " %";
    }
}