public interface Env {
    public Value lookup(String id) throws UnboundVar;
    public Env addBinding(String id, Value v);
    public Env updateBinding(String id, Value v) throws UnboundVar;
    public String toString();
}
