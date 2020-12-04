package sys.net;

import java.io.IOException;

public class DownLoadData implements Runnable {
    RecNetData sign;

    public DownLoadData(RecNetData sign) {
        this.sign = sign;
    }
    @Override
    public void run() {
        String Dir = "DownLoad/.netdata";
        try {
            FtpConnect ftp = new FtpConnect();
            if(Thread.currentThread().isInterrupted()) return;
            ftp.getData();
        } catch (IOException e) {
            sign.Sign(Dir, 1);
            return;
        }
        sign.Sign(Dir, 0);

    }
}
