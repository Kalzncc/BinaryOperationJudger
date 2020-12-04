package sys.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class BinaryOperationSet implements Iterable<BinaryOperation> {
	public final static int MIX = 3, ADD = 1, SUB = 2;
	private int type;
	private int Line;
	private int limit;
	private ArrayList<BinaryOperation> list = new ArrayList<BinaryOperation>();
	private Constraints con;
	private Random rin;
	public BinaryOperationSet(int number, int limit, int type) throws RuntimeException{
		if (type != MIX && type != ADD && type != SUB) throw new RuntimeException("Type execption");
		this.type = type;
		this.limit = limit;
		Line = 6;
		con = new Constraints(0, limit);
		if (type == ADD) {
			while(list.size() != number) {
				AddOperation te;
				con.flush();
				while(!check(te = new AddOperation(con)))
					con.flush();
				list.add(te);
			}
		}
		else if (type == SUB) {
			while(list.size() != number) {
				SubOperation te;
				con.flush();
				while(!check(te = new SubOperation(con)))
					con.flush();
				list.add(te);
			}
		}
		else {
			rin = new Random();
			int flag = rin.nextInt(2);
			while(list.size() != number) {
				if (flag == 1) {
					AddOperation te;
					con.flush();
					while(!check(te = new AddOperation(con)))
						con.flush();
					list.add(te);
				}
				else {
					SubOperation te;
					con.flush();
					while(!check(te = new SubOperation(con)))
						con.flush();
					list.add(te);
				}
				flag = rin.nextInt(2);
			}
		}
	}
	public BinaryOperationSet() {
		Line = 6;
		type = 0;
	}
	private boolean check(BinaryOperation tp) {
		for (BinaryOperation x : list) {
			if (tp.equal(x)) return false;
		}
		return true;
	}
	public void setLine(int line) {
		this.Line = line;
	}
	public void print(boolean flag) {
		String t;
		if (type == MIX) t = "add and sub";
		else if (type == SUB) t = "sub";
		else t = "add";
		System.out.printf("<<<<<<   There are %d BinaryOperation(s) include " + t + " with" + (flag? "":"out") + " answer   >>>>>>\n", list.size());
		for (int i = 0; i < list.size(); i++){
			if (i != 0 && i % Line == 0) System.out.println();
			System.out.printf("(%03d):%-15s", i+1, list.get(i).toString(flag));
		}
		System.out.println();
	}
	public BinaryOperation getOperation(int index) {
		return list.get(index);
	}
	public void removeOperation(int index) {
		list.remove(index);
	}
	public int getSize() {
		return list.size();
	}
	public int getlimit() {
		return limit;
	}
	public int getType() {
		return type;
	}
	public void add(BinaryOperation tp) {
		list.add(tp);
		limit = Math.max(tp.op1, limit);
		limit = Math.max(tp.op2, limit);
		type |= (tp.getop() == '+' ? 1 : 2);
	}
	public BinaryOperationSet RandomProSet(int cnt) throws RuntimeException {
		if (cnt > list.size()) throw new RuntimeException();
		BinaryOperationSet set = BinaryOperationBase.ExternProSet(this, cnt);
		return set;
	}
	@Override
	public Iterator<BinaryOperation> iterator() {
		// TODO Auto-generated method stub
		class iter implements Iterator<BinaryOperation>
        {
			int cur = 0;
            @Override
            public boolean hasNext() {
            	return cur < list.size();
            }
            @Override
            public BinaryOperation next() {
               return list.get(cur++);
            }
            @Override
            public void remove() {} 
        }
        return new iter();
	}
}
