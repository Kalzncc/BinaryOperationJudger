package sys.win;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import sys.set.BinaryOperationBase;
import sys.set.BinaryOperationFiles;
import sys.set.ExerciseFiles;

public class BaseTableWin extends JFrame{
	public static void main (String[] args) {
		new BaseTableWin(null, new BinaryOperationBase(50, 1)).setVisible(true);;
	}
	BinaryOperationBase thisbase;
	JFrame thisown;
	public BaseTableWin (JFrame own, BinaryOperationBase base) {
		super();
		String title = "��ʾ��ʽ��";
		thisbase = base;
		thisown = own;
		String[][] data = base.getTableData();
		title += "-" + base.getLimit() + "֮��-" + (base.getType() != -1 ? "��" : "") + (base.getType() != 1 ? "��" : "") + "��"; 
		setTitle(title);
		setSize(300, 300);
		setLocationRelativeTo(own);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JTable jt;
		JScrollPane jts;
		String[] name = new String[base.getLimit()+1];
		for (int i = 0; i <= base.getLimit(); i++) name[i] = "";
		jt = new JTable((Object[][]) data, (Object[]) name);
		jt.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        jt.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        jt.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        jt.getTableHeader().setReorderingAllowed(false); 
        jt.setRowHeight(30);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jt.setFont(new Font(Font.SERIF, Font.BOLD, 13));
		jt.setPreferredScrollableViewportSize(new Dimension(3000, 3000));
		jt.setEnabled(false);
		jts = new JScrollPane(jt);
		JPanel jp = new JPanel(new BorderLayout());
		jp.add(jts, BorderLayout.CENTER);
		JPanel jpS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton SaveBase = new JButton("������ʽ��");
		jpS.add(SaveBase);
		jp.add(jpS, BorderLayout.SOUTH);
		add(jp);
		
		SaveBase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Dir = FileIO.ReadFile("����ļ�(*.pbs)", "pbs");
				if (Dir.equals("")) return ;
				try {
					String path = Dir;
					if (path.length() < 4 || !path.subSequence(path.length() - 4, path.length()).equals(".pbs")) {
						path += ".pbs";
					}
					ExerciseFiles afile = new ExerciseFiles(path, BinaryOperationFiles.WRITE);
					afile.write(thisbase.getAllSet());
					afile.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(thisown, "�ļ�����ʧ��,�����ļ����Ƿ�Ϸ�", "����", JOptionPane.CLOSED_OPTION);
					return;
				}
				
			}
		});
		
	}
}
