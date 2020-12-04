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
        JFrame jf = new JFrame("����");
        jf.add(pi);
        jf.setSize(400, 400);
        jf.setVisible(true);
    }
    public PieChartPanel(Judgement jd) {
        super(new FlowLayout(FlowLayout.CENTER));
        thisjd = jd;
        data = getDataSet();
        jchart = ChartFactory.createPieChart(
                "��Ŀͳ����Ϣ",
                data,
                true,
                false,
                false
        );
        PiePlot pieplot = (PiePlot) jchart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
        NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
                "{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
        pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�

        // û�����ݵ�ʱ����ʾ������
        pieplot.setNoDataMessage("��������ʾ");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
        pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ

        jchart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
        PiePlot piePlot = (PiePlot) jchart.getPlot();// ��ȡͼ���������
        piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
        jchart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
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
        dataset.setValue("��ȷ�ӷ�", rightadd);
        dataset.setValue("����ӷ�", wrongadd);
        dataset.setValue("��ȷ����", rightsub);
        dataset.setValue("�������", wrongsub);
        return dataset;
    }
}
