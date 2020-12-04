package sys.set;

public abstract class BinaryOperation {
	protected int op1, op2;
	protected char op;
	public BinaryOperation(char op, Constraints Con) throws RuntimeException{
		if (op != '+' && op != '-') throw new RuntimeException("Operation exception");
		this.op = op;
		if (op == '+') {
			op1 = Con.getMin(); op2 = Con.getMax() - Con.getMin();
		}
		else {
			op1 = Con.getMax(); op2 = Con.getMax() - Con.getMin();
		}
	}
	public BinaryOperation(char op, int op1, int op2) {
		this.op = op;
		this.op1 =op1;
		this.op2 = op2;
	}
	public int getop1() {return op1;}
	public int getop2() {return op2;}
	public char getop() {return op;}
	public boolean equal(BinaryOperation b) {
		return op1 == b.op1 && op2 == b.op2 && op == b.op;
	}
	public int hashCode() {
		return ( op1 * 10004 + (op == '+' ? 1 : 0) ) * 1004 + op2;
	}
	
	public abstract int getAnswer ();
	public abstract String toString(boolean flag);
}
