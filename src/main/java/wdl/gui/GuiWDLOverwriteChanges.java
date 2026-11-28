package wdl.gui;

import wdl.IBackupProgressMonitor;
import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLOverwriteChanges extends GuiTurningCameraBase implements IBackupProgressMonitor {
   private volatile boolean backingUp = false;
   private volatile String backupData = "";
   private volatile int backupCount;
   private volatile int backupCurrent;
   private volatile String backupFile = "";
   private int infoBoxX;
   private int infoBoxY;
   private int infoBoxWidth;
   private int infoBoxHeight;
   private GuiButton backupAsZipButton;
   private GuiButton backupAsFolderButton;
   private GuiButton downloadNowButton;
   private GuiButton cancelButton;
   private final long lastSaved;
   private final long lastPlayed;
   private String title;
   private String footer;
   private String captionTitle;
   private String captionSubtitle;
   private String overwriteWarning1;
   private String overwriteWarning2;
   private String backingUpTitle;

   @Override
   public void initGui() {
      this.backingUp = false;
      this.title = I18n.format("wdl.gui.overwriteChanges.title", new Object[0]);
      if (this.lastSaved != -1L) {
         this.footer = I18n.format("wdl.gui.overwriteChanges.footer", new Object[]{this.lastSaved, this.lastPlayed});
      } else {
         this.footer = I18n.format("wdl.gui.overwriteChanges.footerNeverSaved", new Object[]{this.lastPlayed});
      }

      this.captionTitle = I18n.format("wdl.gui.overwriteChanges.captionTitle", new Object[0]);
      this.captionSubtitle = I18n.format("wdl.gui.overwriteChanges.captionSubtitle", new Object[0]);
      this.overwriteWarning1 = I18n.format("wdl.gui.overwriteChanges.overwriteWarning1", new Object[0]);
      this.overwriteWarning2 = I18n.format("wdl.gui.overwriteChanges.overwriteWarning2", new Object[0]);
      this.backingUpTitle = I18n.format("wdl.gui.overwriteChanges.backingUp.title", new Object[0]);
      this.infoBoxWidth = this.fontRendererObj.getStringWidth(this.overwriteWarning1);
      this.infoBoxHeight = 132;
      if (this.infoBoxWidth < 200) {
         this.infoBoxWidth = 200;
      }

      this.infoBoxY = 48;
      this.infoBoxX = this.width / 2 - this.infoBoxWidth / 2;
      int n = this.width / 2 - 100;
      int n2 = this.infoBoxY + 22;
      this.backupAsZipButton = new GuiButton(0, n, n2, I18n.format("wdl.gui.overwriteChanges.asZip.name", new Object[0]));
      this.buttonList.add(this.backupAsZipButton);
      n2 += 22;
      this.backupAsFolderButton = new GuiButton(1, n, n2, I18n.format("wdl.gui.overwriteChanges.asFolder.name", new Object[0]));
      this.buttonList.add(this.backupAsFolderButton);
      n2 += 22;
      this.downloadNowButton = new GuiButton(2, n, n2, I18n.format("wdl.gui.overwriteChanges.startNow.name", new Object[0]));
      this.buttonList.add(this.downloadNowButton);
      n2 += 22;
      this.cancelButton = new GuiButton(3, n, n2, I18n.format("wdl.gui.overwriteChanges.cancel.name", new Object[0]));
      this.buttonList.add(this.cancelButton);
      super.initGui();
   }

   public void setNumberOfFiles(int n) {
      this.backupCount = n;
      this.backupCurrent = 0;
   }

   public static boolean setBackingUp(GuiWDLOverwriteChanges guiWDLOverwriteChanges, boolean flag) {
      return guiWDLOverwriteChanges.backingUp = flag;
   }

   public static Minecraft getMinecraft(GuiWDLOverwriteChanges guiWDLOverwriteChanges) {
      return guiWDLOverwriteChanges.mc;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (!this.backingUp) {
         if (button.id == 0) {
            this.backingUp = true;
            new GuiWDLOverwriteBackupThread(this, true).start();
         }

         if (button.id == 1) {
            this.backingUp = true;
            new GuiWDLOverwriteBackupThread(this, false).start();
         }

         if (button.id == 2) {
            WDL.overrideLastModifiedCheck = true;
            this.mc.displayGuiScreen((GuiScreen)null);
            WDL.startDownload();
         }

         if (button.id == 3) {
            this.mc.displayGuiScreen((GuiScreen)null);
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.backingUp) {
         this.drawBackground(0);
         drawCenteredString(this.fontRendererObj, this.backingUpTitle, this.width / 2, this.height / 4 - 40, 16777215);
         drawCenteredString(this.fontRendererObj, this.backupData, this.width / 2, this.height / 4 - 10, 16777215);
         if (this.backupFile != null) {
            String str = I18n.format("wdl.gui.overwriteChanges.backingUp.progress", new Object[]{this.backupCurrent, this.backupCount, this.backupFile});
            drawCenteredString(this.fontRendererObj, str, this.width / 2, this.height / 4 + 10, 16777215);
         }
      } else {
         this.drawDefaultBackground();
         Utils.drawBorder(32, 22, 0, 0, this.height, this.width);
         drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
         drawCenteredString(this.fontRendererObj, this.footer, this.width / 2, this.height - 8 - this.fontRendererObj.FONT_HEIGHT, 16777215);
         drawRect(this.infoBoxX - 5, this.infoBoxY - 5, this.infoBoxX + this.infoBoxWidth + 5, this.infoBoxY + this.infoBoxHeight + 5, -1342177280);
         drawCenteredString(this.fontRendererObj, this.captionTitle, this.width / 2, this.infoBoxY, 16777215);
         drawCenteredString(this.fontRendererObj, this.captionSubtitle, this.width / 2, this.infoBoxY + this.fontRendererObj.FONT_HEIGHT, 16777215);
         drawCenteredString(this.fontRendererObj, this.overwriteWarning1, this.width / 2, this.infoBoxY + 115, 16777215);
         drawCenteredString(this.fontRendererObj, this.overwriteWarning2, this.width / 2, this.infoBoxY + 115 + this.fontRendererObj.FONT_HEIGHT, 16777215);
         super.drawScreen(mouseX, mouseY, partialTicks);
         String str2 = null;
         if (this.backupAsZipButton.isMouseOver()) {
            str2 = I18n.format("wdl.gui.overwriteChanges.asZip.description", new Object[0]);
         } else if (this.backupAsFolderButton.isMouseOver()) {
            str2 = I18n.format("wdl.gui.overwriteChanges.asFolder.description", new Object[0]);
         } else if (this.downloadNowButton.isMouseOver()) {
            str2 = I18n.format("wdl.gui.overwriteChanges.startNow.description", new Object[0]);
         } else if (this.cancelButton.isMouseOver()) {
            str2 = I18n.format("wdl.gui.overwriteChanges.cancel.description", new Object[0]);
         }

         Utils.drawGuiInfoBox(str2, this.width, this.height, 48);
      }
   }

   public static String setBackupData(GuiWDLOverwriteChanges guiWDLOverwriteChanges, String str) {
      return guiWDLOverwriteChanges.backupData = str;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode != 1) {
         super.keyTyped(typedChar, keyCode);
      }
   }

   public GuiWDLOverwriteChanges(long l, long l2) {
      this.lastSaved = l;
      this.lastPlayed = l2;
   }

   public void onNextFile(String str) {
      this.backupCurrent++;
      this.backupFile = str;
   }

}
