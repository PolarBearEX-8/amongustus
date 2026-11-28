package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import java.util.ArrayList;
import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.Packet;

public class Intave14AutoClickerInvariantCheck extends ModuleMode {
   private long lastClickTime;
   private long lastInteractTime;
   private final ArrayList clickDelays;
   private long lastSwingTime = 0L;
   private double violationLevel;
   private boolean isDigging;

   public Intave14AutoClickerInvariantCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastClickTime = 0L;
      this.violationLevel = 0.0;
      this.clickDelays = new ArrayList();
      this.isDigging = false;
      this.lastInteractTime = 0L;
   }

   @Override
   public void onTick() {
   }

   private double calculateSkewness(ArrayList list) {
      ArrayList<Double> list2 = new ArrayList<>();
      double d = 0.0;
      int n = 0;

      for (Object obj : list) {
         double d2 = ((Long)obj).doubleValue();
         d += d2;
         list2.add(d2);
         n++;
      }

      if (n == 0) {
         return 0.0;
      } else {
         list2.sort(Double::compare);
         double d3 = d / (double)n;
         double d4 = list2.get(((n & 1) == 0 ? n - 1 : n) / 2);
         return (d3 - d4) * 3.0 / this.calculateStdDev(list2);
      }
   }

   private boolean shouldIgnoreClick(long l) {
      return l > 4000L
         || this.isDigging
         || System.currentTimeMillis() - this.lastInteractTime < 3000L
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFishingRod;
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   private double calculateStdDev(ArrayList list) {
      double d = 0.0;

      for (Object obj : list) {
         d += ((Double)obj).doubleValue();
      }

      double d2 = d / (double)list.size();
      double d3 = 0.0;

      for (Object obj2 : list) {
         double d4 = ((Double)obj2).doubleValue();
         d3 += (d4 - d2) * (d4 - d2);
      }

      return Math.sqrt(d3 / (double)list.size());
   }
}
