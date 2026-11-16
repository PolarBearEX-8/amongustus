package cc.baritone.utils;

import client.event.PreMotionObserver;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.DefaultGuiScreenAdapter;
import java.io.IOException;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class WdlGuiGuiScreen extends GuiScreen implements PreMotionObserver {
   public final GuiScreen guiScreen2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;

   @Override
   public void applyIndex(int count, int count2, float factor) {
      CouldnOpenHelper.applyIndex(23, 32, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.rangePresets.title", new Object[0]), this.width / 2, 8, 16777215);
      String text = null;
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2.isEnabled()) {
         text = StatCollector.getText("wdl.gui.rangePresets.vanilla.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter3.isEnabled()) {
         text = StatCollector.getText("wdl.gui.rangePresets.spigot.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter4.isEnabled()) {
         String text2 = StatCollector.getText("wdl.gui.rangePresets.server.description", new Object[0]) + "\n\n";
         if (this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3) {
            text = text2 + StatCollector.getText("wdl.gui.rangePresets.server.installed", new Object[0]);
         } else {
            text = text2 + StatCollector.getText("wdl.gui.rangePresets.server.notInstalled", new Object[0]);
         }
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter5.isEnabled()) {
         text = StatCollector.getText("wdl.gui.rangePresets.cancel.description", new Object[0]);
      }

      if (text != null) {
         CouldnOpenHelper.applyText(text, this.width, this.height, 48);
      }

      super.applyIndex(count, count2, factor);
   }

   public WdlGuiGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag) {
         Set set = AStarCoordinator.resolveSet(0x10bb378d9b0aL);
         if (count == 0) {
            for (String text : (Iterable<String>)(Object)(set)) {
               Pokechu22WorldDownloaderManager.properties2
                  .setProperty("Entity." + text + ".TrackDistance", Integer.toString(AStarCoordinator.computeCount(0x107159722784L, text)));
            }
         } else if (count == 1) {
            for (String text2 : (Iterable<String>)(Object)(set)) {
               Class clazz = (Class)AStarCoordinator.map.get(text2);
               if (clazz != null) {
                  Pokechu22WorldDownloaderManager.properties2
                     .setProperty("Entity." + text2 + ".TrackDistance", Integer.toString(AStarCoordinator.computeCount(clazz)));
               }
            }
         } else if (count == 2) {
            for (String text3 : (Iterable<String>)(Object)(set)) {
               Pokechu22WorldDownloaderManager.properties2
                  .setProperty("Entity." + text3 + ".TrackDistance", Integer.toString(DownloadInGeneralManager.computeCount(text3)));
            }
         }
      }

      GuiScreen guiScreen = this.guiScreen2;
      this.mc.displayGuiScreen(guiScreen);
   }

   @Override
   public void updateState2() {
      int count = this.height / 4;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         0, this.width / 2 - 100, count, StatCollector.getText("wdl.gui.rangePresets.vanilla", new Object[0])
      );
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         1, this.width / 2 - 100, count, StatCollector.getText("wdl.gui.rangePresets.spigot", new Object[0])
      );
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         2, this.width / 2 - 100, count, StatCollector.getText("wdl.gui.rangePresets.server", new Object[0])
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = DownloadInGeneralManager.isActive8();
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter4);
      count += 28;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.cancel", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter5);
   }

   @Override
   public void enableRepeatEvents() {
      Pokechu22WorldDownloaderManager.updateState2();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 3) {
            String text = StatCollector.getText("wdl.gui.rangePresets.upperWarning", new Object[0]);
            String text2;
            if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
               text2 = StatCollector.getText("wdl.gui.rangePresets.vanilla.warning", new Object[0]);
            } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
               text2 = StatCollector.getText("wdl.gui.rangePresets.spigot.warning", new Object[0]);
            } else {
               if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 != 2) {
                  throw new Error("Button.id should never be negative.");
               }

               text2 = StatCollector.getText("wdl.gui.rangePresets.server.warning", new Object[0]);
            }

            Minecraft mc = this.mc;
            DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(this, text, text2, auxiliaryTexturesGuiIconsPngManagerAdapter.count2);
            mc.displayGuiScreen(defaultGuiScreenAdapter);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }
}
