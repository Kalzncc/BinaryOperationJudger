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
	//static final String heString = "	本程序由软工二班软件构造小组\n	 齐福领	    马明皓\n	               协作完成";
	static final String heString = "本程序由软工二班软件构造小组";
	static final String miString = "  齐福领  马明皓  协作完成";
	public static void main(String[] args) {

	}
	public AboutWin (JFrame owner) {
	//	owner.setEnabled(false);
		setTitle("关于");
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
