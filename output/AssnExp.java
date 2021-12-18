public class AssnExp extends Expr {
    public final String id;
    public final Expr exp;

    public AssnExp(
        String _id,
        Expr _exp
    ) {
        id = _id;
        exp = _exp;
    }

    public Value eval(Env e) throws EvalError {
        Value newVal = exp.eval(e);
        if(e.lookup(id).getClass() == newVal.getClass()) {
            e = e.updateBinding(id, newVal);
            return e.lookup(id);
        } else {
            throw new EvalError("Evaluation error at AssnExp: values not of the same type");
        }
    }

    public String toString()  {
        return id + " := " + exp.toString();
    }
}
