package sys.win;

import java.util.*;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class Timer extends JLabel implements Runnable{
	Date date;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	public Timer() {
		date = new Date();
	}
	
	@Override
	public void run() {
		while(true) {
			date = new Date();
			setText("��ǰʱ��Ϊ:" + df.format(date));
			revalidate();
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
	public static String getTime(Date currentTime,Date firstTime){
		long diff = currentTime.getTime() - firstTime.getTime();//�����õ��Ĳ�ֵ��΢�뼶��
		Calendar currentTimes =dataToCalendar(currentTime);//��ǰϵͳʱ��תCalendar����
		Calendar firstTimes =dataToCalendar(firstTime);//��ѯ������ʱ��תCalendar����
		int year = currentTimes.get(Calendar.YEAR) - firstTimes.get(Calendar.YEAR);//��ȡ��
		int month = currentTimes.get(Calendar.MONTH) - firstTimes.get(Calendar.MONTH);
		int day = currentTimes.get(Calendar.DAY_OF_MONTH) - firstTimes.get(Calendar.DAY_OF_MONTH);
		if (day < 0){
			month-=1;
			currentTimes.add(Calendar.MONTH, -1);
			day = day + currentTimes.getActualMaximum(Calendar.DAY_OF_MONTH);//��ȡ��
		}
		if (month<0){
			month = (month + 12) % 12;//��ȡ��
			year--;
		}
		long days = diff / (1000 * 60 * 60 * 24);
		long hours=(diff-days*(1000*60*60*24))/(1000* 60*60);//��ȡʱ?
		long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);//��ȡ����
		long s=(diff/1000-days*24*60*60-hours*60*60-minutes*60);//��ȡ��
		String CountTime= hours+"Сʱ"+minutes+"��"+s+"��";
		return CountTime;
	}

// Date����תCalendar����
	public static Calendar dataToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
