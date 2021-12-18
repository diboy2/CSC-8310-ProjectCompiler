public class FunVal extends Value {
    public final String param;
    public final Expr expr;
    public final Env env;

    public FunVal(String _param, Expr _expr, Env _env) {
        param = _param;
        expr = _expr;
        env = _env;
    }


    public String toString() {
        return "( " + param + " ) = " + expr.toString();
    }
}
