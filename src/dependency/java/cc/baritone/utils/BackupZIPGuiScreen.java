package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class BackupZIPGuiScreen extends GuiScreen {
   public GuiScreen guiScreen2;
   public String labelText;
   public CompositeCustom compositeCustom;

   @Override
   public void updateState2() {
      this.compositeCustom = CompositeCustom.resolveCompositeCustom(Pokechu22WorldDownloaderManager.properties4.getProperty("Backup", "ZIP"));
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, this.width / 2 - 100, 32, this.getTextAsText()));
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
   }

   @Override
   public void enableRepeatEvents() {
      Pokechu22WorldDownloaderManager.properties4.setProperty("Backup", this.compositeCustom.name());
      Pokechu22WorldDownloaderManager.updateState2();
   }

   public BackupZIPGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
      this.labelText = StatCollector.getText("wdl.gui.backup.description1", new Object[0])
         + "\n\n"
         + StatCollector.getText("wdl.gui.backup.description2", new Object[0])
         + "\n\n"
         + StatCollector.getText("wdl.gui.backup.description3", new Object[0]);
   }

   private String getTextAsText() {
      return StatCollector.getText("wdl.gui.backup.backupMode", new Object[]{this.compositeCustom.getText()});
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            switch (BackupZIPGuiScreenCompositeCustomSwitchMap.countArray[this.compositeCustom.ordinal()]) {
               case 1:
                  this.compositeCustom = CompositeCustom.FOLDER;
                  break;
               case 2:
                  this.compositeCustom = CompositeCustom.ZIP;
                  break;
               case 3:
                  this.compositeCustom = CompositeCustom.NONE;
            }

            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getTextAsText();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      CouldnOpenHelper.applyIndex(23, 32, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.backup.title", new Object[0]), this.width / 2, 8, 16777215);
      super.applyIndex(count, count2, factor);
      CouldnOpenHelper.applyText(this.labelText, this.width - 50, 3 * this.height / 5, this.width, this.height, 48);
   }
}
