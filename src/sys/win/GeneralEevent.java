package sys.win;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import sys.net.DownLoadData;
import sys.set.*;

public class GeneralEevent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void NetSet(LoadWin thisown) {
		new NewSetWin(thisown).setVisible(true);
	}
	public static void OpenSet(LoadWin thisown) {
		String Dir = FileIO.ReadFile("����ļ�(*.pbs)",  "pbs");
		if (Dir.equals("")) return;
		ExerciseFiles afile;
		try {
			 afile = new ExerciseFiles(Dir, BinaryOperationFiles.READ);
		} catch (FileNotFoundException e1) {
			JOptionPane.showConfirmDialog(thisown, "δ�ҵ�·��", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		} catch (RuntimeException e1) {
			JOptionPane.showConfirmDialog(thisown, "�����ļ�����", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		}
		BinaryOperationSet set = null;
		try {
			set = afile.read();
		} catch (RuntimeException | IOException e1) {
			JOptionPane.showConfirmDialog(thisown, "�����ļ�����", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		}
		thisown.area.initSet(set);
	}
	public static void SaveSet(LoadWin thisown) {
		String Dir = FileIO.SaveFile("����ļ�(*.pbs)", "pbs");
		if (Dir.equals("")) return;
		try {
			String path = Dir;
			if (path.length() < 4 || !path.subSequence(path.length() - 4, path.length()).equals(".pbs")) {
				path += ".pbs";
			}
			ExerciseFiles afile = new ExerciseFiles(path, BinaryOperationFiles.WRITE);
			afile.write(thisown.area.thisSet);
			afile.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(thisown, "�ļ�����ʧ��,�����ļ����Ƿ�Ϸ�", "����", JOptionPane.CLOSED_OPTION);
			return;
		}
	}
	public static void AddSet(LoadWin thisown) {
		new AddSetWin(thisown).setVisible(true);;
	}
	public static void ImportAns(LoadWin thisown) {
		String Dir = FileIO.ReadFile("���ļ�(*.ans)", "ans");
		if (Dir.equals("")) return;
		try {
			AnswerFiles afile = new AnswerFiles(Dir, BinaryOperationFiles.READ);
			ArrayList<Integer> ans = afile.read();
			afile.close();
			thisown.area.initAns(ans);
		}
		catch (Exception e2) {
			JOptionPane.showConfirmDialog(thisown, "�����ļ�������ߴ��뵱ǰ��ϰ��ƥ��", "����", JOptionPane.CLOSED_OPTION);
			e2.printStackTrace();
			return;
		}
	}
	public static void SaveAns(LoadWin thisown) {
		String Dir = FileIO.SaveFile("���ļ�(*.ans)", "ans");
		if (Dir.equals("")) return;
		try {
			String path = Dir;
			if (path.length() < 4 || !path.subSequence(path.length() - 4, path.length()).equals(".ans")) {
				path += ".ans";
			}
			ArrayList<Integer> ans = thisown.area.getAns();
			AnswerFiles afile = new AnswerFiles(path, BinaryOperationFiles.WRITE);
			afile.write(ans);
			afile.close();
		}
		catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(thisown, "�ļ�����ʧ��,�����ļ����Ƿ�Ϸ�", "����", JOptionPane.CLOSED_OPTION);
			return;
		}
	}
	public static void CheBase (LoadWin thisown) {
		new NewBaseWin(thisown).setVisible(true);
	}
	public static void GetFile(LoadWin thisown) {
		String Dir = FileIO.ReadFile("����ļ�(*.pbs)",  "pbs");
		if (Dir.equals("")) return;
		ExerciseFiles afile;
		try {
			 afile = new ExerciseFiles(Dir, BinaryOperationFiles.READ);
		} catch (FileNotFoundException e1) {
			JOptionPane.showConfirmDialog(thisown, "δ�ҵ�·��", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		} catch (RuntimeException e1) {
			JOptionPane.showConfirmDialog(thisown, "�����ļ�����", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		}
		BinaryOperationSet set = null;
		try {
			set = afile.read();
		} catch (RuntimeException | IOException e1) {
			JOptionPane.showConfirmDialog(thisown, "�����ļ�����", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		}
		new BaseProWin(thisown, set).setVisible(true);
	}
	public static void TestStart(LoadWin thisown, Judgement jd) {
		int res = JOptionPane.showConfirmDialog(thisown, "�ύ��, �������������,�������¼(��������1000�Ĵ��ļ������¼), �޷��ٱ��޸�. ȷ��Ҫ�ύ��", "�ύ��", JOptionPane.YES_NO_OPTION);
		if (res != JOptionPane.YES_OPTION) return;
		Date EndTime = new Date();
		ArrayList<Integer> ans;
		ans = thisown.area.getAns();
		ArrayList<Integer> WrongAns = null;
		String info;
		try {
			jd = new Judgement(thisown.area.thisSet, ans, 1);
			if(jd.getSet().getSize() >= 1000) {
				jd.setInfoOwn(thisown.iarea);
				Thread td = new Thread(jd);
				td.start();
				WrongAns = jd.getWrongAns();
				info = jd.getInfo();
			}
			else {
				WrongAns = jd.StartJudge();
				info = jd.getInfo();
				WinStatus.setTested(thisown);
				int num = WrongAns.size();
				int cnt = thisown.area.thisSet.getSize();
				int p = (cnt - num) * 100 / cnt;
				thisown.iarea.bar.setForeground(Color.green);
				thisown.iarea.bar.setBackground(Color.red);
				thisown.area.setStatus(WrongAns);
                thisown.iarea.bar.setValue(p);
				Date da = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String pp = Timer.getTime(EndTime, thisown.area.startTime);
				thisown.iarea.tArea.setText("====" + df.format(da) + "====\n" + "�ܺ�ʱ:" + pp + "\n" + info);

				thisown.marea.jd = jd;
				AnsweringData data = new AnsweringData(jd);
				try {
					RecFile file = new RecFile("DownLoad/.LineChartData", BinaryOperationFiles.APPEND);
					file.AddData(data);
					file.close();
				}
				catch (Exception e) {
					JOptionPane.showConfirmDialog(thisown, "�޷�д���¼�ļ�", "����", JOptionPane.CLOSED_OPTION);
					return;
				}
			}
			
		}
		catch (Exception e1) {
			JOptionPane.showConfirmDialog(thisown, "�����쳣", "����", JOptionPane.CLOSED_OPTION);
			e1.printStackTrace();
			return;
		}
		
	}
	public static void ImportTest(LoadWin thisown, Judgement jd) {
		String Dir = FileIO.SaveFile("����ļ�(*.res)", "res");
		try {
			String path = Dir;
			if (path.length() < 4 || !path.subSequence(path.length() - 4, path.length()).equals(".res")) {
				path += ".res";
			}
			jd.SaveFile(path);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(thisown, "�ļ�����ʧ��,�����ļ����Ƿ�Ϸ�", "����", JOptionPane.CLOSED_OPTION);
			return;
		}
	}
	public static void CheLineChart(LoadWin thisown) {
		new LineChartWin(thisown).setVisible(true);
	}
	public static void CheChart(LoadWin thisowm, Judgement jd) {
		new ChartWin(thisowm, jd).setVisible(true);
	}
	public static void CheNetChart(LoadWin thisown) {
		NetDownload rec = new NetDownload(thisown);
		DownLoadData rdata = new DownLoadData(rec);
		Thread th = new Thread(rdata);
		th.start();
	}
	public static void AddNetChart(LoadWin thisown, Judgement jd) {
		AnsweringData data = new AnsweringData(jd);
		NetUpload up = new NetUpload(thisown, data);
		up.StartUpload();
	}
	public static void OpenHelpWin(LoadWin thisown) {
		new AboutWin(thisown).setVisible(true);
	}
	
}
