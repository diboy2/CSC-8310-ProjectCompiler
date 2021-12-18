public class NotExp extends Expr {
  public final Expr arg;

  public NotExp(Expr _arg) {
    arg = _arg;
  }

  public Value eval(Env e) throws EvalError {
    Value val = arg.eval(e);
    if (val instanceof BoolVal)
      return new BoolVal(!((BoolVal)val).value);
    else
      throw new EvalError("Evaluation error at NotExp: Incompatible arg types");
  }

  public String toString() {
    return "! " + arg.toString();
  }
}
