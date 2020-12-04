package sys.win;

import sys.net.RecNetData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NetDownload extends NetTrans {

    public NetDownload(LoadWin own) {
        super(own);
    }
    @Override
    public int Sign(String Dir, int status) {
        jd.dispose();
        thisown.setEnabled(true);
        if (status == 1) {
            JOptionPane.showConfirmDialog(thisown, "连接服务器失败", "错误", JOptionPane.CLOSED_OPTION);
            return 0;
        }
        new LineChartWin(thisown, Dir).setVisible(true);
        return 0;
    }
}
