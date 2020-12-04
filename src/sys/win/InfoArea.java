package sys.win;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sys.set.Judgement;
import sys.set.Recinfo;


public class InfoArea extends JPanel implements Recinfo {
	JProgressBar bar = new JProgressBar();
	JTextArea tArea = new JTextArea("");
	JTextArea nArea = new JTextArea("");
	JButton js = new JButton("输出评测信息到文件");
	
	LoadWin thisown;
	public InfoArea(LoadWin JJ) {
		thisown = JJ;
		JLabel jl = new JLabel("欢迎使用口算练习系统");
		add(jl);
		Timer timer = new Timer();
		Thread thr = new Thread(timer);
		thr.start();
		add(timer);
		initNText();
		initBar();
		initTestS();
		
		
		
		
		js.setEnabled(false);
		add(js);
		initAction();
		
	}
	private void initTestS () {
		JLabel jn = new JLabel("评测信息");
		add(jn);
		tArea.setLineWrap(true);
		tArea.setEditable(false);
		
		JScrollPane tAreaS = new JScrollPane(tArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tAreaS.setPreferredSize(new Dimension(275, 150));
		add(tAreaS);
	}
	private void initNText() {
		JLabel jn = new JLabel("今日任务");
		add(jn);
		try {
			File file = new File("DownLoad/.sign");
			InputStreamReader fin = new InputStreamReader(new FileInputStream(file), "utf-8");
			int len; ArrayList<String> te = new ArrayList<String>();
			String sign = "";
			while((len = fin.read()) != -1) te.add(String.valueOf((char)len));
			sign = String.join("", te);
			 nArea.setText(sign);
			 fin.close();
		}
		catch (Exception e) {
			nArea.setText("打开文件失败");
		}
		nArea.setLineWrap(true);
		JScrollPane nAreaS = new JScrollPane(nArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		nAreaS.setPreferredSize(new Dimension(275, 100));
		add(nAreaS);
	}
	private void initBar() {
		JLabel ti = new JLabel("题目状态");
		add(ti);
		bar.setMinimum(0);
		bar.setMaximum(100);
		bar.setValue(0);
		bar.setIndeterminate(true);
		bar.setStringPainted(true);
		bar.setPreferredSize(new Dimension(275, 20));
		bar.setBackground(Color.white);
		add(bar);
	}
	private void initAction() {
		js.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.ImportTest(thisown, thisown.marea.jd);
			}
		});
		nArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				File file = new File("DownLoad/.sign");
				try {
					OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
					fout.write(nArea.getText());
					fout.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				File file = new File("DownLoad/.sign");
				try {
					OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
					fout.write(nArea.getText());
					fout.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				File file = new File("DownLoad/.sign");
				try {
					OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
					fout.write(nArea.getText());
					fout.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	public void changeBar() {
		int cnt = 0, sum = 0;
		for (ProblemCom x : thisown.area.setCom) {
			if (!x.AnswerF.getText().equals("")) cnt++;
			sum++;
		}
		int p = 100 * cnt / sum;
		bar.setValue(p);
	}
	@Override
	public void putInfo(int curnum, int allnum) {
		tArea.setText("大量试题评测中..\n" + "(" + curnum + "/" + allnum + ")");
		thisown.iarea.bar.setValue(100 * curnum / allnum);
	}
	@Override
	public void BeginPut() {
		WinStatus.setLoading(thisown);
		thisown.area.jb.setEnabled(false);
		thisown.iarea.js.setEnabled(false);
		thisown.iarea.bar.setValue(0);
	}
	@Override
	public void EndPut(Judgement jd) {
		if (jd == null) {
			tArea.setText("失败! 没有完成评测");
			thisown.iarea.changeBar();
			return;
		}
		tArea.setText("展示答案中....");
		thisown.iarea.bar.setValue(0);
		thisown.area.setStatus(jd.getWrongAns());
		WinStatus.setunLoading(thisown);
		WinStatus.setTested(thisown);
		thisown.area.openPag(thisown.area.curpag);
		int num = jd.getWrongAns().size();
		int cnt = thisown.area.thisSet.getSize();
		int p = (cnt - num) * 100 / cnt;
		thisown.iarea.bar.setValue(p);
		thisown.iarea.bar.setForeground(Color.green);
		thisown.iarea.bar.setBackground(Color.red);
		Date da = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		thisown.iarea.tArea.setText("====" + df.format(da) + "====\n评测完成, 评测信息过多,请输出至文件查看");
		thisown.marea.jd = jd;
	}
}
