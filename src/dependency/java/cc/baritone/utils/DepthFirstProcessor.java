package cc.baritone.utils;

import client.util.MinecraftAccessor;
import net.minecraft.client.gui.GuiScreen;

public final class DepthFirstProcessor implements Runnable {
   public final HeuristicEngine heuristicEngine;

   @Override
   public void run() {
      HeuristicEngine heuristicEngine2 = this.heuristicEngine;
      MinecraftAccessor.minecraft.displayGuiScreen((GuiScreen)heuristicEngine2);
   }

   public DepthFirstProcessor(HeuristicEngine heuristicEngine2) {
      this.heuristicEngine = heuristicEngine2;
   }
}
