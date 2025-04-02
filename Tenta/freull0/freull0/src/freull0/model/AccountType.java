package freull0.model;

/**
 * Inlämningsuppgift4 Syfte: Innehåller konstanter för konton.
 *
 * @author Fredrik Ullman, freull-0
 */
public enum AccountType

{
    SPARKONTO("Sparkonto"), KREDITKONTO("Kreditkonto");

    private final String kontoTyp;

    AccountType(String kontoTyp)
    {
        this.kontoTyp = kontoTyp;
    }

    public String getName()
    {
        return kontoTyp;
    }
}
