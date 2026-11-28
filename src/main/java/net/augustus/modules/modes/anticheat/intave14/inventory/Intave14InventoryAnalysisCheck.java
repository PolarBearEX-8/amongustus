package net.augustus.modules.modes.anticheat.intave14.inventory;

import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14InventoryAnalysisCheck extends ModuleMode {
   private ArrayList tickIntervals;
   private long lastClickTime = 0L;
   private long lastTickTime;

   @Override
   public void onClientPacket(Packet packet) {
   }

   public void recordTickInterval() {
      long currentTimeMs = System.currentTimeMillis();
      if (this.lastTickTime != 0L) {
         this.tickIntervals.add(currentTimeMs - this.lastTickTime);
         if (this.tickIntervals.size() >= 4) {
            this.tickIntervals.remove(0);
         }
      }

      this.lastTickTime = currentTimeMs;
   }

   public Intave14InventoryAnalysisCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.tickIntervals = new ArrayList();
      this.lastTickTime = 0L;
   }

   @Override
   public void onTick() {
   }

   private double calculateAverageLong(List list, int n, byte b, int n2) {
      double d = 0.0;

      for (Object obj : list) {
         d += ((Long)obj).doubleValue();
      }

      return d == 0.0 ? 0.0 : d / (double)list.size();
   }
}
