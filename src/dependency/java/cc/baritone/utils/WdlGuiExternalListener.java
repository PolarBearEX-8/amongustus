package cc.baritone.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class WdlGuiExternalListener implements ExternalListener {
   public final HeuristicRegistry heuristicRegistry;

   @Override
   public void updateState() {
      HeuristicRegistry.resolveMinecraftByHeuristicRegistr(this.heuristicRegistry).displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void setEnabled(boolean enabled) {
      Pokechu22WorldDownloaderManager.enabled4 = enabled;
      if (Pokechu22WorldDownloaderManager.enabled4) {
         Minecraft mc = HeuristicRegistry.resolveMinecraft6(this.heuristicRegistry);
         HeuristicPipeline heuristicPipeline = new HeuristicPipeline(
            StatCollector.getText("wdl.gui.multiworldSelect.title.changeOptions", new Object[0]), new WdlGuiExternalListenVirtualListener(this)
         );
         mc.displayGuiScreen(heuristicPipeline);
      } else {
         Pokechu22WorldDownloaderManager.properties4.setProperty("LinkedWorlds", "");
         Pokechu22WorldDownloaderManager.updateState2();
         Pokechu22WorldDownloaderManager.enabled5 = true;
         Minecraft mc2 = HeuristicRegistry.resolveMinecraft5(this.heuristicRegistry);
         HeuristicRegistry heuristicRegistry2 = this.heuristicRegistry;
         mc2.displayGuiScreen(heuristicRegistry2);
      }
   }

   public WdlGuiExternalListener(HeuristicRegistry heuristicRegistry2) {
      this.heuristicRegistry = heuristicRegistry2;
   }
}
