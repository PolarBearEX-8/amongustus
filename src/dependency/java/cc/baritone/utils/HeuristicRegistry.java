package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class HeuristicRegistry extends GuiScreen {
   public String labelText = null;
   public String labelText2 = "";
   public GuiScreen guiScreen2;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator;

   @Override
   public void updateState2() {
      if (Pokechu22WorldDownloaderManager.enabled4 && Pokechu22WorldDownloaderManager.labelText4.isEmpty()) {
         Minecraft mc = this.mc;
         HeuristicPipeline heuristicPipeline = new HeuristicPipeline(
            StatCollector.getText("wdl.gui.multiworldSelect.title.changeOptions", new Object[0]), new HeuristicRegistryVirtualListener(this)
         );
         mc.displayGuiScreen(heuristicPipeline);
      } else if (!Pokechu22WorldDownloaderManager.enabled5) {
         Minecraft mc2 = this.mc;
         HeuristicAuditor heuristicAuditor = new HeuristicAuditor(new WdlGuiExternalListener(this));
         mc2.displayGuiScreen(heuristicAuditor);
      } else {
         this.list.clear();
         this.labelText2 = StatCollector.getText("wdl.gui.wdl.title", new Object[]{Pokechu22WorldDownloaderManager.labelText2.replace('@', ':')});
         if (Pokechu22WorldDownloaderManager.properties4.getProperty("ServerName").isEmpty()) {
            Pokechu22WorldDownloaderManager.properties4.setProperty("ServerName", Pokechu22WorldDownloaderManager.getText());
         }

         this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
            42, this.fontRenderer, this.width / 2 - 155, 19, 150, 18
         );
         this.internalTexturesGuiIconsPngManagerAdapter.applyText(Pokechu22WorldDownloaderManager.properties4.getProperty("ServerName"));
         this.list
            .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
         this.heuristicRegistrySplineCalculator = new HeuristicRegistrySplineCalculator(this);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (!this.heuristicRegistrySplineCalculator.checkIndexByIndex(count, count2, count3)) {
         super.applyIndex(count, count2, count3);
      }
   }

   public HeuristicRegistry(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   public static Minecraft resolveMinecraft4(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   public static Minecraft resolveMinecraft7(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   public static Minecraft resolveMinecraftByHeuristicRegistr(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   public static String resolveText(HeuristicRegistry heuristicRegistry, String text) {
      return heuristicRegistry.labelText = text;
   }

   public static Minecraft resolveMinecraft5(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.labelText = null;
      this.heuristicRegistrySplineCalculator.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText2, this.width / 2, 8, 16777215);
      String text = StatCollector.getText("wdl.gui.wdl.worldname", new Object[0]);
      this.applyFontRenderer(
         this.fontRenderer, text, this.internalTexturesGuiIconsPngManagerAdapter.count7 - this.fontRenderer.getIndex(text + " "), 26, 16777215
      );
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
      CouldnOpenHelper.applyText(this.labelText, this.width, this.height, 48);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
   }

   public static Minecraft resolveMinecraft(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   @Override
   public void enableRepeatEvents() {
      if (this.internalTexturesGuiIconsPngManagerAdapter != null) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("ServerName", this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
         Pokechu22WorldDownloaderManager.updateState2();
      }
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      super.updateState4();
   }

   public static Minecraft resolveMinecraft2(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   public static Minecraft resolveMinecraft3(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      this.heuristicRegistrySplineCalculator.checkIndex(count, count2, count3);
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.heuristicRegistrySplineCalculator.updateState();
   }

   public static Minecraft resolveMinecraft6(HeuristicRegistry heuristicRegistry) {
      return heuristicRegistry.mc;
   }
}
