package sys.win;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import sys.set.BinaryOperation;
import sys.set.BinaryOperationSet;


public class AnswerArea extends JPanel {
	SpringLayout jpsl = new SpringLayout();
	JPanel jps = new JPanel(jpsl);
	JPanel jpb = new JPanel(new BorderLayout());
	JButton jb = new JButton("提交答案");
	JButton jpre = new JButton("上一页");
	JButton jnxt = new JButton("下一页");
	JButton jjump = new JButton("跳转");
	JTextField jf = new JTextField();
	JLabel jpag = new JLabel();
	public int curpag = 0;
	public int allpag = 0;
	LoadWin thisown;
	BinaryOperationSet thisSet = null;
	ProblemCom[] setCom;
	Date startTime;
	public AnswerArea(LoadWin own) {
		super(new BorderLayout());
		thisown = own;
		add(jps, BorderLayout.CENTER);
		JPanel jlp = new JPanel();

		jf.setPreferredSize(new Dimension(50, 30));
		JPanel jw = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jw.add(jf);
		jw.add(jjump);
		jpb.add(jw, BorderLayout.WEST);
		jlp.add(jpag, BorderLayout.CENTER);
		jpb.add(jlp);
		JPanel je = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		je.add(jpre);
		je.add(jnxt);
		je.add(jb);
		jpb.add(je, BorderLayout.EAST);
		add(jpb, BorderLayout.SOUTH);
		initAction();

	}
	public boolean initSet(BinaryOperationSet set) {
		if (thisSet != null) {
			int p = JOptionPane.showConfirmDialog(this, "窗口中已经有练习, 如果新建(追加)练习,, 已有的练习(作答)会丢失, 确定要继续吗?", "提示", JOptionPane.YES_NO_OPTION);
			if(p!=JOptionPane.YES_OPTION) return false;
		}
		if (set.getSize() < 1000) {
			thisSet = set;
			WinStatus.setAnsering(thisown);
			thisown.iarea.bar.setIndeterminate(false);
			thisown.iarea.tArea.setText("");
			thisown.iarea.bar.setValue(0);
			thisown.iarea.bar.setForeground(Color.cyan);
			thisown.iarea.bar.setBackground(Color.white);
			setCom = new ProblemCom[thisSet.getSize()];
			allpag = thisSet.getSize() / 27;
			
			if (thisSet.getSize() % 27 != 0) allpag++;
			for (int i = 0; i < thisSet.getSize(); i++) {
				setCom[i] = new ProblemCom(i+1, thisSet.getOperation(i), jpsl, jps, thisown);
			}
			curpag = 1;
			openPag(1);
			startTime = new Date();
		}
		else {
			int res = JOptionPane.showConfirmDialog(thisown, "练习题数量多!太大的练习量程序可能崩溃, 显示异常. 是否继续?", "巨大练习量", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				new FileLoadWin(thisown, set).setVisible(true);;
			}
		}
		
		return true;
	}
	public int addSet(BinaryOperationSet set) {
		Set<Integer> ha = new TreeSet<Integer>();
		for (BinaryOperation x : thisSet) {
			ha.add(x.hashCode());
		}
		int cnt = 0;
		for (BinaryOperation x : set) {
			if (ha.contains(x.hashCode())) continue;
			thisSet.add(x);
			cnt++;
		}
		if (!initSet(thisSet)) cnt = -1;
		return cnt;
	}
	public void openPag(int newpag) {
		jpag.setText("第" + newpag + "页 / 共" + allpag + "页");
		jps.removeAll();
		int st = (newpag - 1) * 27;
		int en = newpag * 27;
		if (en > thisSet.getSize()) { en = thisSet.getSize(); }
		jnxt.setEnabled(true);
		jpre.setEnabled(true);
		if (newpag == allpag)
			jnxt.setEnabled(false);
		if (newpag  == 1)
			jpre.setEnabled(false);
		for (int i = st; i < en; i++) {
			setCom[i].setOpen();
		}
		
		jps.revalidate();
		jps.repaint();
	}
	private void nextPag() {
		curpag++;
		openPag(curpag);
	}
	private void prePag() {
		curpag--;
		openPag(curpag);
	}
	public boolean initAns(ArrayList<Integer> ans) throws RuntimeException{
		if (thisSet != null) {
			int p = JOptionPane.showConfirmDialog(this, "此举会覆盖已有作答, 确定要继续吗?", "提示", JOptionPane.YES_NO_OPTION);
			if(p!=JOptionPane.YES_OPTION) return false;
		}
		if (thisSet == null || thisSet.getSize() != ans.size()) throw new RuntimeException();
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i) != -1)
				setCom[i].AnswerF.setText(String.valueOf(ans.get(i)));
			else setCom[i].AnswerF.setText("");
		}
		return true;
	}
	
	private void initAction() {
		jpre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prePag();
			}
		});
		jnxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextPag();
			}
		});
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.TestStart(thisown, thisown.marea.jd);
			}
		});
		jjump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pag = jf.getText();
				if (!pag.matches("[\\d]+")) return;
				int num = Integer.parseInt(pag);
				if (num == 0 || num > allpag) return;
				curpag = num;
				openPag(num);
			}
		});
		addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (curpag == 0) return;
                if (e.getWheelRotation() == -1) {
                    if (curpag > 1)
                        prePag();
                }
                else {
                    if (curpag < allpag)
                        nextPag();
                }
            }
        });
	}
	
	public ArrayList<Integer> getAns() {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (ProblemCom x : setCom) {
			String anss = x.AnswerF.getText();
			if (!anss.matches("[\\d]+")) ans.add(-1);
			else ans.add(Integer.parseInt(anss));
		}
		return ans;
	}
	public void setStatus (ArrayList<Integer> wans) {
		boolean[] bs = new boolean[thisSet.getSize()];
		for (int i = 0; i < thisSet.getSize(); i++) bs[i] = false;
		for (int x : wans) {
			bs[x] = true;
		}
		for (int i = 0; i < thisSet.getSize(); i++) {
			thisown.iarea.tArea.setText("答案展示中..\n" + "(" + i + "/" + thisSet.getSize() + ")");
			setCom[i].setStatus(bs[i]);				
			setCom[i].AnswerF.setEditable(false);
			thisown.iarea.bar.setValue(100 * i / thisSet.getSize());
		}
	}


}
