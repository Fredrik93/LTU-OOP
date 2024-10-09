package freull0;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditAccount extends Account
{
    //Kreditgräns
    private final BigDecimal creditLimit = new BigDecimal("5000");
    //Ränta
    BigDecimal interestRate = new BigDecimal("1.1");
    //Skuldränta
    BigDecimal penaltyFeeRate = new BigDecimal("5.0");

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

    /**
     * När kreditkontot skapas så är saldot 0 kr, kreditgränsen är satt till 5000 kr. M an kan göra uttag så länge
     * saldot inte passerar kreditgränsen, saldot ska kunna bli lägst -5000 kr då kreditgränsen är 5000 kr.
     */
    public CreditAccount(BigDecimal amountAvailable, BigDecimal interestRate, AccountType accountType)
    {
        super(amountAvailable, interestRate, accountType);
        this.amount = amountAvailable.add(creditLimit);

    }

    @Override
    void withdrawAmount(BigDecimal amount)
    {
        //saldot får inte passera kreditgränsen (-5000). fast det här är nog fel. tän om if satsen.
        if(amount.compareTo(creditLimit) > 0)
        {
            throw new IllegalArgumentException("Kreditgränsen (5000:-) nådd, prova att ta ut en lägre summa");
        }
        super.amount = getAmount().subtract(amount);

    }

    public void closeAccount()
    {
        //Om saldot är under noll
        if(amount.compareTo(BigDecimal.ZERO) < 0)
        {
            BigDecimal penaltyFeeRateInPercent = penaltyFeeRate.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            BigDecimal penaltyFee = getAmount().multiply(penaltyFeeRateInPercent);
            BigDecimal amountWithSubtractedPenaltyFee = getAmount().subtract(penaltyFee);
            super.amount = amountWithSubtractedPenaltyFee;
        }
    }
}
