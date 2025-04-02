package freull0.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * * Inlämningsuppgift4 Syfte: innehåller fält för transaktioner *
 * * @author Fredrik Ullman, freull-0
 *
 * @param date
 *         datum för transaktion
 * @param transactionAmount
 *         beloppet som tas ut eller sätts in
 * @param currentAmount
 *         saldot på kontot
 * @param accountId
 *         kontoId
 */
public record Transaction(LocalDateTime date, int transactionAmount, BigDecimal currentAmount, int accountId)
        implements Serializable
{

    public String formatDate()
    {
        //Expected : [2024-10-14 17:33:52 -500,00 kr Saldo: -500,00 kr, 2024-10-14 17:33:53 -4 000,00 kr Saldo: -4 500,00 kr]
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    private String formattedTransactionAmount()
    {
        DecimalFormat decimalFormatAmount = new DecimalFormat("###,##0.00", setSymbols());
        return decimalFormatAmount.format(this.transactionAmount);
    }

    private String formattedcurrentAmount()
    {
        DecimalFormat decimalFormatAmount = new DecimalFormat("###,##0.00", setSymbols());
        return decimalFormatAmount.format(this.currentAmount);
    }

    /** Används för att lägga till rätt symboler e.g., komma-separerade värden */
    public DecimalFormatSymbols setSymbols()
    {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        return symbols;
    }

    @Override
    public String toString()
    {
        return formatDate() + " " + formattedTransactionAmount() + " kr " + "Saldo: " + formattedcurrentAmount() + " kr";
    }
}

