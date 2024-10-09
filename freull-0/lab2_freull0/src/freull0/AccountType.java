package freull0;

public enum AccountType

{
    SPARKONTO("Sparkonto"), KREDITKONTO("Kreditkonto");

    private String kontoTyp;

    AccountType(String kontoTyp)
    {
        this.kontoTyp = kontoTyp;
    }

    public String getName()
    {
        return kontoTyp;
    }
}
