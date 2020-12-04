package sys.set;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExerciseFiles extends BinaryOperationFiles{
	
	public ExerciseFiles(String Dir, String type) throws RuntimeException, FileNotFoundException{
		super(Dir, type);
	}
	public void write(BinaryOperationSet tp) throws RuntimeException, IOException{
		if (type != WRITE) throw new RuntimeException("Type exception");
		for (BinaryOperation x : tp) {
			fout.writeInt(x.getop1());
			fout.writeBoolean(x.getop() == '+');
			fout.writeInt(x.getop2());
		}
	}
	public BinaryOperationSet read() throws RuntimeException, IOException {
		BinaryOperationSet tps = new BinaryOperationSet();
		if (type != READ) throw new RuntimeException("Type exception");
		int op1 = 0, op2 = 0; char op;
		while(true){
			try {
				op1 = fin.readInt();
				op = fin.readBoolean() ? '+' : '-';
				op2 = fin.readInt();
				if (op1 < 0 || op2 < 0) throw new RuntimeException();
				if (op == '+')
					tps.add(new AddOperation(op1, op2));
				else if (op == '-')
					tps.add(new SubOperation(op1, op2));
				else {
					throw new RuntimeException();
				}
			}
			catch (IOException e) {
				
				return tps;
			}
			
		}
	}

}
