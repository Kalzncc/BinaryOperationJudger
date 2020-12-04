package sys.win;

import java.awt.Color;

import sys.set.BinaryOperationSet;

public class LoadLargeFile implements Runnable{
	FileLoadWin thisown;
	LoadWin lthisown;
	BinaryOperationSet thisSet;
	ProblemCom[] setCom;
	public LoadLargeFile (LoadWin lthisown, FileLoadWin thisown, BinaryOperationSet set) {
		this.thisown = thisown;
		thisSet = set;
		this.lthisown = lthisown;
	}

	@Override
	public void run() {
		setCom = new ProblemCom[thisSet.getSize()];
		for (int i = 0; i < thisSet.getSize(); i++) {
			setCom[i] = new ProblemCom(i+1, thisSet.getOperation(i), lthisown.area.jpsl, lthisown.area.jps, lthisown);
			if (Thread.currentThread().isInterrupted()) {
				WinStatus.setunLoading(lthisown);

				if (lthisown.area.curpag != 0) {
					lthisown.area.openPag(lthisown.area.curpag);
				}
				return;
			}
			thisown.jl.setText("¶ÁÈëÖÐ: " + i + "/" + thisSet.getSize());
			thisown.bar.setValue(i);
		}
		lthisown.area.allpag = thisSet.getSize() / 27;
		if (thisSet.getSize() % 27 != 0) lthisown.area.allpag++;
		WinStatus.setunLoading(lthisown);
		WinStatus.setAnsering(lthisown);
		lthisown.iarea.bar.setIndeterminate(false);
		lthisown.iarea.tArea.setText("");
		lthisown.iarea.bar.setValue(0);
		lthisown.iarea.bar.setForeground(Color.cyan);
		lthisown.iarea.bar.setBackground(Color.white);
		lthisown.area.thisSet = thisSet;
		lthisown.area.setCom = setCom;
		thisown.dispose();
		lthisown.area.curpag = 1;
		lthisown.area.openPag(1);
		
	}
}
