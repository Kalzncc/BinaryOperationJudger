package sys.win;

import sys.set.AnsweringData;
import sys.set.BinaryOperationFiles;
import sys.set.BinaryOperationSet;
import sys.set.RecFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LineChartWin extends JFrame {
    LoadWin thisown;
    JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER));
    LineChartPanel lp = null;
    JTable jt;
    ArrayList<AnsweringData> dataset;
    public static void main(String[] args) {
        new LineChartWin(null).setVisible(true);
    }
    public LineChartWin(LoadWin own) {
        super("多天统计信息");
        thisown = own;
        initWin("DownLoad/.LineChartData");
    }
    public LineChartWin(LoadWin own, String Dir) {
        super("多天统计信息");
        thisown = own;
        initWin(Dir);
    }
    public void initWin(String Dir) {

        setSize(720, 700);
        setResizable(false);
        setLocationRelativeTo(thisown);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        try {
            RecFile tp = new RecFile(Dir, BinaryOperationFiles.READ);
            dataset = tp.read();
            tp.close();
        }
        catch (Exception e) {
            JOptionPane.showConfirmDialog(thisown, "无法读取信息", "错误", JOptionPane.CLOSED_OPTION);
        }
        lp = new LineChartPanel(dataset);
        jp.add(lp);
        Object[] name = new Object[] {"时间", "题目数量", "范围", "正确数量", "类型", "正确率"};
        Object[][] data = new Object[dataset.size()][6];
        for (int i = 0; i < dataset.size(); i++) {
            AnsweringData te = dataset.get(i);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String tt = df.format(te.date);
            data[dataset.size() - i - 1][0] = tt;
            data[dataset.size() - i - 1][1] = te.SetNum;
            data[dataset.size() - i - 1][2] = te.limit;
            data[dataset.size() - i - 1][3] = te.RightNum;
            if (te.type == BinaryOperationSet.MIX) data[dataset.size() - i - 1][4] = "加减法";
            if (te.type == BinaryOperationSet.ADD) data[dataset.size() - i - 1][4] = "加法";
            if (te.type == BinaryOperationSet.SUB) data[dataset.size() - i - 1][4] = "减法";
            data[dataset.size() - i - 1][5] = String.valueOf(1.0 * te.RightNum / te.SetNum);
        }
        jt = new JTable(data, name);
        jt.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        jt.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        jt.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        jt.getTableHeader().setReorderingAllowed(false);
        jt.setRowHeight(30);
        jt.setFont(new Font(Font.SERIF, Font.BOLD, 13));
        jt.setPreferredScrollableViewportSize(new Dimension(3000, 3000));
        jt.setEnabled(false);
        JScrollPane jts = new JScrollPane(jt);
        jts.setBorder(BorderFactory.createTitledBorder("记录信息"));
        jts.setPreferredSize(new Dimension(670, 230));
        jp.add(jts);
        add(jp);
    }
}
