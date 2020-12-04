package sys.win;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.jar.JarOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

public class LoadWin extends JFrame{
	JMenuBar Bar;
	JPanel jp;
	JSplitPane janswering;
	JButton jpl = new JButton();
	JButton jpr = new JButton();
	MenuBarArea marea;
	AnswerArea area;
	InfoArea iarea;
	public static void main(String[] args) {
		new LoadWin().setVisible(true);
	}
	public LoadWin () {
		super("口算练习系统");
		setSize(1040, 575);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		JFrame thiso = this;
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
				int res =JOptionPane.showConfirmDialog(thiso, "你确定要退出吗? 没有保存的练习和作答都会丢失", "关闭", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		jp = new JPanel(new BorderLayout());
		InitMenu();
		InitAnsArea();
		add(jp);
		WinStatus.setinit(this);
	}
	private void InitAnsArea() {
		janswering = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jp.add(marea, BorderLayout.NORTH);
		jp.add(janswering, BorderLayout.CENTER);
		janswering.setDividerLocation(710);
		janswering.setEnabled(false);
	//	BinaryOperationSet tp =new BinaryOperationSet(30, 100, BinaryOperationSet.MIX); 
		area = new AnswerArea(this);
		iarea = new InfoArea(this);
		janswering.setLeftComponent(area);
		janswering.setRightComponent(iarea);
	}
	private void InitMenu() {
		marea = new MenuBarArea(this);
	}
	
	public void ShowAboutWin() {
		new AboutWin(this).setVisible(true);;
	}
}
