package sys.win;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import sys.set.BinaryOperationBase;
import sys.set.BinaryOperationSet;

public class AddSetWin extends JFrame{
	LoadWin thisown;
	JLabel limitL = new JLabel("限制范围");
	JLabel numL = new JLabel("题目数量");
	JTextField limitF = new JTextField();
	JTextField numF = new JTextField();
	JButton Submit = new JButton("确定");
	JPanel jp = new JPanel(new FlowLayout());
	JCheckBox ptadd = new JCheckBox("加法");
	JCheckBox ptsub = new JCheckBox("减法");
	public AddSetWin(LoadWin own) {
		super("追加练习");
		own.setEnabled(false);
		thisown = own;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(own);
		setSize(350, 160);
		setResizable(false);
		limitF.setPreferredSize(new Dimension(50, 30));
		numF.setPreferredSize(new Dimension(50, 30));
		jp.add(limitL);
		jp.add(limitF);
		jp.add(numL);
		jp.add(numF);
		jp.add(ptadd);
		jp.add(ptsub);
		jp.add(Submit);
		add(jp);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowIconified(WindowEvent e) {
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
			}	
			@Override
			public void windowClosing(WindowEvent e) {
				own.setEnabled(true);
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		JFrame thiso = this;
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String li = limitF.getText();
				String nu = numF.getText();
				if (!li.matches("[\\d]+") || !nu.matches("[\\d]+")) {
					JOptionPane.showConfirmDialog(thiso, "请输入正确的数字", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!ptadd.isSelected() && !ptsub.isSelected()) {
					JOptionPane.showConfirmDialog(thiso, "请从加法/减法中选择至少一个", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (Integer.parseInt(li) >= 1000 || Integer.parseInt(nu) > 1000 || Integer.parseInt(nu) == 0 || Integer.parseInt(li) == 0) {
					JOptionPane.showConfirmDialog(thiso, "请确保范围在1~999之间， 数量在1~999之间", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				int flag = 0;
				if (ptadd.isSelected() && ptsub.isSelected()) flag = BinaryOperationBase.MIX; 
				else if (!ptadd.isSelected()) flag = BinaryOperationBase.SUB;
				else flag = BinaryOperationBase.ADD;
				BinaryOperationSet set;
				BinaryOperationBase base = new BinaryOperationBase(Integer.parseInt(li), flag);
				try {
					set = base.getBinaryOperationSet(Integer.parseInt(nu));
				}
				catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(thiso, "算式基中算式数量不足", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				int res = thisown.area.addSet(set);
				
				if (res != -1) {
					dispose();
					JOptionPane.showConfirmDialog(thisown, "经过查重, 共追加" + res + "个题目","提示" , JOptionPane.CLOSED_OPTION);
					own.setEnabled(true);
					own.isFocusable();
				}
			}
		});
	}
}
