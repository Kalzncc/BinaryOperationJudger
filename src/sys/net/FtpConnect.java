package sys.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpConnect {
    static final String name = "ftpuser";
    static final String passwd = "18\u0011\u000E\u0006\u001502";
    static final String host = "154.8.152.140";
    static final String dataFile = "ConWorkSpace/data/.data";
    static final String dataDir = "ConWorkSpace/data";
    static final int QueueMaxNum = 0;
    FTPClient ftp = new FTPClient();
    public static void main(String[] args) {

    }
    public void getData () throws IOException {
        DownLoadFile(dataFile, ".netdata");
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) throw new IOException();
    }
    public void putData() throws  IOException {
        UpLoadFile(dataDir, ".data", new File("DownLoad/.netdata"));
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) throw new IOException();
    }
    public String getPasswd() {
        char[] tt = passwd.toCharArray();
        for (int i = 2; i < tt.length - 2; i++) {
            tt[i] = (char) (((int) tt[i] - 100 - i + 200) % 200);
        }
        String p = String.valueOf(tt);
        return p;
    }
    public FtpConnect() throws SocketException, IOException {
        //ftp.setConnectTimeout(10 * 1000);
        ftp.connect(host);
        ftp.login(name, getPasswd());
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
            throw new SocketException("无法连接到服务器");
        }
    }
    private void DownLoadFile(String Dir, String FileName) throws IOException {
        FileOutputStream fp = new FileOutputStream("DownLoad/" + FileName);
        ftp.changeWorkingDirectory("/home/ftpuser");
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftp.retrieveFile(Dir, fp);
        System.out.println(ftp.getReplyCode());
        fp.close();
    }
    private void UpLoadFile(String Dir, String FileName, File fp) throws IOException {
        FileInputStream fpt = new FileInputStream(fp);
        ftp.changeWorkingDirectory("/home/ftpuser");
        ftp.changeWorkingDirectory(Dir);
        ftp.setBufferSize(1024);
        ftp.setControlEncoding("Utf-8");
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftp.storeFile(FileName, fpt);
        fpt.close();
    }
}
