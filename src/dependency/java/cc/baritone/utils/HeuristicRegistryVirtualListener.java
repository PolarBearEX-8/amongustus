package cc.baritone.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class HeuristicRegistryVirtualListener implements VirtualListener {
   public final HeuristicRegistry heuristicRegistry;

   public HeuristicRegistryVirtualListener(HeuristicRegistry heuristicRegistry2) {
      this.heuristicRegistry = heuristicRegistry2;
   }

   @Override
   public void applyText(String text) {
      Pokechu22WorldDownloaderManager.labelText4 = text;
      Pokechu22WorldDownloaderManager.enabled4 = true;
      Pokechu22WorldDownloaderManager.enabled5 = true;
      Pokechu22WorldDownloaderManager.properties2 = Pokechu22WorldDownloaderManager.resolveProperties(text);
      Minecraft mc = HeuristicRegistry.resolveMinecraft3(this.heuristicRegistry);
      HeuristicRegistry heuristicRegistry2 = this.heuristicRegistry;
      mc.displayGuiScreen(heuristicRegistry2);
   }

   @Override
   public void updateState() {
      HeuristicRegistry.resolveMinecraft2(this.heuristicRegistry).displayGuiScreen((GuiScreen)null);
   }
}
