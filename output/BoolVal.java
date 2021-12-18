public class BoolVal extends Value {
    public final boolean value;

    public BoolVal(boolean _value) {
        value = _value;
    }

    public String toString() {
	    return String.valueOf(value);
    }
}
