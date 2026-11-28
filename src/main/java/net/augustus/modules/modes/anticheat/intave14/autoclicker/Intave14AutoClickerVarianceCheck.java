package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import java.util.ArrayList;
import java.util.Collection;
import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.Packet;

public class Intave14AutoClickerVarianceCheck extends ModuleMode {
   private final ArrayList clickDelays;
   private double violationLevel;
   private long lastSwingTime = 0L;
   private long lastClickTime;
   private boolean isDigging;
   private long lastInteractTime;

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   private double calculateStdDev(Collection items) {
      double d = 0.0;

      for (Object obj : items) {
         d += ((Long)obj).doubleValue();
      }

      double d2 = d / (double)items.size();
      double d3 = 0.0;

      for (Object obj2 : items) {
         double d4 = ((Long)obj2).doubleValue();
         d3 += (d4 - d2) * (d4 - d2);
      }

      return Math.sqrt(d3 / (double)items.size());
   }

   private boolean shouldIgnoreClick(long l) {
      return l > 4000L
         || this.isDigging
         || System.currentTimeMillis() - this.lastInteractTime < 3000L
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFishingRod;
   }

   public Intave14AutoClickerVarianceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.clickDelays = new ArrayList();
      this.lastClickTime = 0L;
      this.violationLevel = 0.0;
      this.isDigging = false;
      this.lastInteractTime = 0L;
   }
}
