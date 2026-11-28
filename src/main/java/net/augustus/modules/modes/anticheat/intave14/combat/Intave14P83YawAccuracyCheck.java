package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P83YawAccuracyCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private double violationBuffer;

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14P83YawAccuracyCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(83, "high accuracy rotation yaw vl:", AntiCheatCategory.KILLAURA);
      this.violationBuffer = 0.0;
   }

   @Override
   public void onTick() {
   }
}
