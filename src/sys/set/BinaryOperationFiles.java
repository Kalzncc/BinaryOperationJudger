package sys.set;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public abstract class BinaryOperationFiles {
	public final static String READ = "r", WRITE = "w", APPEND = "a";
	protected File fp;
	protected String type;
	protected DataOutputStream fout = null;
	protected DataInputStream fin = null;
	public BinaryOperationFiles(String Dir, String type) throws RuntimeException, FileNotFoundException{
		if (type != READ && type != WRITE && type != APPEND) throw new RuntimeException("Type exception");
		this.type = type;
		fp = new File(Dir);
		if (type == READ) {
			fin = new DataInputStream(new BufferedInputStream(new FileInputStream(fp)));
		}
		else if (type == WRITE){
			fout = new DataOutputStream(new BufferedOutputStream( new FileOutputStream(fp)));
		}
		else {
			fout = new DataOutputStream(new BufferedOutputStream( new FileOutputStream(fp, true)));
		}
	}
	public void close () {
		try {
			if (type == WRITE || type == APPEND) fout.close();
			else fin.close();
		} 
		catch (IOException e) {
			System.out.println("Fail to close file");
		}
	}
	public String getFilename() {
		return fp.getName();
	}
	
	
}
