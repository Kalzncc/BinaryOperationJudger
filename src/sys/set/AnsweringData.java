package sys.set;

import java.io.IOException;
import java.util.Date;

public class AnsweringData {
	public Date date;
	public int SetNum;
	public int RightNum;
	public int limit;
	public int type;
	public static void main(String[] args) throws IOException {
    }

	public AnsweringData(String te) {
		String[] p = te.split("\\,");
		String[] daString = p[0].split(":");
		date = new Date(Integer.parseInt(daString[0]), Integer.parseInt(daString[1]), Integer.parseInt(daString[2]), Integer.parseInt(daString[3]), 
				Integer.parseInt(daString[4]), Integer.parseInt(daString[5]));
		SetNum = Integer.parseInt(p[1]);
		RightNum = Integer.parseInt(p[2]);
		limit = Integer.parseInt(p[3]);
		type = Integer.parseInt(p[4]);
	}
	public AnsweringData(Judgement jd) {
		date = new Date();
		SetNum = jd.getSize();
		RightNum = jd.getRightNum();
		limit = jd.getSet().getlimit();
		type = jd.getSet().getType();
	}
	@Override
	public String toString() {
		return date.getYear() + ":" + date.getMonth() + ":" + date.getDate() + ":" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "," +
				SetNum + "," + RightNum + "," + limit + "," + type;
	}
}
