package freull0;

public enum AccountType

{
    SPARKONTO("Sparkonto");
    private String sparKonto;

    AccountType(String sparKonto)
    {
        this.sparKonto = sparKonto;
    }

    public String getName()
    {
        return sparKonto;
    }
}
