package wdl;

import wdl.gui.GuiWDLSaveProgress;
import net.augustus.utils.interfaces.MC;

public final class DisplaySaveProgressTask implements Runnable {
   public final GuiWDLSaveProgress progressScreen;

   public DisplaySaveProgressTask(GuiWDLSaveProgress guiWDLSaveProgress) {
      this.progressScreen = guiWDLSaveProgress;
   }

   @Override
   public void run() {
      GuiWDLSaveProgress progressScreen2 = this.progressScreen;
      MC.mc.displayGuiScreen(progressScreen2);
   }
}
