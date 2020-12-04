package sys.set;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Judgement implements Runnable{
	private String ExerciseFileName;
	private String AnswerFileName;
	private BinaryOperationSet Set;
	private ArrayList<Integer> AnsSet;
	private ArrayList<Integer> WrongAnsIndex;
	private int points;
	private int AllPoints;
	private int GetPoints;
	private int Getcnt;
	Recinfo InfoOwn;
	private String info = "";
	private OutputStreamWriter fout;
	public Judgement (ExerciseFiles EFile, AnswerFiles AFile, int points) throws RuntimeException, IOException{
		Set = EFile.read();
		AnsSet = AFile.read();
		WrongAnsIndex = new ArrayList<Integer>();
		if (Set.getSize() != AnsSet.size()) throw new RuntimeException("Judge exception");
		AllPoints = Set.getSize() * points;
		ExerciseFileName = EFile.getFilename();
		AnswerFileName = AFile.getFilename();
		GetPoints = 0;
		Getcnt = 0;
		InfoOwn = new Recinfo() {
			@Override
			public void putInfo(int curnum, int allnum) {
				return;
			}	
			@Override
			public void EndPut(Judgement w) {
			}		
			@Override
			public void BeginPut() {
			}
		};
		this.points = points;
	}
	public Judgement (BinaryOperationSet set, ArrayList<Integer> ans, int points) throws RuntimeException, IOException{
		Set = set;
		AnsSet = ans;
		WrongAnsIndex = new ArrayList<Integer>();
		if (Set.getSize() != AnsSet.size()) throw new RuntimeException("Judge exception");
		AllPoints = Set.getSize() * points;
		GetPoints = 0;
		Getcnt = 0;
		InfoOwn = new Recinfo() {
			@Override
			public void putInfo(int curnum, int allnum) {
				return;
			}	
			@Override
			public void EndPut(Judgement w) {
			}		
			@Override
			public void BeginPut() {
			}
		};
		this.points = points;
	}
	public ArrayList<Integer> StartJudge () throws IOException {
		info = "";
		GetPoints = 0;
		Getcnt = 0;
		WrongAnsIndex.clear();
		for (int i = 0; i < AnsSet.size(); i++) {
			if (Set.getOperation(i).getAnswer() == AnsSet.get(i)) {
				GetPoints += points;
				Getcnt++;
			}
			else WrongAnsIndex.add(i);
		}
		SaveInfo();
		return WrongAnsIndex;
	}
	public void setInfoOwn (Recinfo own) {
		InfoOwn = own;
	}
	@Override
	public void run() {
		InfoOwn.BeginPut();
		info = "";
		GetPoints = 0;
		Getcnt = 0;
		WrongAnsIndex.clear();
		for (int i = 0; i < AnsSet.size(); i++) {
			if (Thread.currentThread().isInterrupted()) {
				WrongAnsIndex.clear();
				info = "";
				InfoOwn.EndPut(null);
				return;
			}
			if (Set.getOperation(i).getAnswer() == AnsSet.get(i)) {
				GetPoints += points;
				Getcnt++;
			}
			else WrongAnsIndex.add(i);
			InfoOwn.putInfo(i, Set.getSize());
		}
		try {
			SaveInfo();
		} catch (IOException e) {
			InfoOwn.EndPut(null);
			e.printStackTrace();
		}
		InfoOwn.EndPut(this);
	}
	public void SaveInfo() throws IOException {
		info = "";
		ArrayList<String> q = new ArrayList<String>();
		Out("算式总数 : " + Set.getSize(), 0, q);
		Out("正确总数 : " + Getcnt, 0, q);
		if (!WrongAnsIndex.isEmpty()) {
			Out("发现错误答案 : ", 0, q);
			for (int x : WrongAnsIndex) {
				Out("No." + String.valueOf(x+1) + " : " + Set.getOperation(x).toString(true) + " 但是 " + (AnsSet.get(x) == -1 ? "未被作答" : "用户答案为 " + String.valueOf(AnsSet.get(x))), 0, q);
			}
		}
		info = String.join("", q);
	}
	
	public void SaveFile(String Dir) throws Exception {
		File ResultFile = new File(Dir);
		fout = new OutputStreamWriter(new FileOutputStream(ResultFile));
		Out("算式总数 : " + Set.getSize(), 1, null);
		Out("正确总数 : " + Getcnt, 1, null);
		if (!WrongAnsIndex.isEmpty()) {
			Out("发现错误答案 : ", 1, null);
			for (int x : WrongAnsIndex) {
				Out("No." + String.valueOf(x+1) + " : " + Set.getOperation(x).toString(true) + " 但是 " + (AnsSet.get(x) == -1 ? "未被作答" : "用户答案为 " + String.valueOf(AnsSet.get(x))), 1, null);
			}
		}
		fout.close();
	}
	private void Out(String te, int flag, ArrayList<String> a) throws IOException{
		if (flag == 0) {
			a.add(te +"\n");
		}
		else  {
			fout.write(te + "\n");
		}
	}
	public int getSize() {
		return Set.getSize();
	}
	public int getRightNum() {
		return Getcnt;
	}
	public int getPoint() {
		return GetPoints;
	}
	public ArrayList<Integer> getWrongAns() {
		return WrongAnsIndex;
	}
	public BinaryOperationSet getSet() {
		return Set;
	}
	public String getInfo () {
		return info;
	}
	public void printResult() {
		Set.print(true);
	}
	
}
