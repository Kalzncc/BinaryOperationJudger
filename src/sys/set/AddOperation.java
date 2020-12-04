package sys.set;

public class AddOperation extends BinaryOperation{
	public AddOperation(Constraints Con) {
		super('+', Con);
	}
	
	public AddOperation(int op1, int op2) {
		super('+', op1, op2);
	}
	public int getAnswer() {
		return op1 + op2;
	}
	public String toString(boolean flag) {
		return String.valueOf(op1) + String.valueOf(op) + String.valueOf(op2)  + (flag ? "=" + String.valueOf(op1 + op2) : "");
	}
}
