package wdl.gui;

import wdl.IBackupProgressMonitor;
import java.io.IOException;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLSaveProgress extends GuiTurningCameraBase implements IBackupProgressMonitor {
   private final String title;
   private volatile String majorTaskMessage = "";
   private volatile String minorTaskMessage = "";
   private volatile int majorTaskNumber;
   private final int majorTaskCount;
   private volatile int minorTaskProgress;
   private volatile int minorTaskMaximum;
   private volatile boolean doneWorking = false;

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.doneWorking) {
         GuiScreen screen = (GuiScreen)null;
         this.mc.displayGuiScreen(screen);
      } else {
         Utils.drawBorder(32, 32, 0, 0, this.height, this.width);
         String majorTaskMessage2 = this.majorTaskMessage;
         if (this.majorTaskCount > 1) {
            majorTaskMessage2 = I18n.format("wdl.gui.saveProgress.progressInfo", new Object[]{this.majorTaskMessage, this.majorTaskNumber, this.majorTaskCount});
         }

         String minorTaskMessage2 = this.minorTaskMessage;
         if (this.minorTaskMaximum > 1) {
            majorTaskMessage2 = I18n.format("wdl.gui.saveProgress.progressInfo", new Object[]{this.minorTaskMessage, this.minorTaskProgress, this.minorTaskMaximum});
         }

         drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
         drawCenteredString(this.fontRendererObj, majorTaskMessage2, this.width / 2, 100, 16777215);
         if (this.minorTaskMaximum > 0) {
            this.drawProgressBar(110, 84, 89, this.majorTaskNumber * this.minorTaskMaximum + this.minorTaskProgress, (this.majorTaskCount + 1) * this.minorTaskMaximum);
         } else {
            this.drawProgressBar(110, 84, 89, this.majorTaskNumber, this.majorTaskCount);
         }

         drawCenteredString(this.fontRendererObj, minorTaskMessage2, this.width / 2, 130, 16777215);
         this.drawProgressBar(140, 64, 69, this.minorTaskProgress, this.minorTaskMaximum);
         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   public void onNextFile(String str) {
      this.minorTaskProgress++;
      this.minorTaskMessage = I18n.format("wdl.saveProgress.backingUp.file", new Object[]{str});
   }

   private void drawProgressBar(int n, int n2, int n3, int n4, int n5) {
      if (n5 != 0) {
         this.mc.getTextureManager().bindTexture(Gui.icons);
         int n6 = n4 * 182 / n5;
         int n7 = this.width / 2 - 91;
         this.drawTexturedModalRect(n7, n, 0, n2, 182, 5);
         this.drawTexturedModalRect(n7, n, 0, n3, n6, 5);
      }
   }

   public void startMajorTask(String str, int n) {
      this.majorTaskMessage = str;
      this.majorTaskNumber++;
      this.minorTaskMessage = "";
      this.minorTaskProgress = 0;
      this.minorTaskMaximum = n;
   }

   public void setDoneWorking() {
      this.doneWorking = true;
   }

   public void setNumberOfFiles(int n) {
      this.minorTaskMaximum = n;
   }

   public void setMinorTaskProgress(String str, int n) {
      this.minorTaskMessage = str;
      this.minorTaskProgress = n;
   }

   public void setMinorTaskProgress(int n) {
      this.minorTaskProgress = n;
   }

   public GuiWDLSaveProgress(String str, int n) {
      this.title = str;
      this.majorTaskCount = n;
      this.majorTaskNumber = 0;
   }

}
