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
	JMenuItem NewSet = new JMenuItem("新建练习");
	JMenuItem OpenSet = new JMenuItem("打开练习");
	JMenuItem SaveSet = new JMenuItem("保存练习");
	JMenuItem SaveAns = new JMenuItem("保存答案");
	JMenuItem AddSet = new JMenuItem("追加习题");
	JMenuItem ImportAns = new JMenuItem("导入答案");
	JMenuItem TestStart = new JMenuItem("提交答案");
	JMenuItem ImportTest = new JMenuItem("输出评测信息到文件");
	JMenuItem LoginInfo = new JMenuItem("查看统计信息");
	JMenuItem HelpItem = new JMenuItem("关于");
	JMenuItem CheBase = new JMenuItem("查看算式基");
	JMenuItem GetFile = new JMenuItem("从文件中生成练习");
	JMenuItem CheLine = new JMenuItem("查看多天统计信息");
	JMenuItem CheNetChart = new JMenuItem("查看服务器记录");
	JMenuItem AddNetChart = new JMenuItem("上传本次记录");
	JMenu FileMenu = new JMenu("文件");
	JMenu TestMenu = new JMenu("批改");
	JMenu NetMenu = new JMenu("统计");
	JMenu SaMenu = new JMenu("网络");
	JMenu HelpMenu = new JMenu("帮助"); 

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
