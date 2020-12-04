package sys.win;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import sys.set.BinaryOperationSet;

public class FileLoadWin extends JFrame{
	JProgressBar bar = new JProgressBar();
	JButton jcon = new JButton("取消");
	JLabel jl = new JLabel();
	JPanel jp = new JPanel();
	Thread th;
	public static void main(String[] args) {
	//	new FileLoadWin(null, new BinaryOperationSet(100, 100, 1)).setVisible(true);
	}
	public FileLoadWin (LoadWin thisown, BinaryOperationSet set) {
		super("载入中");
		WinStatus.setLoading(thisown);
		setSize(400, 170);
		setResizable(false);
		setLocationRelativeTo(thisown);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		bar.setPreferredSize(new Dimension(330, 20));
		bar.setMaximum(set.getSize() - 1);
		bar.setMinimum(0);
		jl.setText("读入中: 0/" + set.getSize());
		jp.add(jl);
		jp.add(bar);
		jp.add(jcon);
		th = new Thread(new LoadLargeFile(thisown, this, set));
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
				th.interrupt();
			}	
			@Override
			public void windowClosed(WindowEvent e) {
			}	
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		jcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				th.interrupt();
				dispose();
			}
		});
		
		
		th.start();
		
		
		
		
	}
}
