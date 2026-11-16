package cc.baritone.utils;

import client.util.MinecraftAccessor;
import net.minecraft.client.gui.GuiScreen;

public class DiagonalPlannerVirtualListener implements VirtualListener {
   public final DiagonalPlanner diagonalPlanner;

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

   public DiagonalPlannerVirtualListener(DiagonalPlanner diagonalPlanner2) {
      this.diagonalPlanner = diagonalPlanner2;
   }
}
