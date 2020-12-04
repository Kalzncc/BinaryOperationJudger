package sys.win;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import sys.set.Judgement;

public class MenuBarArea extends JMenuBar{
	LoadWin thisown;
	JMenuItem NewSet = new JMenuItem("�½���ϰ");
	JMenuItem OpenSet = new JMenuItem("����ϰ");
	JMenuItem SaveSet = new JMenuItem("������ϰ");
	JMenuItem SaveAns = new JMenuItem("�����");
	JMenuItem AddSet = new JMenuItem("׷��ϰ��");
	JMenuItem ImportAns = new JMenuItem("�����");
	JMenuItem TestStart = new JMenuItem("�ύ��");
	JMenuItem ImportTest = new JMenuItem("���������Ϣ���ļ�");
	JMenuItem LoginInfo = new JMenuItem("�鿴ͳ����Ϣ");
	JMenuItem HelpItem = new JMenuItem("����");
	JMenuItem CheBase = new JMenuItem("�鿴��ʽ��");
	JMenuItem GetFile = new JMenuItem("���ļ���������ϰ");
	JMenuItem CheLine = new JMenuItem("�鿴����ͳ����Ϣ");
	JMenuItem CheNetChart = new JMenuItem("�鿴��������¼");
	JMenuItem AddNetChart = new JMenuItem("�ϴ����μ�¼");
	JMenu FileMenu = new JMenu("�ļ�");
	JMenu TestMenu = new JMenu("����");
	JMenu NetMenu = new JMenu("ͳ��");
	JMenu SaMenu = new JMenu("����");
	JMenu HelpMenu = new JMenu("����"); 

	Judgement jd;
	public MenuBarArea (LoadWin own) {
		super();
		thisown = own;
		
		FileMenu.add(NewSet);
		FileMenu.add(OpenSet);
		FileMenu.add(SaveSet);
		FileMenu.add(AddSet);
		FileMenu.addSeparator();
		FileMenu.add(ImportAns);
		FileMenu.add(SaveAns);
		FileMenu.addSeparator();
		FileMenu.add(CheBase);
		FileMenu.add(GetFile);
		TestMenu.add(TestStart);
		TestMenu.add(ImportTest);
		NetMenu.add(LoginInfo);
		NetMenu.add(CheLine);
		SaMenu.add(CheNetChart);
		SaMenu.add(AddNetChart);
		HelpMenu.add(HelpItem);
		add(FileMenu);
		add(TestMenu);
		add(NetMenu);
		add(SaMenu);
		add(HelpMenu);
		SaveSet.setEnabled(false);
		SaveAns.setEnabled(false);
		ImportAns.setEnabled(false);
		TestStart.setEnabled(false);
		ImportTest.setEnabled(false);
		AddSet.setEnabled(false);
		NewSet.setEnabled(true);
		OpenSet.setEnabled(true);
		LoginInfo.setEnabled(false);
		AddNetChart.setEnabled(false);
		initAction();
		
	}
	private void initAction() {
		NewSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.NetSet(thisown);
			}
		});
		OpenSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.OpenSet(thisown);
			}
		});
		
		SaveSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.SaveSet(thisown);
			}
				
		});
		AddSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.AddSet(thisown);
			}
		});
		ImportAns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.ImportAns(thisown);
			}
		});
		
		SaveAns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.SaveAns(thisown);
			}
		});
		
		CheBase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.CheBase(thisown);
			}
		});
		
		GetFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.GetFile(thisown);
			}
		});
		
		TestStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.TestStart(thisown, jd);
			}
		});
		
		ImportTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.ImportTest(thisown, jd);
			}
		});
		LoginInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.CheChart(thisown, jd);
			}
		});
		CheLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.CheLineChart(thisown);
			}
		});
		CheNetChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.CheNetChart(thisown);
			}
		});
		AddNetChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.AddNetChart(thisown, jd);
			}
		});
		HelpItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralEevent.OpenHelpWin(thisown);
			}
		});
	}
}
