package test;

import java.awt.Font;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("ˮ������", data, true,
                false, false);
        // ���ðٷֱ�
        PiePlot pieplot = (PiePlot) chart.getPlot();
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

        chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
        PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
        piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));

        ChartPanel chartPanel = new ChartPanel(chart, true);
        JFrame frame = new JFrame("Java����ͳ��ͼ");
        frame.add(chartPanel); // �������ͼ
        frame.setBounds(0, 0, 900, 600);
        frame.setVisible(true);
    }

    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("ƻ��", 100);
        dataset.setValue("����", 200);
        dataset.setValue("����", 300);
        dataset.setValue("�㽶", 400);
        dataset.setValue("��֦", 500);
        return dataset;
    }
}