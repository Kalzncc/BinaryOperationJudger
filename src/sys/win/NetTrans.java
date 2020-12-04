package sys.win;

import sys.net.RecNetData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class NetTrans implements RecNetData {
    LoadWin thisown;
    JFrame jd = new JFrame();
    JButton jb = new JButton("取消");
    public NetTrans(LoadWin own) {
        thisown = own;
        initJD();
    }
    private void initJD() {
        jd = new JFrame("载入中");
        jd.setLocationRelativeTo(thisown);
        thisown.setEnabled(false);
        jd.setSize(300, 130);
        jd.setResizable(false);
        jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jd.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                Thread.currentThread().interrupt();
                thisown.setEnabled(true);
            }
            @Override
            public void windowClosed(WindowEvent e) {
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        JLabel jl = new JLabel("正在连接服务器");
        JPanel jp = new JPanel();
        jp.add(jl);
        jp.add(jb);
        jd.add(jp);
        jd.setVisible(true);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.currentThread().interrupt();
                jd.dispose();
                thisown.setEnabled(true);
            }
        });
    }

}
