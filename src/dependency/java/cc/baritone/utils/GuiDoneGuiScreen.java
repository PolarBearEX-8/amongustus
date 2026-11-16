package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class GuiDoneGuiScreen extends GuiScreen {
   public int count2;
   public static final int count3 = 23;
   public static final int count4 = 19;
   public static final int count5 = 32;
   public int count6 = -1;
   public final GuiScreen guiScreen2;
   public MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter;
   public WdlGuiPreUpdateDispatcherSpl wdlGuiPreUpdateDispatcherSpl;
   public boolean enabled6 = false;
   public int count7;

   private void applyStringEntry(StringEntry stringEntry) {
      this.wdlGuiPreUpdateDispatcherSpl.clear();
      if (stringEntry != null) {
         String text = stringEntry.getText();
         this.wdlGuiPreUpdateDispatcherSpl.applyText(text);
      }
   }

   @Override
   public void applyIndex(int count, int count8, int count9, long value) {
      if (this.enabled6) {
         this.count2 = count8 - this.count7;
      }

      if (this.count2 < 31) {
         this.count2 = 31;
      }

      if (this.count2 > this.height - 32 - 8) {
         this.count2 = this.height - 32 - 8;
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }
   }

   public static Minecraft resolveMinecraftByGuiDoneGuiScreen(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.mc;
   }

   @Override
   public void processAction(int count, int count8, int count9) throws IOException {
      if (count8 > this.count2 && count8 < this.count2 + 19) {
         this.enabled6 = true;
         this.count7 = count8 - this.count2;
      } else if (!this.minecraftSplineCalculatorAdapter.checkIndex(count, count8, count9)) {
         if (!this.wdlGuiPreUpdateDispatcherSpl.checkIndex(count, count8, count9)) {
            super.processAction(count, count8, count9);
         }
      }
   }

   @Override
   public void updateState2() {
      this.count2 = this.height - 100;
      this.enabled6 = false;
      this.minecraftSplineCalculatorAdapter = new MinecraftSplineCalculatorAdapter(this);
      this.wdlGuiPreUpdateDispatcherSpl = new WdlGuiPreUpdateDispatcherSpl(this);
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
   }

   public static FontRenderer resolveFontRenderer2(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.fontRenderer;
   }

   public GuiDoneGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   public static Minecraft resolveMinecraft(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.mc;
   }

   public static FontRenderer resolveFontRendererByGuiDoneGuiScreen(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.fontRenderer;
   }

   public static int computeCount(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.count2;
   }

   public static void applyGuiDoneGuiScreen(GuiDoneGuiScreen guiDoneGuiScreen, StringEntry stringEntry) {
      guiDoneGuiScreen.applyStringEntry(stringEntry);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.minecraftSplineCalculatorAdapter.updateState();
      this.wdlGuiPreUpdateDispatcherSpl.updateState();
   }

   public static int computeCount(GuiDoneGuiScreen guiDoneGuiScreen, int count) {
      return guiDoneGuiScreen.count6 = count;
   }

   @Override
   public void applyIndex(int count, int count8, int count9) {
      this.enabled6 = false;
      if (!this.minecraftSplineCalculatorAdapter.checkIndexByIndex(count, count8, count9)) {
         if (!this.wdlGuiPreUpdateDispatcherSpl.checkIndexByIndex(count, count8, count9)) {
            super.applyIndex(count, count8, count9);
         }
      }
   }

   public static FontRenderer resolveFontRenderer(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.fontRenderer;
   }

   public static int computeCountByGuiDoneGuiScreen(GuiDoneGuiScreen guiDoneGuiScreen) {
      return guiDoneGuiScreen.count6;
   }

   @Override
   public void applyIndex(int count, int count8, float factor) {
      this.renderElement3();
      if (this.count2 < 56) {
         this.count2 = 56;
      }

      if (this.count2 > this.height - 19 - 32 - 33) {
         this.count2 = this.height - 19 - 32 - 33;
      }

      this.minecraftSplineCalculatorAdapter.applyIndex(count, count8, factor);
      this.wdlGuiPreUpdateDispatcherSpl.applyIndex(count, count8, factor);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.extensions.title", new Object[0]), this.width / 2, 8, 16777215);
      super.applyIndex(count, count8, factor);
   }
}
