package sys.set;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AnswerFiles extends BinaryOperationFiles{
	public AnswerFiles (String Dir, String type) throws RuntimeException, FileNotFoundException{
		super(Dir, type);
	}
	public void write (ArrayList<Integer> ans) throws IOException {
		if (type != WRITE) throw new RuntimeException("Type Exception");
		for (Integer x : ans) {
			fout.writeInt(x);
		}
	}
	public ArrayList<Integer> read() throws IOException, RuntimeException {
		if (type != READ) throw new RuntimeException("Type Exception"); 
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int res;
		while(true) {
			try {
				res = fin.readInt();
				ans.add(res);
				if (res < -1) throw new RuntimeException();
			} catch (IOException e) {
				return ans;
			}
		}
	}
}
