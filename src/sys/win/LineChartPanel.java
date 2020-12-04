package sys.win;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import sys.set.AnsweringData;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.*;
import sys.set.BinaryOperationFiles;
import sys.set.RecFile;

public class LineChartPanel extends JPanel {
    ArrayList<AnsweringData> dataset;
    public static void main(String[] args ) throws FileNotFoundException {
        RecFile file = new RecFile("DownLoad/.LineChartData", BinaryOperationFiles.READ);
        ArrayList<AnsweringData> data = file.read();
        LineChartPanel chart = new LineChartPanel(data);
        JFrame jf = new JFrame("����ͼ");
        jf.setSize(720, 700);
        jf.add(chart);
        jf.setVisible(true);
    }
    public LineChartPanel(ArrayList<AnsweringData> dataset) {
        this.dataset = dataset;
        XYDataset xydataset = createDataset();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("��ȷ������ͼ", "����", "��ȷ��",xydataset, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();

        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        dateaxis.setAutoTickUnitSelection(false);
        DateTickUnit tickUnit = new DateTickUnit(DateTickUnitType.DAY, 3, new SimpleDateFormat("yyyy-mm-dd hh")); // �ڶ���������ʱ�������
        dateaxis.setTickUnit(tickUnit);
        dateaxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
        dateaxis.setTickLabelsVisible(true);
        dateaxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
        ValueAxis rangeAxis=xyplot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������

        ChartPanel chartPanel = new ChartPanel(jfreechart,true);
        chartPanel.setPreferredSize(new Dimension(670, 350));
        add(chartPanel);

    }

    private XYDataset createDataset() {
        TimeSeries timeseries = new TimeSeries("�û���ȷ��");
        for (AnsweringData x : dataset) {
            timeseries.add(new Second(x.date), 1.0 *x.RightNum / x.SetNum);
        }
        TimeSeriesCollection time = new TimeSeriesCollection();
        time.addSeries(timeseries);
        return time;
    }
}
