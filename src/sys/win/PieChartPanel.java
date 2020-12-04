package sys.win;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import sys.set.BinaryOperation;
import sys.set.BinaryOperationSet;
import sys.set.Judgement;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PieChartPanel extends JPanel {
    private Judgement thisjd = null;
    private JFreeChart jchart = null;
    DefaultPieDataset data = null;
    public static void main(String[] args) {
        PieChartPanel pi = new PieChartPanel(null);
        JFrame jf = new JFrame("哈哈");
        jf.add(pi);
        jf.setSize(400, 400);
        jf.setVisible(true);
    }
    public PieChartPanel(Judgement jd) {
        super(new FlowLayout(FlowLayout.CENTER));
        thisjd = jd;
        data = getDataSet();
        jchart = ChartFactory.createPieChart(
                "题目统计信息",
                data,
                true,
                false,
                false
        );
        PiePlot pieplot = (PiePlot) jchart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
        NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
                "{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
        pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

        // 没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);// 设置不显示空值
        pieplot.setIgnoreZeroValues(true);// 设置不显示负值

        jchart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
        PiePlot piePlot = (PiePlot) jchart.getPlot();// 获取图表区域对象
        piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
        jchart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
        ChartPanel chartPanel = new ChartPanel(jchart, true);
        add(chartPanel);
    }
    private DefaultPieDataset getDataSet () {
        boolean[] vis = new boolean[thisjd.getSize()];
        for (boolean x : vis) x = false;
        ArrayList<Integer> WrongAns = thisjd.getWrongAns();
        for (Integer x : WrongAns) vis[x] = true;
        int rightadd = 0, rightsub = 0;
        int wrongadd = 0, wrongsub = 0;
        BinaryOperationSet set = thisjd.getSet();
        int cnt = 0;
        for (BinaryOperation x : set) {
            if (x.getop() =='+' && vis[cnt])
                wrongadd++;
            else if (x.getop() == '+' && !vis[cnt])
                rightadd++;
            else if (x.getop() == '-' && !vis[cnt])
                rightsub++;
            else wrongsub++;
            cnt++;
        }
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("正确加法", rightadd);
        dataset.setValue("错误加法", wrongadd);
        dataset.setValue("正确减法", rightsub);
        dataset.setValue("错误减法", wrongsub);
        return dataset;
    }
}
