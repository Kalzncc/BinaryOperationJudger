package sys.set;

public class AddConstraints extends Constraints{
	public AddConstraints(int lower, int upper) throws RuntimeException{
		super(lower, upper);
	}
	public boolean check(AddOperation tp) {
		return tp.getop1() >= lower && tp.getop1() <= upper && tp.getop2() >= lower && tp.getop2() <= upper && tp.getAnswer() >= lower && tp.getAnswer() <= upper;
	}
	
}
