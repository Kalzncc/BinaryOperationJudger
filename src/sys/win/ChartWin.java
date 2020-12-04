package sys.win;

import sys.set.Judgement;

import javax.swing.*;

public class ChartWin  extends JFrame {
    LoadWin thisown;
    Judgement thisjd;
    PieChartPanel jp;
    public ChartWin(LoadWin own, Judgement jd) {
        super("统计信息");
        setSize(700, 470);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(thisown);
        thisjd = jd;
        thisown = own;
        jp = new PieChartPanel(jd);
        add(jp);
    }
}
