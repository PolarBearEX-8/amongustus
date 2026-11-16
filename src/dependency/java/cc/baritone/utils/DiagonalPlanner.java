package cc.baritone.utils;

import client.util.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public final class DiagonalPlanner implements ExternalListener {
   @Override
   public void setEnabled(boolean enabled) {
      Pokechu22WorldDownloaderManager.enabled4 = enabled;
      if (Pokechu22WorldDownloaderManager.enabled4) {
         Minecraft minecraft = MinecraftAccessor.minecraft;
         HeuristicPipeline heuristicPipeline = new HeuristicPipeline(
            StatCollector.getText("wdl.gui.multiworldSelect.title.startDownload", new Object[0]), new DiagonalPlannerVirtualListener(this)
         );
         minecraft.displayGuiScreen(heuristicPipeline);
      } else {
         Pokechu22WorldDownloaderManager.properties4.setProperty("LinkedWorlds", "");
         Pokechu22WorldDownloaderManager.updateState2();
         Pokechu22WorldDownloaderManager.enabled5 = true;
         MinecraftAccessor.minecraft.displayGuiScreen((GuiScreen)null);
         Pokechu22WorldDownloaderManager.updateState3();
      }
   }

   @Override
   public void updateState() {
      MinecraftAccessor.minecraft.displayGuiScreen((GuiScreen)null);
      Pokechu22WorldDownloaderManager.updateState9();
   }
}
