package cc.baritone.utils;

import client.util.MinecraftAccessor;
import net.minecraft.client.gui.GuiScreen;

public final class BreadthFirstAuditor implements VirtualListener {
   @Override
   public void updateState() {
      MinecraftAccessor.minecraft.displayGuiScreen((GuiScreen)null);
      Pokechu22WorldDownloaderManager.updateState9();
   }

   @Override
   public void applyText(String text) {
      Pokechu22WorldDownloaderManager.labelText4 = text;
      Pokechu22WorldDownloaderManager.enabled4 = true;
      Pokechu22WorldDownloaderManager.enabled5 = true;
      MinecraftAccessor.minecraft.displayGuiScreen((GuiScreen)null);
      Pokechu22WorldDownloaderManager.updateState3();
   }
}
