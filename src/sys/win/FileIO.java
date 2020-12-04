package sys.win;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileIO {
	public static String ReadFile(String tip, String name) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(tip, name);
		jfc.setFileFilter(filter);
		int res = jfc.showDialog(new JLabel(), "Ñ¡Ôñ");
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			return file.getAbsolutePath();
		}
		return "";
	}
	public static String SaveFile(String tip, String name) {
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(tip, name);
		jfc.setFileFilter(filter);
		int res = jfc.showSaveDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			return file.getAbsolutePath();
		}
		return "";
	}
}
