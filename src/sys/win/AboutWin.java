package sys.win;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AboutWin extends JDialog{
	//static final String heString = "	���������������������С��\n	 �븣��	    �����\n	               Э�����";
	static final String heString = "���������������������С��";
	static final String miString = "  �븣��  �����  Э�����";
	public static void main(String[] args) {

	}
	public AboutWin (JFrame owner) {
	//	owner.setEnabled(false);
		setTitle("����");
		setSize(360, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		owner.setEnabled(false);
		addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				owner.setEnabled(true);				
			}
			@Override
			public void windowActivated(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowOpened(WindowEvent e) {}
		});
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		JPanel he = new JPanel(fl);
		JLabel heL = new JLabel(heString);
		JLabel miL = new JLabel(miString);
		fl.setHgap(20);
		fl.setVgap(20);
		he.add(heL);
		he.add(miL);
		add(he);
	}
	
}
