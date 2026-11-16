package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class WdlGuiPreUpdateDispatcherHandler implements PreUpdateDispatcher {
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public final GuiScreen guiScreen;
   public final String labelText;
   public final HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator;

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .matchesMinecraft(HeuristicRegistrySplineCalculator.resolveMinecraft(this.heuristicRegistrySplineCalculator), count2, count3)) {
         Minecraft mc = HeuristicRegistrySplineCalculator.resolveMinecraft2(this.heuristicRegistrySplineCalculator);
         GuiScreen guiScreen2 = this.guiScreen;
         mc.displayGuiScreen(guiScreen2);
         this.auxiliaryTexturesGuiIconsPngManagerAdapter
            .applySoundHandler(HeuristicRegistrySplineCalculator.resolveMinecraft3(this.heuristicRegistrySplineCalculator).getMcSoundHandler());
         return true;
      } else {
         return false;
      }
   }

   public WdlGuiPreUpdateDispatcherHandler(HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator2, String text, GuiScreen screen, boolean flag) {
      this.heuristicRegistrySplineCalculator = heuristicRegistrySplineCalculator2;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         0, 0, 0, StatCollector.getText("wdl.gui.wdl." + text + ".name", new Object[0])
      );
      this.guiScreen = screen;
      if (flag) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = DownloadInGeneralManager.isActive6();
      }

      this.labelText = StatCollector.getText("wdl.gui.wdl." + text + ".description", new Object[0]);
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = this.heuristicRegistrySplineCalculator.heuristicRegistry.width / 2 - 100;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count3;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .applyMinecraftByMinecraft(HeuristicRegistrySplineCalculator.resolveMinecraftByHeuristicRegistr(this.heuristicRegistrySplineCalculator), count6, count7);
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter.isEnabled()) {
         HeuristicRegistry.resolveText(this.heuristicRegistrySplineCalculator.heuristicRegistry, this.labelText);
      }
   }
}
