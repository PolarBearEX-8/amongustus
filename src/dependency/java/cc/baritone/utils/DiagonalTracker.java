package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class DiagonalTracker extends GuiScreen {
   public final GuiScreen guiScreen2;
   public static final String labelText = "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465";
   public static final String labelText2 = "https://github.com/Pokechu22/WorldDownloader";
   public static final String labelText3 = "https://github.com/uyjulian/LiteModWDL/";
   public PreUpdateDispatcherSplineCalculator preUpdateDispatcherSplineCalculator;

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (!this.preUpdateDispatcherSplineCalculator.checkIndexByIndex(count, count2, count3)) {
         super.applyIndex(count, count2, count3);
      }
   }

   public DiagonalTracker(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      this.preUpdateDispatcherSplineCalculator.checkIndex(count, count2, count3);
      super.processAction(count, count2, count3);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (this.preUpdateDispatcherSplineCalculator != null) {
         this.preUpdateDispatcherSplineCalculator.applyIndex(count, count2, factor);
         super.applyIndex(count, count2, factor);
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.about.title", new Object[0]), this.width / 2, 2, 16777215);
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.preUpdateDispatcherSplineCalculator.updateState();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         Minecraft mc = this.mc;
         GuiDoneGuiScreen guiDoneGuiScreen = new GuiDoneGuiScreen(this);
         mc.displayGuiScreen(guiDoneGuiScreen);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         applyText4(Pokechu22WorldDownloaderManager.getText4());
         auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = StatCollector.getText("wdl.gui.about.debugInfo.copied", new Object[0]);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }
   }

   @Override
   public void updateState2() {
      try {
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  0, this.width / 2 - 155, 18, 150, 20, StatCollector.getText("wdl.gui.about.extensions", new Object[0])
               )
            );
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  1, this.width / 2 + 5, 18, 150, 20, StatCollector.getText("wdl.gui.about.debugInfo", new Object[0])
               )
            );
         this.list
            .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
         String text = "1.8.9a-beta2";
         String text2 = Pokechu22WorldDownloaderManager.getText3();
         this.preUpdateDispatcherSplineCalculator = new PreUpdateDispatcherSplineCalculator(this.mc, this.width, this.height, 39, 32);
         this.preUpdateDispatcherSplineCalculator.applyText(StatCollector.getText("wdl.gui.about.blurb", new Object[0]));
         this.preUpdateDispatcherSplineCalculator.add();
         this.preUpdateDispatcherSplineCalculator.applyText(StatCollector.getText("wdl.gui.about.version", new Object[]{text, text2}));
         this.preUpdateDispatcherSplineCalculator.add();
         String text3 = Pokechu22WorldDownloaderManager.minecraft.getLanguageManager().getBrandProperty().toString();
         String text4 = StatCollector.getText("wdl.translatorCredit", new Object[]{text3});
         if (text4 != null && !text4.isEmpty()) {
            this.preUpdateDispatcherSplineCalculator.applyText(text4);
            this.preUpdateDispatcherSplineCalculator.add();
         }

         this.preUpdateDispatcherSplineCalculator
            .applyText(
               StatCollector.getText("wdl.gui.about.forumThread", new Object[0]),
               "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465"
            );
         this.preUpdateDispatcherSplineCalculator.add();
         this.preUpdateDispatcherSplineCalculator
            .applyText(StatCollector.getText("wdl.gui.about.coremodSrc", new Object[0]), "https://github.com/Pokechu22/WorldDownloader");
         this.preUpdateDispatcherSplineCalculator.add();
         this.preUpdateDispatcherSplineCalculator
            .applyText(StatCollector.getText("wdl.gui.about.litemodSrc", new Object[0]), "https://github.com/uyjulian/LiteModWDL/");
      } catch (NullPointerException nullPointerException) {
         nullPointerException.printStackTrace();
      }
   }
}
