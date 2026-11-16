package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.setting.ClientSettingsBlackType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class WdlGuiPreUpdateDispatcher implements PreUpdateDispatcher {
   public final StringEntry stringEntry;
   public final String labelText;
   public String labelText2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public final MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter;

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter != null
         && this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .matchesMinecraft(MinecraftSplineCalculatorAdapter.resolveMinecraft2(this.minecraftSplineCalculatorAdapter), count2, count3)) {
         if (this.stringEntry.specialListener instanceof GuiScreenSpecialListener) {
            ((GuiScreenSpecialListener)this.stringEntry.specialListener).applyGuiScreen(this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen);
         }

         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applySoundHandler(MinecraftSplineCalculatorAdapter.resolveMinecraft5(this.minecraftSplineCalculatorAdapter).getMcSoundHandler());
         return true;
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2
         .matchesMinecraft(MinecraftSplineCalculatorAdapter.resolveMinecraft3(this.minecraftSplineCalculatorAdapter), count2, count3)) {
         this.stringEntry.updateState();
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2
            .applySoundHandler(MinecraftSplineCalculatorAdapter.resolveMinecraft6(this.minecraftSplineCalculatorAdapter).getMcSoundHandler());
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = StatCollector.getText(
            "wdl.gui.extensions." + (this.stringEntry.isActive() ? "enabled" : "disabled"), new Object[0]
         );
         if (!this.stringEntry.isActive()) {
            this.labelText2 = "" + ClientSettingsBlackType.GRAY + ClientSettingsBlackType.ITALIC + this.labelText;
         } else {
            this.labelText2 = this.labelText;
         }

         return true;
      } else if (GuiDoneGuiScreen.computeCountByGuiDoneGuiScreen(this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen) != count) {
         GuiDoneGuiScreen.computeCount(this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen, count);
         MinecraftSplineCalculatorAdapter.resolveMinecraft4(this.minecraftSplineCalculatorAdapter)
            .getMcSoundHandler()
            .applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("gui.button.press"), 1.0F));
         GuiDoneGuiScreen.applyGuiDoneGuiScreen(this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen, this.stringEntry);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   public WdlGuiPreUpdateDispatcher(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter2, StringEntry stringEntry2) {
      this.minecraftSplineCalculatorAdapter = minecraftSplineCalculatorAdapter2;
      this.stringEntry = stringEntry2;
      String textAsText = stringEntry2.getTextAsText();
      this.labelText = StatCollector.getText("wdl.gui.extensions.modVersion", new Object[]{textAsText, stringEntry2.labelText2});
      if (!stringEntry2.isActive()) {
         this.labelText2 = "" + ClientSettingsBlackType.GRAY + ClientSettingsBlackType.ITALIC + this.labelText;
      } else {
         this.labelText2 = this.labelText;
      }

      if (stringEntry2.specialListener instanceof GuiScreenSpecialListener) {
         GuiScreenSpecialListener specialListener = (GuiScreenSpecialListener)stringEntry2.specialListener;
         String text = specialListener.getText();
         if (text == null || text.isEmpty()) {
            StatCollector.getText("wdl.gui.extensions.defaultSettingsButtonText", new Object[0]);
         }

         this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, 0, 0, 80, 20, specialListener.getText());
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         0, 0, 0, 80, 20, StatCollector.getText("wdl.gui.extensions." + (stringEntry2.isActive() ? "enabled" : "disabled"), new Object[0])
      );
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter != null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.applyIndex(count2, count3);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter != null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen.width - 180;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count3 - 1;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applyMinecraftByMinecraft(MinecraftSplineCalculatorAdapter.resolveMinecraftByMinecraftSplineC(this.minecraftSplineCalculatorAdapter), count6, count7);
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count = this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen.width - 92;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 = count3 - 1;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2
         .applyMinecraftByMinecraft(MinecraftSplineCalculatorAdapter.resolveMinecraft(this.minecraftSplineCalculatorAdapter), count6, count7);
      int count8 = count3 + count5 / 2 - GuiDoneGuiScreen.resolveFontRenderer2(this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen).indexOffset / 2;
      GuiDoneGuiScreen.resolveFontRendererByGuiDoneGuiScreen(this.minecraftSplineCalculatorAdapter.guiDoneGuiScreen)
         .getIndex(this.labelText2, count2, count8, 16777215);
   }
}
