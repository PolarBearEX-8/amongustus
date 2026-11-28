package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import java.util.ArrayList;
import java.util.Collection;
import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.Packet;

public class Intave14AutoClickerSpikeCheck extends ModuleMode {
   private double violationLevel;
   private double lastCps;
   private long lastInteractTime;
   private boolean isDigging;
   private long lastSwingTime = 0L;
   private final ArrayList clickDelays = new ArrayList();

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   private boolean shouldIgnoreClick(long l) {
      return l > 4000L
         || this.isDigging
         || System.currentTimeMillis() - this.lastInteractTime < 3000L
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFishingRod;
   }

   public Intave14AutoClickerSpikeCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.violationLevel = 0.0;
      this.lastCps = 0.0;
      this.isDigging = false;
      this.lastInteractTime = 0L;
   }

   private double sumValues(Collection items) {
      double d = 0.0;

      for (Object obj : items) {
         d += ((Long)obj).doubleValue();
      }

      return d;
   }

   private double calculateCps(Collection items) {
      return 20.0 / this.sumValues(items) * 50.0;
   }
}
