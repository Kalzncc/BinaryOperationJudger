package sys.set;

public class SubConstraints extends Constraints{
	public SubConstraints(int lower, int upper) throws RuntimeException {
		super(lower, upper);
	}
	public boolean check(SubOperation tp) {
		return tp.getop1() >= lower && tp.getop1() <= upper && tp.getop2() >= lower && tp.getop2() <= upper && tp.getAnswer() >= lower && tp.getAnswer() <= upper;
	}
}
