package astroclime.backend;
public enum Unit {
    C(Character.toString((char) 176 ) + "C"), F(Character.toString((char) 176 ) + "F"), K("K");
    private String symbol;
    Unit(String c) {
        this.symbol = c;
    }
    public String getSymbol(){
        return symbol;
    }
}
