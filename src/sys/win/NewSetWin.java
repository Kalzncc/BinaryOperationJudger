package sys.win;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import sys.set.BinaryOperationBase;
import sys.set.BinaryOperationSet;



public class NewSetWin extends JFrame {
	JLabel limitL = new JLabel("���Ʒ�Χ");
	JLabel numL = new JLabel("��Ŀ����");
	JTextField limitF = new JTextField();
	JTextField numF = new JTextField();
	JButton Submit = new JButton("ȷ��");
	JPanel jp = new JPanel(new FlowLayout());
	JCheckBox ptadd = new JCheckBox("�ӷ�");
	JCheckBox ptsub = new JCheckBox("����");
	public NewSetWin(LoadWin own) {
		super("�½���ϰ");
		own.setEnabled(false);
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
					JOptionPane.showConfirmDialog(thiso, "��������ȷ������", "��ʾ", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!ptadd.isSelected() && !ptsub.isSelected()) {
					JOptionPane.showConfirmDialog(thiso, "��Ӽӷ�/������ѡ������һ��", "��ʾ", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (Integer.parseInt(li) >= 1000 || Integer.parseInt(nu) > 1000 || Integer.parseInt(nu) == 0 || Integer.parseInt(li) == 0) {
					JOptionPane.showConfirmDialog(thiso, "��ȷ����Χ��1~999֮�䣬 ������1~999֮��", "��ʾ", JOptionPane.CLOSED_OPTION);
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
					JOptionPane.showConfirmDialog(thiso, "��ʽ������ʽ��������", "��ʾ", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (own.area.initSet(set)) {
					dispose();
					own.setEnabled(true);
					own.isFocusable();
				}
			}
		});
	}
}
