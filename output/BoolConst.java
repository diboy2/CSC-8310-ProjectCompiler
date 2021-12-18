public class BoolConst extends Expr {
    public final boolean value;

    public BoolConst(boolean _value) {
        value = _value;
    }
    public Value eval(Env e) throws EvalError {
        return new BoolVal(value);
    }
    public String toString() {
        return String.valueOf(value);
    }
}
