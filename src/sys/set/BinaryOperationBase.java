package sys.set;


import java.io.IOException;
import java.util.Random;




public class BinaryOperationBase {
	public static final int ADD = 1, SUB = -1, MIX = 0;
	private int limit;
	private int type;
	private BinaryOperationSet base;
	public BinaryOperationBase(int limit, int type){
		if (type > 1 || type < -1) throw new RuntimeException("Type exception");
		this.limit = limit;
		this.type = type;
		base = new BinaryOperationSet();
		if (type == 1 || type == 0) {
			for (int op1 = 0; op1 <= this.limit; op1++) {
				for (int op2 = 0; op2 <= this.limit; op2++) {
					AddOperation te = new AddOperation(op1, op2);
					if (te.getAnswer() > this.limit) continue;
					base.add(te);
				}
			}
		}
		if (type == -1 || type == 0) {
			for (int op1 = 0; op1 <= this.limit; op1++) {
				for (int op2 = 0; op2 <= this.limit; op2++) {
					SubOperation te = new SubOperation(op1, op2);
					if (te.getAnswer() > this.limit || te.getAnswer() < 0) continue;
					base.add(te);
				}
			}
		}	
	}
	public void write(String Dir) throws RuntimeException, IOException{
		ExerciseFiles basefile = new ExerciseFiles(Dir, BinaryOperationFiles.WRITE);
		basefile.write(base);
		basefile.close();
	}
	public void print() {
		base.print(true);
	}
	
	public BinaryOperationSet getBinaryOperationSet(int cnt) throws RuntimeException, IOException{
		if (cnt > base.getSize()) throw new RuntimeException("BinaryOperation number too low");
		Random rin = new Random();
		BinaryOperationSet res = new BinaryOperationSet();
		while(res.getSize() < cnt) {
			int index = rin.nextInt(base.getSize());
			res.add(base.getOperation(index));
			base.removeOperation(index);
		}
		return res;
	}
	
	public String[][] getTableData() {
		String[][] data = new String[limit+1][limit+1]; 
		for (int i = 0; i <= limit; i++) {
			for (int j = 0; j <= limit; j++) {
				data[i][j] = "";
				if (i +j < limit && (type == 1 || type == 0)) {
					data[i][j] = new AddOperation(i, j).toString(false);
				}
				else if (i + j >= limit && (type == -1 || type == 0)) {
					data[i][j] = new SubOperation(i, limit - j).toString(false);
				}
			}
		}
		return data;
	}
	
	public BinaryOperationSet getAllSet() {
		return base;
	}
	
	public static BinaryOperationSet ExternProSet (BinaryOperationSet set, int cnt) {
		if (cnt > set.getSize()) throw new RuntimeException("BinaryOperation number too low");
		Random rin = new Random();
		BinaryOperationSet res = new BinaryOperationSet();
		while(res.getSize() < cnt) {
			int index = rin.nextInt(set.getSize());
			res.add(set.getOperation(index));
			set.removeOperation(index);
		}
		return res;
	}
	
	public static BinaryOperationSet getBinaryOperationSet(String Dir, int cnt) throws RuntimeException, IOException{
		ExerciseFiles te = new ExerciseFiles(Dir, BinaryOperationFiles.READ);
		BinaryOperationSet base = te.read();
		if (cnt > base.getSize()) throw new RuntimeException("BinaryOperation number too low");
		Random rin = new Random();
		BinaryOperationSet res = new BinaryOperationSet();
		while(res.getSize() < cnt) {
			int index = rin.nextInt(base.getSize());
			res.add(base.getOperation(index));
			base.removeOperation(index);
		}
		te.close();
		return res;
	}
	
	public int getLimit() {
		return limit;
	}
	public int getType() {
		return type;
	}
}
