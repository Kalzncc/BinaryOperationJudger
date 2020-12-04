package sys.win;

public class WinStatus {
    public static void setinit(LoadWin thisown) {
        thisown.marea.NewSet.setEnabled(true);
        thisown.marea.OpenSet.setEnabled(true);
        thisown.marea.SaveSet.setEnabled(false);
        thisown.marea.AddSet.setEnabled(false);
        thisown.marea.ImportAns.setEnabled(false);
        thisown.marea.SaveAns.setEnabled(false);
        thisown.marea.CheBase.setEnabled(true);
        thisown.marea.GetFile.setEnabled(true);
        thisown.marea.TestStart.setEnabled(false);
        thisown.marea.ImportTest.setEnabled(false);
        thisown.marea.LoginInfo.setEnabled(false);
        thisown.marea.CheLine.setEnabled(true);
        thisown.marea.CheNetChart.setEnabled(true);
        thisown.marea.AddNetChart.setEnabled(false);
        thisown.marea.HelpItem.setEnabled(true);
        thisown.area.jb.setEnabled(false);
        thisown.area.jjump.setEnabled(false);
        thisown.area.jpre.setEnabled(false);
        thisown.area.jnxt.setEnabled(false);
        thisown.area.jf.setEnabled(false);
        thisown.iarea.js.setEnabled(false);
    }
    public static void setAnsering (LoadWin thisown) {
        thisown.marea.NewSet.setEnabled(true);
        thisown.marea.OpenSet.setEnabled(true);
        thisown.marea.SaveSet.setEnabled(true);
        thisown.marea.AddSet.setEnabled(true);
        thisown.marea.ImportAns.setEnabled(true);
        thisown.marea.SaveAns.setEnabled(true);
        thisown.marea.CheBase.setEnabled(true);
        thisown.marea.GetFile.setEnabled(true);
        thisown.marea.TestStart.setEnabled(true);
        thisown.marea.ImportTest.setEnabled(false);
        thisown.marea.LoginInfo.setEnabled(false);
        thisown.marea.CheLine.setEnabled(true);
        thisown.marea.CheNetChart.setEnabled(true);
        thisown.marea.AddNetChart.setEnabled(false);
        thisown.marea.HelpItem.setEnabled(true);
        thisown.area.jb.setEnabled(true);
        thisown.area.jjump.setEnabled(true);
        thisown.area.jpre.setEnabled(false);
        thisown.area.jnxt.setEnabled(false);
        thisown.area.jf.setEnabled(true);
        thisown.iarea.js.setEnabled(false);
    }
    public static void setTested (LoadWin thisown) {
        thisown.marea.NewSet.setEnabled(true);
        thisown.marea.OpenSet.setEnabled(true);
        thisown.marea.SaveSet.setEnabled(true);
        thisown.marea.AddSet.setEnabled(false);
        thisown.marea.ImportAns.setEnabled(false);
        thisown.marea.SaveAns.setEnabled(true);
        thisown.marea.CheBase.setEnabled(true);
        thisown.marea.GetFile.setEnabled(true);
        thisown.marea.TestStart.setEnabled(false);
        thisown.marea.ImportTest.setEnabled(true);
        thisown.marea.LoginInfo.setEnabled(true);
        thisown.marea.CheLine.setEnabled(true);
        thisown.marea.CheNetChart.setEnabled(true);
        thisown.marea.AddNetChart.setEnabled(true);
        thisown.marea.HelpItem.setEnabled(true);
        thisown.area.jb.setEnabled(false);
        thisown.area.jjump.setEnabled(true);
        thisown.area.jpre.setEnabled(false);
        thisown.area.jnxt.setEnabled(false);
        thisown.area.jf.setEnabled(true);
        thisown.iarea.js.setEnabled(true);
    }
    public static void setLoading (LoadWin thisown) {
        thisown.marea.FileMenu.setEnabled(false);
        thisown.marea.TestMenu.setEnabled(false);
    }
    public static void setunLoading (LoadWin thisown) {
        thisown.marea.FileMenu.setEnabled(true);
        thisown.marea.TestMenu.setEnabled(true);
    }
}
