public class VarExp extends Expr {
  public final String value;

  public VarExp(String _value) {
    value = _value;
  }
  public Value eval(Env e) throws EvalError {
    return e.lookup(value);
  }

  public String toString() {
    return value;
  }
}
