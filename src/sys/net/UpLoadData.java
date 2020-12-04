package sys.net;

import java.io.IOException;

public class UpLoadData implements Runnable{
    RecNetData sign;
    public UpLoadData() {
        sign = (x, y) -> {
            return 0;
        };
    }
    public UpLoadData(RecNetData sign) {
        this.sign = sign;
    }
    @Override
    public void run() {
        String Dir = "DownLoad/.netdata";
        try {
            FtpConnect ftp = new FtpConnect();
            if(Thread.currentThread().isInterrupted()) return;
            ftp.putData();
        } catch (IOException e) {
            sign.Sign(Dir, 1);
            return;
        }
        sign.Sign(Dir, -1);

    }
}
