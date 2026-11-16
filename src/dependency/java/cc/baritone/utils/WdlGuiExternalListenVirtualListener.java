package cc.baritone.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class WdlGuiExternalListenVirtualListener implements VirtualListener {
   public final WdlGuiExternalListener wdlGuiExternalListener;

   @Override
   public void updateState() {
      HeuristicRegistry.resolveMinecraft4(this.wdlGuiExternalListener.heuristicRegistry).displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void applyText(String text) {
      Pokechu22WorldDownloaderManager.labelText4 = text;
      Pokechu22WorldDownloaderManager.enabled4 = true;
      Pokechu22WorldDownloaderManager.enabled5 = true;
      Pokechu22WorldDownloaderManager.properties2 = Pokechu22WorldDownloaderManager.resolveProperties(text);
      Minecraft mc = HeuristicRegistry.resolveMinecraft(this.wdlGuiExternalListener.heuristicRegistry);
      HeuristicRegistry heuristicRegistry = this.wdlGuiExternalListener.heuristicRegistry;
      mc.displayGuiScreen(heuristicRegistry);
   }

   public WdlGuiExternalListenVirtualListener(WdlGuiExternalListener wdlGuiExternalListener2) {
      this.wdlGuiExternalListener = wdlGuiExternalListener2;
   }
}
