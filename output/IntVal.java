public class IntVal extends Value {
    public final int value;

    public IntVal(int _value) {
	    value = _value;
    }
    public String toString() {
	    return String.valueOf(value);
    }
}
