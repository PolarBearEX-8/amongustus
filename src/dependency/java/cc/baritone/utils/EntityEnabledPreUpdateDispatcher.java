package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.util.StatCollector;

public class EntityEnabledPreUpdateDispatcher implements PreUpdateDispatcher {
   public final WdlGuiPreUpdateDispatcherBridge wdlGuiPreUpdateDispatcherBridge;
   public final String labelText;
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public final MinecraftAuxiliaryTexturesGuiIc minecraftAuxiliaryTexturesGuiIc;
   public boolean enabled;
   public int count;
   public String labelText2;
   public final MinecraftSplineCalculator minecraftSplineCalculator;

   public EntityEnabledPreUpdateDispatcher(MinecraftSplineCalculator minecraftSplineCalculator2, WdlGuiPreUpdateDispatcherBridge wdlGuiPreUpdateDispatcherBridge2, String text) {
      super();
      this.minecraftSplineCalculator = minecraftSplineCalculator2;
      this.wdlGuiPreUpdateDispatcherBridge = wdlGuiPreUpdateDispatcherBridge2;
      this.labelText = text;
      this.enabled = Pokechu22WorldDownloaderManager.properties2.getProperty("Entity." + text + ".Enabled", "true").equals("true");
      this.count = AStarCoordinator.computeCount(text, 0x52381d48d0a4L);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, 0, 0, 75, 18, this.getText());
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = wdlGuiPreUpdateDispatcherBridge2.isEnabled();
      this.minecraftAuxiliaryTexturesGuiIc = new MinecraftAuxiliaryTexturesGuiIc(1, 0, 0, 150, 18, "wdl.gui.entities.trackDistance", this.count, 256);
      this.labelText2 = UserEntityGuiScreen.resolveText(minecraftSplineCalculator2.userEntityGuiScreen);
      this.minecraftAuxiliaryTexturesGuiIc.enabled3 = this.labelText2.equals("user");
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
      this.minecraftAuxiliaryTexturesGuiIc.applyIndex(count3, count4);
      if (this.labelText2.equals("user")) {
         this.count = this.minecraftAuxiliaryTexturesGuiIc.getCount();
         Pokechu22WorldDownloaderManager.properties2.setProperty("Entity." + this.labelText + ".TrackDistance", Integer.toString(this.count));
      }
   }

   private String getText() {
      return this.wdlGuiPreUpdateDispatcherBridge.isEnabled() && this.enabled
         ? StatCollector.getText("wdl.gui.entities.entity.included", new Object[0])
         : StatCollector.getText("wdl.gui.entities.entity.ignored", new Object[0]);
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7, int count8, boolean flag) {
      int count9 = this.minecraftSplineCalculator.userEntityGuiScreen.width / 2
         - MinecraftSplineCalculator.computeCountByMinecraftSplineC(this.minecraftSplineCalculator) / 2
         + MinecraftSplineCalculator.computeCount(this.minecraftSplineCalculator)
         + 10;
      MinecraftSplineCalculator.resolveMinecraft12(this.minecraftSplineCalculator)
         .fontRendererObj
         .getIndex(
            this.labelText,
            count9 - MinecraftSplineCalculator.computeCount(this.minecraftSplineCalculator) - 10,
            count4 + count6 / 2 - MinecraftSplineCalculator.resolveMinecraft11(this.minecraftSplineCalculator).fontRendererObj.indexOffset / 2,
            16777215
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = count9;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count4;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = this.wdlGuiPreUpdateDispatcherBridge.isEnabled();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getText();
      this.minecraftAuxiliaryTexturesGuiIc.count = count9 + 85;
      this.minecraftAuxiliaryTexturesGuiIc.count3 = count4;
      if (!this.labelText2.equals(UserEntityGuiScreen.resolveText(this.minecraftSplineCalculator.userEntityGuiScreen))) {
         this.labelText2 = UserEntityGuiScreen.resolveText(this.minecraftSplineCalculator.userEntityGuiScreen);
         this.minecraftAuxiliaryTexturesGuiIc.enabled3 = this.labelText2.equals("user");
         this.minecraftAuxiliaryTexturesGuiIc.applyIndex(AStarCoordinator.computeCount(this.labelText, 0x52381d48d0a4L));
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .applyMinecraftByMinecraft(MinecraftSplineCalculator.resolveMinecraft6(this.minecraftSplineCalculator), count7, count8);
      this.minecraftAuxiliaryTexturesGuiIc.applyMinecraftByMinecraft(MinecraftSplineCalculator.resolveMinecraft8(this.minecraftSplineCalculator), count7, count8);
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .matchesMinecraft(MinecraftSplineCalculator.resolveMinecraft7(this.minecraftSplineCalculator), count3, count4)) {
         this.enabled ^= true;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applySoundHandler(MinecraftSplineCalculator.resolveMinecraft10(this.minecraftSplineCalculator).getMcSoundHandler());
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getText();
         Pokechu22WorldDownloaderManager.properties2.setProperty("Entity." + this.labelText + ".Enabled", Boolean.toString(this.enabled));
         return true;
      } else if (this.minecraftAuxiliaryTexturesGuiIc.matchesMinecraft(MinecraftSplineCalculator.resolveMinecraft(this.minecraftSplineCalculator), count3, count4)) {
         this.count = this.minecraftAuxiliaryTexturesGuiIc.getCount();
         Pokechu22WorldDownloaderManager.properties2.setProperty("Entity." + this.labelText + ".TrackDistance", Integer.toString(this.count));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyIndex(int count2, int count3, int count4) {
   }
}
