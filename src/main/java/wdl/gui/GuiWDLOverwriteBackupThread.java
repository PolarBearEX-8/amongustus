package wdl.gui;

import wdl.WDL;
import wdl.WDLMessageTypes;
import wdl.WDLMessages;
import wdl.WorldBackup;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLOverwriteBackupThread extends Thread {
   private final DateFormat dateFormat;
   private final boolean zip;
   public final GuiWDLOverwriteChanges parentGui;

   @Override
   public void run() {

      try {
         String str = WDL.getWorldFolderName(WDL.worldName) + "_" + this.dateFormat.format(new Date()) + "_user" + (this.zip ? ".zip" : "");
         if (this.zip) {
            GuiWDLOverwriteChanges.setBackupData(this.parentGui, I18n.format("wdl.gui.overwriteChanges.backingUp.zip", new Object[]{str}));
         } else {
            GuiWDLOverwriteChanges.setBackupData(this.parentGui, I18n.format("wdl.gui.overwriteChanges.backingUp.folder", new Object[]{str}));
         }

         File worldDirectory = WDL.saveHandler.getWorldDirectory();
         File file = new File(worldDirectory.getParentFile(), str);
         if (file.exists()) {
            throw new IOException("Backup target (" + file + ") already exists!");
         }

         if (this.zip) {
            WorldBackup.zipDirectory(worldDirectory, file, this.parentGui);
         } else {
            WorldBackup.copyDirectory(worldDirectory, file, this.parentGui);
         }
      } catch (Exception e) {
         WDLMessages.chatMessageTranslated(WDLMessageTypes.ERROR, "wdl.messages.generalError.failedToSetUpEntityUI", new Object[0]);
      } finally {
         GuiWDLOverwriteChanges.setBackingUp(this.parentGui, false);
         WDL.overrideLastModifiedCheck = true;
         GuiWDLOverwriteChanges.getMinecraft(this.parentGui).displayGuiScreen((GuiScreen)null);
         WDL.startDownload();
      }
   }

   public GuiWDLOverwriteBackupThread(GuiWDLOverwriteChanges guiWDLOverwriteChanges, boolean flag) {
      this.parentGui = guiWDLOverwriteChanges;
      this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
      this.zip = flag;
   }

}
