public class BinExp extends Expr {
  public final BinOp binOp;
  public final Expr expr1;
  public final Expr expr2;

  public BinExp(BinOp _binOp, Expr _expr1, Expr _expr2) {
    binOp = _binOp;
    expr1 = _expr1;
    expr2 = _expr2;
  }

  public Value eval(Env e) throws EvalError {
    return evaluateValues(expr1.eval(e), expr2.eval(e));
  }

  private Value evaluateValues(Value val1, Value val2) throws EvalError {
    switch(binOp) {
      case PLUS:
        checkForIntValues(val1, val2);
        return new IntVal(((IntVal)val1).value + ((IntVal)val2).value);
      case MINUS:
        checkForIntValues(val1, val2);
        return new IntVal(((IntVal)val1).value - ((IntVal)val2).value);
      case TIMES:
        checkForIntValues(val1, val2);
        return new IntVal(((IntVal)val1).value * ((IntVal)val2).value);
      case DIV:
        return evaluateDivide(val1, val2);
      case EQ:
        return evaluateEquals(val1, val2);
      case LT:
        checkForIntValues(val1, val2);
        return new BoolVal(((IntVal)val1).value < ((IntVal)val2).value);
      case AND:
        return shortCircuitAnd(val1, val2);
      case OR:
        return shortCircuitOr(val1, val2);
      default:
        throw new EvalError("Evaluation error at BinExp: Operator does not exist");
    }
  }

  public BoolVal evaluateEquals(Value val1, Value val2) throws EvalError {
    checkForEqualsValues(val1, val2);
    if(val1 instanceof IntVal) {
      return new BoolVal(((IntVal)val1).value == ((IntVal)val2).value);
    }

    if(val1 instanceof BoolVal) {
      return new BoolVal(((BoolVal)val1).value == ((BoolVal)val2).value);
    }
    throw new EvalError(
      "Evaluation error at BinExp: should not reach this point"
    );
  }

  public void checkForEqualsValues(Value val1, Value val2) throws EvalError {
    if(!(val1 instanceof BoolVal || val1 instanceof IntVal)) {
      throw new EvalError(
        "Evaluation error at BinExp: values are not both of type int or both of type boolean for equals operator"
      );
    }

    if(val1 instanceof BoolVal) {
      if(!(val2 instanceof BoolVal)) {
        throw new EvalError(
          "Evaluation error at BinExp: values are not both of type boolean for equals operator"
        );
      }
    }

    if(val1 instanceof IntVal) {
      if(!(val2 instanceof IntVal)) {
        throw new EvalError(
          "Evaluation error at BinExp: values are not both of type int for equals operator"
        );
      }
    }
  }

  public void checkForBoolValues(Value val1, Value val2) throws EvalError{
    if(!(val1 instanceof BoolVal) || !(val2 instanceof BoolVal)) {
      throw new EvalError(
        "Evaluation error at BinExp: values are not both of type boolean for logic operators");
    }
  }

  public BoolVal shortCircuitAnd(Value val1, Value val2) throws EvalError{
    checkForBoolValues(val1, val2);
    boolean value1 = ((BoolVal)val1).value;
    if(!value1) {
      return new BoolVal(false);
    }
    return new BoolVal(value1 && ((BoolVal)val2).value);
  }

  public BoolVal shortCircuitOr(Value val1, Value val2) throws EvalError{
    checkForBoolValues(val1, val2);
    boolean value1 = ((BoolVal)val1).value;
    if(value1) {
      return new BoolVal(true);
    }
    return new BoolVal(value1 || ((BoolVal)val2).value);
  }

  public void checkForIntValues(Value val1, Value val2) throws EvalError {
    if(!(val1 instanceof IntVal) || !(val2 instanceof IntVal)) {
      throw new EvalError(
        "Evaluation error at BinExp: values are not both of type int for <, arithmetic operators"
      );
    }
  }

  public IntVal evaluateDivide(Value val1, Value val2) throws EvalError {
    // check for 0 for dividend
    checkForIntValues(val1, val2);
    int value2 = ((IntVal)val2).value;
    if(value2 == 0) {
      throw new EvalError(
        "Evaluation error at BinExp: divisor is 0"
      );
    }
    return new IntVal(((IntVal)val1).value / value2);
  }

  public String toString() {
    return expr1.toString() +
      " " + binOp.toString() + " " +
      expr2.toString();
  }
}
