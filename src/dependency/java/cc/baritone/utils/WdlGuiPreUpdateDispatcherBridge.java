package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.util.StatCollector;

public class WdlGuiPreUpdateDispatcherBridge implements PreUpdateDispatcher {
   public final String labelText;
   public final int count;
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public boolean enabled;
   public final MinecraftSplineCalculator minecraftSplineCalculator;

   private String getText() {
      return this.enabled
         ? StatCollector.getText("wdl.gui.entities.group.enabled", new Object[0])
         : StatCollector.getText("wdl.gui.entities.group.disabled", new Object[0]);
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .matchesMinecraft(MinecraftSplineCalculator.resolveMinecraft9(this.minecraftSplineCalculator), count3, count4)) {
         this.enabled ^= true;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applySoundHandler(MinecraftSplineCalculator.resolveMinecraftByMinecraftSplineC(this.minecraftSplineCalculator).getMcSoundHandler());
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getText();
         Pokechu22WorldDownloaderManager.properties2.setProperty("EntityGroup." + this.labelText + ".Enabled", Boolean.toString(this.enabled));
         return true;
      } else {
         return false;
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public WdlGuiPreUpdateDispatcherBridge(MinecraftSplineCalculator minecraftSplineCalculator2, String text) {
      this.minecraftSplineCalculator = minecraftSplineCalculator2;
      this.labelText = text;
      this.count = MinecraftSplineCalculator.resolveMinecraft4(minecraftSplineCalculator2).fontRendererObj.getIndex(text);
      this.enabled = Pokechu22WorldDownloaderManager.properties2.getProperty("EntityGroup." + text + ".Enabled", "true").equals("true");
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, 0, 0, 90, 18, this.getText());
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7, int count8, boolean flag) {
      MinecraftSplineCalculator.resolveMinecraft5(this.minecraftSplineCalculator)
         .fontRendererObj
         .getIndex(
            this.labelText,
            count3 + 55 - this.count / 2,
            count4 + count6 - MinecraftSplineCalculator.resolveMinecraft3(this.minecraftSplineCalculator).fontRendererObj.indexOffset - 1,
            16777215
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = count3 + 110;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count4;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getText();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .applyMinecraftByMinecraft(MinecraftSplineCalculator.resolveMinecraft2(this.minecraftSplineCalculator), count7, count8);
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
   }

   @Override
   public void applyIndex(int count2, int count3, int count4) {
   }
}
