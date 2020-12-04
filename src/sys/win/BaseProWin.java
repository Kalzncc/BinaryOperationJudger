package sys.win;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import sys.set.BinaryOperationSet;

public class BaseProWin extends JFrame{
	LoadWin thisown;
	BinaryOperationSet thisSet;
	JPanel jp = new JPanel(new FlowLayout());
	JLabel jti;
	JTextField jf = new JTextField();
	JLabel jL = new JLabel("产生题目数");
	JButton Submit = new JButton("确定");
	public static void main(String[] args) {
		new BaseProWin(new LoadWin(), new BinaryOperationSet(100, 30, BinaryOperationSet.MIX)).setVisible(true);;
	}
	public BaseProWin(LoadWin own, BinaryOperationSet set) {
		super("产生练习");
		thisown = own; thisSet = set;
		thisown.setEnabled(false);
		String s = "                      共读入:" + set.getSize() + "道题目                          ";
		jti = new JLabel(s);
		setSize(300, 160);
		setLocationRelativeTo(thisown);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jp.add(jti);
		jf.setPreferredSize(new Dimension(50, 30));
		jp.add(jL);
		jp.add(jf);
		jp.add(Submit);
		add(jp);
		
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = jf.getText();
				if(!num.matches("[\\d]+")) {
					JOptionPane.showConfirmDialog(thisown, "请输入正确的数字", "错误", JOptionPane.CLOSED_OPTION);
					return; 
				}
				int nump = Integer.parseInt(num);
				if (nump >= 1000) {
					JOptionPane.showConfirmDialog(thisown, "数量在0~999之间", "错误", JOptionPane.CLOSED_OPTION);
					return; 
				}
				BinaryOperationSet set = null;
				try {
				 set = thisSet.RandomProSet(Integer.parseInt(num));
				}
				catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(thisown, "数量应小于文件总量", "错误", JOptionPane.CLOSED_OPTION);
				}
				if (thisown.area.initSet(set)) {
					thisown.setEnabled(true);
					dispose();
				}
			}
		});
		
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
				thisown.setEnabled(true);
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		
	}
}
