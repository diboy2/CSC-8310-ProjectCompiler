public class AppExp extends Expr {
    public final String id;
    public final Expr expr;

    public AppExp(
        String _id,
        Expr _expr
    ) {
        id = _id;
        expr = _expr;
    }

    public Value eval(Env e) throws EvalError {
        Value funVal = e.lookup(id);
        if(funVal instanceof FunVal) {
            Value val = expr.eval(e);
            Env denv = ((FunVal)funVal).env;
            String param = ((FunVal)funVal).param;
            Expr funExp = ((FunVal)funVal).expr;
            denv = denv.addBinding(param, val);
            return funExp.eval(denv);
        } else {
            throw new EvalError("Evaluation error: Incompatible arg types");
        }
    }

    public String toString()  {
        return id + "(" + expr.toString() + ")";
    }
}
