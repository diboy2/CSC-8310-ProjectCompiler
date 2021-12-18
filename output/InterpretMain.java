public class InterpretMain {
    public static void main(String[] args) throws Exception {
		Env emptyEnv = new EnvImp();
		ProjLangParser parser = new ProjLangParser(System.in);
	    	try {
	        	Expr e = parser.input(); //modified parser builds/returns AST
	        	try {
	           		Value v = e.eval(emptyEnv); //eval may result in eval err
		       		System.out.println(v);
	        	} catch(EvalError err) {
	           		 System.out.println(err.getMessage());
	    		}
	       	} catch(Exception exn) {
	        	System.out.println("Syntax error: " + exn.getMessage());
	   		} catch (TokenMgrError terr) {
		   		System.out.println(terr.getMessage());
			}
    }
}
