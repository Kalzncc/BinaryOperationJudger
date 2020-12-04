package sys.win;

import sys.net.DownLoadData;
import sys.net.RecNetData;
import sys.net.UpLoadData;
import sys.set.*;

import javax.swing.*;

public class NetUpload extends NetTrans {
    AnsweringData data;
    public NetUpload(LoadWin own, AnsweringData data) {
        super(own);
        this.data = data;
    }
    public void StartUpload() {
        DownLoadData data = new DownLoadData(this);
        Thread th = new Thread(data);
        th.start();
    }
    @Override
    public int Sign(String Dir, int status) {
        if (status == 1) {
            JOptionPane.showConfirmDialog(thisown, "���ӷ�����ʧ��", "����", JOptionPane.CLOSED_OPTION);
            return 1;
        }
        else if (status == 0) {
            try {
                RecFile rec = new RecFile(Dir, BinaryOperationFiles.APPEND);
                rec.AddData(data);
                rec.close();
            }
            catch (Exception e) {
                jd.dispose();
                thisown.setEnabled(true);
                JOptionPane.showConfirmDialog(thisown, "�ļ�д��ʧ��", "����", JOptionPane.CLOSED_OPTION);
                return 1;
            }
            UpLoadData data = new UpLoadData(this);
            Thread th = new Thread(data);
            th.start();
        }
        else {
            jd.dispose();
            thisown.setEnabled(true);
            JOptionPane.showConfirmDialog(thisown, "�ϴ��ɹ�", "��ʾ", JOptionPane.CLOSED_OPTION);
            thisown.marea.AddNetChart.setEnabled(false);
            return 0;
        }
        return 0;
    }
}
