package sys.win;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sys.set.BinaryOperation;

public class ProblemCom extends JPanel{
	JTextField ProblemF = new JTextField();
	JTextField AnswerF = new JTextField();
	JLabel Eq = new JLabel("=");
	JPanel ProblemJ = new JPanel();
	JLabel no;
	LoadWin jown;
	int num;
	SpringLayout jps;
	JPanel jp;
	BinaryOperation thisown;
	public static void main(String[] args) {
		
	}
	public ProblemCom (int num, BinaryOperation tp, SpringLayout own, JPanel ownJ, LoadWin JJ) {
		super(new FlowLayout(FlowLayout.LEFT));
		thisown = tp;
		jown = JJ;
		jps = own;
		jp = ownJ;
		this.num = num;
		ProblemF.setPreferredSize(new Dimension(70, 30));
		AnswerF.setPreferredSize(new Dimension(40, 30));
		no = new JLabel("No." + String.valueOf(num));
		no.setPreferredSize(new Dimension(40, 30));
		no.setFont(new Font(Font.SERIF, Font.BOLD, 13));
		add(no);
		add(ProblemF);
		add(Eq);
		add(AnswerF);
		ProblemF.setText(tp.toString(false));
		ProblemF.setEditable(false);
		AnswerF.setBackground(Color.white);
		initAction();
	}
	public void setOpen () {
		SpringLayout.Constraints thisCon = jps.getConstraints(this);
		thisCon.setWidth(Spring.constant(225));
		thisCon.setHeight(Spring.constant(40));
		int w = (num - 1) % 3;
		int h = (num - 1) / 3;
		this.setBorder(BorderFactory.createTitledBorder(""));
		thisCon.setX(Spring.constant(w * 180 + (w * 50) ) );
		thisCon.setY(Spring.constant((h % 9) * 45 + 5));
		SpringLayout.Constraints ownJc = jps.getConstraints(jp);
		ownJc.setHeight(Spring.sum( thisCon.getConstraint(  SpringLayout.SOUTH), Spring.constant(5) ) );
		jp.add(this);
	}
	public void setStatus (boolean flag) {
		if (flag) {
			AnswerF.setBackground(Color.red);
			JLabel jans = new JLabel(String.valueOf(thisown.getAnswer()));
			jans.setForeground(Color.green);
			add(jans);
		}
		else AnswerF.setBackground(Color.green);
		revalidate();
		repaint();
	}
	public void initAction() {
		AnswerF.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				jown.iarea.changeBar();
				if (AnswerF.getText().equals(""))
					AnswerF.setBackground(Color.white);
				else AnswerF.setBackground(Color.cyan);
			}
			@Override
			public void insertUpdate(DocumentEvent e) {	
				jown.iarea.changeBar();
				if (AnswerF.getText().equals(""))
					AnswerF.setBackground(Color.white);
				else AnswerF.setBackground(Color.cyan);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				jown.iarea.changeBar();
				if (AnswerF.getText().equals(""))
					AnswerF.setBackground(Color.white);
				else AnswerF.setBackground(Color.cyan);
			}
		});
	}
}