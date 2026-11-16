package cc.baritone.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class UserZipThread extends Thread {
   public final DateFormat dateFormat;
   public final boolean enabled;
   public final DiagonalWatcher diagonalWatcher;

   @Override
   public void run() {
      try {
         String text = Pokechu22WorldDownloaderManager.resolveText(Pokechu22WorldDownloaderManager.labelText4)
            + "_"
            + this.dateFormat.format(new Date())
            + "_user"
            + (this.enabled ? ".zip" : "");
         if (this.enabled) {
            DiagonalWatcher.resolveText(this.diagonalWatcher, StatCollector.getText("wdl.gui.overwriteChanges.backingUp.zip", new Object[]{text}));
         } else {
            DiagonalWatcher.resolveText(this.diagonalWatcher, StatCollector.getText("wdl.gui.overwriteChanges.backingUp.folder", new Object[]{text}));
         }

         File file = Pokechu22WorldDownloaderManager.listenerAdapter17.getFile();
         File file2 = new File(file.getParentFile(), text);
         if (file2.exists()) {
            throw new IOException("Backup target (" + file2 + ") already exists!");
         }

         if (this.enabled) {
            DepthFirstStrategy.applyFileByFile(file, file2, this.diagonalWatcher);
         } else {
            DepthFirstStrategy.applyFile(file, file2, this.diagonalWatcher);
         }
      } catch (Exception exception) {
         LoggerEntry.applyAltListener(OptionType.ERROR, "wdl.messages.generalError.failedToSetUpEntityUI", new Object[0]);
      } finally {
         DiagonalWatcher.matchesDiagonalWatcher(this.diagonalWatcher, false);
         Pokechu22WorldDownloaderManager.enabled3 = true;
         DiagonalWatcher.resolveMinecraft(this.diagonalWatcher).displayGuiScreen((GuiScreen)null);
         Pokechu22WorldDownloaderManager.updateState3();
      }
   }

   public UserZipThread(DiagonalWatcher diagonalWatcher2, boolean flag) {
      this.diagonalWatcher = diagonalWatcher2;
      this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
      this.enabled = flag;
   }
}
