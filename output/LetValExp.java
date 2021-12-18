public class LetValExp extends Expr {
    public final String id;
    public final Expr valExp;
    public final Expr envExp;

    public LetValExp(
        String _id,
        Expr _valExp,
        Expr _envExp
    ) {
        id = _id;
        valExp = _valExp;
        envExp = _envExp;
    }

    public Value eval(Env e) throws EvalError {
        Value val = valExp.eval(e);
        e = e.addBinding(id, val);
        return envExp.eval(e);
    }

    public String toString()  {
        return "let val " + id + " = " +
            valExp.toString() + " in " +
            envExp.toString() + " end ";
    }
}
