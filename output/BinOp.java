import java.util.function.Function;

public enum BinOp {
    PLUS("+"),
    // {
    //     public Value evaluateValues(int val1,int val2) {
    //         return IntVal(val1 + val2);
    //     }
    // }
    MINUS("-"),
    TIMES("*"),
    DIV("/"),
    EQ("="),
    LT("<"),
    AND("&"),
    OR("|");
    private final String value;

    private BinOp(String _value) {
        value = _value;
    }

    // public abstract Value evaluateValues(int val1, int val2);
    public String toString() {
        return value;
    }
}
