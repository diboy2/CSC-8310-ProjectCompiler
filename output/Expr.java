public abstract class Expr {
    abstract public Value eval(Env e) throws EvalError;
    abstract public String toString();
}
