public class WhileExp extends Expr {
    public final Expr logicExp;
    public final Expr doExp;

    public WhileExp(
        Expr _logicExp,
        Expr _doExp
    ) {
        logicExp = _logicExp;
        doExp = _doExp;
    }

    public Value eval(Env e) throws EvalError {
        Value val = logicExp.eval(e);
        if(val instanceof BoolVal) {
            if(((BoolVal)val).value) {
                doExp.eval(e);
                return this.eval(e);
            } else {
                return new BoolVal(false);
            }
        } else {
            throw new EvalError(
                "Evaluation error at WhileExp: logical expression does not evaluate to boolean"
            );
        }
    }

    public String toString()  {
        return "while " + logicExp.toString() +
            " do " + doExp.toString();
    }
}
