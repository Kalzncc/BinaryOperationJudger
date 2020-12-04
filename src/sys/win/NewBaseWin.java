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

public class NewBaseWin extends JFrame{
	JLabel limitL = new JLabel("限制范围");
	JTextField limitF = new JTextField();
	JButton Submit = new JButton("确定");
	JPanel jp = new JPanel(new FlowLayout());
	JCheckBox ptadd = new JCheckBox("加法");
	JCheckBox ptsub = new JCheckBox("减法");
	public static void main (String[] args) {
		new NewBaseWin(null).setVisible(true);
	}
	public NewBaseWin(LoadWin own) {
		super("查看算式基");
		own.setEnabled(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(own);
		setSize(350, 160);
		setResizable(false);
		limitF.setPreferredSize(new Dimension(50, 30));
		jp.add(limitL);
		
		jp.add(limitF);
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
			//	String nu = numF.getText();
				if (!li.matches("[\\d]+") ) {
					JOptionPane.showConfirmDialog(thiso, "请输入正确的数字", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!ptadd.isSelected() && !ptsub.isSelected()) {
					JOptionPane.showConfirmDialog(thiso, "请从加法/减法中选择至少一个", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (Integer.parseInt(li) >= 1000 || Integer.parseInt(li) == 0) {
					JOptionPane.showConfirmDialog(thiso, "请确保范围在1~999之间", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				int flag = 0;
				if (ptadd.isSelected() && ptsub.isSelected()) flag = BinaryOperationBase.MIX; 
				else if (!ptadd.isSelected()) flag = BinaryOperationBase.SUB;
				else flag = BinaryOperationBase.ADD;
				BinaryOperationBase base = new BinaryOperationBase(Integer.parseInt(li), flag);
				own.setEnabled(true);
				new BaseTableWin(own, base).setVisible(true);
				dispose();
			}
		});
	}
}
