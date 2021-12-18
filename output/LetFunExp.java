public class LetFunExp extends Expr {
    public final String id;
    public final String param;
    public final Expr funExp;
    public final Expr envExp;

    public LetFunExp(
        String _id,
        String _param,
        Expr _funExp,
        Expr _envExp
    ) {
        id = _id;
        param = _param;
        funExp = _funExp;
        envExp = _envExp;
    }

    public Value eval(Env e) throws EvalError {
        e = e.addBinding(id, new FunVal(null, null, null));
        FunVal funVal = new FunVal(param, funExp, e);
        e = e.updateBinding(id, funVal);
        return envExp.eval(e);
    }

    public String toString()  {
        return "let fun " + id +
            funExp.toString() + " in " +
            envExp.toString() + " end ";
    }
}
